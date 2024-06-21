package com.cafeteria.recommendation;

import com.cafeteria.db.DBOperationsHandler;
import com.cafeteria.feedback.FeedbackItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    private DBOperationsHandler dbOperationsHandler;
    private static final List<String> POSITIVE_WORDS = List.of("good", "excellent", "amazing", "awesome", "great");
    private static final List<String> NEGATIVE_WORDS = List.of("bad", "poor", "terrible", "awful", "worst");

    public ReportService(DBOperationsHandler dbOperationsHandler) {
        this.dbOperationsHandler = dbOperationsHandler;
    }

    public List<FeedbackItem> getAllFeedback() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        Timestamp timestampOneMonthAgo = Timestamp.valueOf(oneMonthAgo);

        String query = "SELECT * FROM Feedback WHERE date >= ?";
        ResultSet resultSet = dbOperationsHandler.executeQuery(query, timestampOneMonthAgo);
        List<FeedbackItem> feedbackList = new ArrayList<>();
        try {
            while (resultSet != null && resultSet.next()) {
                FeedbackItem feedback = new FeedbackItem(
                        resultSet.getInt("feedbackId"),
                        resultSet.getInt("foodItemId"),
                        resultSet.getFloat("rating"),
                        resultSet.getString("comment"),
                        resultSet.getTimestamp("date")
                );
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    public Map<Integer, List<FeedbackItem>> groupFeedbackByFoodItem(List<FeedbackItem> feedbackList) {
        Map<Integer, List<FeedbackItem>> groupedFeedback = new HashMap<>();
        for (FeedbackItem feedback : feedbackList) {
            groupedFeedback.computeIfAbsent(feedback.getFoodItemId(), k -> new ArrayList<>()).add(feedback);
        }
        return groupedFeedback;
    }

    public Map<Integer, Double> calculateAverageRatings(Map<Integer, List<FeedbackItem>> groupedFeedback) {
        Map<Integer, Double> averageRatings = new HashMap<>();
        for (Map.Entry<Integer, List<FeedbackItem>> entry : groupedFeedback.entrySet()) {
            int foodItemId = entry.getKey();
            List<FeedbackItem> feedbacks = entry.getValue();
            double sum = 0;
            for (FeedbackItem feedback : feedbacks) {
                sum += feedback.getRating();
            }
            double average = sum / feedbacks.size();
            averageRatings.put(foodItemId, average);
        }
        return averageRatings;
    }

    public Map<Integer, Double> performSentimentAnalysis(Map<Integer, List<FeedbackItem>> groupedFeedback, Map<Integer, Double> averageRatings) {
        Map<Integer, Double> adjustedRatings = new HashMap<>(averageRatings);
        for (Map.Entry<Integer, List<FeedbackItem>> entry : groupedFeedback.entrySet()) {
            int foodItemId = entry.getKey();
            List<FeedbackItem> feedbacks = entry.getValue();
            for (FeedbackItem feedback : feedbacks) {
                int sentimentScore = calculateSentimentScore(feedback.getComments());
                double currentRating = adjustedRatings.get(foodItemId);
                adjustedRatings.put(foodItemId, currentRating + sentimentScore * 0.1);
            }
        }
        return adjustedRatings;
    }

    private int calculateSentimentScore(String comments) {
        int score = 0;
        for (String word : comments.split("\\s+")) {
            if (POSITIVE_WORDS.contains(word.toLowerCase())) {
                score++;
            } else if (NEGATIVE_WORDS.contains(word.toLowerCase())) {
                score--;
            }
        }
        return score;
    }

    public void storeRecommendation(int foodItemId, double adjustedRating) {
        String query = "INSERT INTO RecommendedMenu (foodItemId, averageRating) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE rating = ?";
        dbOperationsHandler.executeQuery(query, foodItemId, adjustedRating, adjustedRating);
    }

    public void calculateAndStoreRecommendations() {
        List<FeedbackItem> feedbackList = getAllFeedback();
        Map<Integer, List<FeedbackItem>> groupedFeedback = groupFeedbackByFoodItem(feedbackList);
        Map<Integer, Double> averageRatings = calculateAverageRatings(groupedFeedback);
        Map<Integer, Double> adjustedRatings = performSentimentAnalysis(groupedFeedback, averageRatings);

        // Store adjusted ratings into RecommendedMenu table
        for (Map.Entry<Integer, Double> entry : adjustedRatings.entrySet()) {
            int foodItemId = entry.getKey();
            double adjustedRating = entry.getValue();
            storeRecommendation(foodItemId, adjustedRating);
        }
    }
}

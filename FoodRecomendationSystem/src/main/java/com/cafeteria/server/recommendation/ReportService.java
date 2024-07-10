package com.cafeteria.server.recommendation;

import com.cafeteria.server.feedback.FeedbackItem;
import com.cafeteria.server.feedback.FeedbackService;
import com.cafeteria.server.menu.MenuItem;
import com.cafeteria.server.menu.MenuService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ReportService {

    private FeedbackService feedbackService;
    private SentimentAnalysisService sentimentAnalysisService;
    private MenuService menuService;
    public ReportService() throws SQLException {
        this.feedbackService = new FeedbackService();
        this.sentimentAnalysisService = new SentimentAnalysisService();
        this.menuService = new MenuService();
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
            averageRatings.put(foodItemId, Math.min(5.0, Math.max(1.0, Math.round(average * 10) / 10.0)));  // Ensure rating is between 1 and 5
        }
        return averageRatings;
    }

    public JSONObject createRecommendationsJSON(int topN, boolean includeSentiment) {
        List<FeedbackItem> feedbackList = feedbackService.viewRecentFeedback();
        Map<Integer, List<FeedbackItem>> groupedFeedback = groupFeedbackByFoodItem(feedbackList);
        Map<Integer, Double> averageRatings = calculateAverageRatings(groupedFeedback);

        Map<Integer, Integer> sentimentScores = null;
        if (includeSentiment) {
            sentimentScores = sentimentAnalysisService.calculateSentimentScores(groupedFeedback);
        }

        List<JSONObject> recommendations = new ArrayList<>();

        for (Map.Entry<Integer, Double> entry : averageRatings.entrySet())
        {
            int foodItemId = entry.getKey();
            double averageRating = entry.getValue();
            List<String> comments = groupedFeedback.get(foodItemId).stream()
                    .map(FeedbackItem::getComments)
                    .collect(Collectors.toList());

            // For demonstration, assume you have a method to get the food item details by ID
            MenuItem foodItem = menuService.getMenuIteamByFoodId(foodItemId);

            String framedSentiment = null;
            if (includeSentiment) {
                framedSentiment = sentimentAnalysisService.frameSentiment(groupedFeedback.get(foodItemId));
            }

            JSONObject recommendation = new JSONObject();
            recommendation.put("foodId", foodItem.getFoodItemID());
            recommendation.put("name", foodItem.getName());
            recommendation.put("price", foodItem.getPrice());
            recommendation.put("averageRating", averageRating);
            recommendation.put("framedSentiment", framedSentiment);
            recommendations.add(recommendation);
        }

        JSONArray sortedRecommendations = sortRecommendations(recommendations,topN);

        JSONObject response = new JSONObject();
        response.put("recommendations", sortedRecommendations);
        return response;
    }

    public static JSONArray sortRecommendations(List<JSONObject> recommendations, int topN) {
        return new JSONArray(
                recommendations.stream()
                        .sorted(Comparator.comparingDouble(recommendation -> ((JSONObject) recommendation).optDouble("averageRating", 0.0)).reversed())
                        .limit(topN)
                        .collect(Collectors.toList())
        );

    }


}

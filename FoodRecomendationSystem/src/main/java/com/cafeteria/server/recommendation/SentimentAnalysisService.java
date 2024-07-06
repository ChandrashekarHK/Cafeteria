package com.cafeteria.server.recommendation;

import com.cafeteria.server.feedback.FeedbackItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentimentAnalysisService {

    private static final List<String> POSITIVE_WORDS = List.of("good", "excellent", "amazing", "awesome", "great");
    private static final List<String> NEGATIVE_WORDS = List.of("bad", "poor", "terrible", "awful", "worst");

    public Map<Integer, Integer> calculateSentimentScores(Map<Integer, List<FeedbackItem>> groupedFeedback) {
        Map<Integer, Integer> sentimentScores = new HashMap<>();
        for (Map.Entry<Integer, List<FeedbackItem>> entry : groupedFeedback.entrySet()) {
            int foodItemId = entry.getKey();
            int totalSentimentScore = 0;
            int positiveCount = 0;
            int negativeCount = 0;
            for (FeedbackItem feedback : entry.getValue()) {
                int sentimentScore = calculateSentimentScore(feedback.getComments());
                totalSentimentScore += sentimentScore;
                if (sentimentScore > 0) {
                    positiveCount++;
                } else if (sentimentScore < 0) {
                    negativeCount++;
                }
            }
            sentimentScores.put(foodItemId, totalSentimentScore);
            System.out.println("Food Item ID: " + foodItemId + " Positive Comments: " + positiveCount + " Negative Comments: " + negativeCount);
        }
        return sentimentScores;
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

    public String determineSentiment(String comments) {
        int score = calculateSentimentScore(comments);
        if (score > 0) {
            return "Positive";
        } else if (score < 0) {
            return "Negative";
        } else {
            return "Neutral";
        }
    }

    public String frameSentiment(List<FeedbackItem> feedbacks) {
        StringBuilder framedSentiment = new StringBuilder();
        for (FeedbackItem feedback : feedbacks) {
            framedSentiment.append(feedback.getComments()).append(" (").append(determineSentiment(feedback.getComments())).append(") ").append("\n");
        }
        return framedSentiment.toString().trim();
    }
}

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
            for (FeedbackItem feedback : entry.getValue()) {
                totalSentimentScore += calculateSentimentScore(feedback.getComments());
            }
            sentimentScores.put(foodItemId, totalSentimentScore);
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

    public String frameSentiment(List<FeedbackItem> feedbacks) {
        StringBuilder framedSentiment = new StringBuilder();
        for (FeedbackItem feedback : feedbacks) {
            framedSentiment.append(feedback.getComments()).append(" ");
        }
        return framedSentiment.toString().trim();
    }
}

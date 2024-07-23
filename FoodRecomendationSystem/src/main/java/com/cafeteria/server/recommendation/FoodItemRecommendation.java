package com.cafeteria.server.recommendation;

import java.math.BigDecimal;
import java.util.List;

public class FoodItemRecommendation {
    private int foodId;
    private String name;
    private BigDecimal price;
    private double averageRating;
    private String framedSentiment;

    public FoodItemRecommendation(int foodId, String name, BigDecimal price, double averageRating,
            String framedSentiment) {
        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.averageRating = averageRating;
        this.framedSentiment = framedSentiment;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getFramedSentiment() {
        return framedSentiment;
    }
}

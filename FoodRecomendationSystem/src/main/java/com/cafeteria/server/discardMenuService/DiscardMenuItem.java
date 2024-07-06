package com.cafeteria.server.discardMenuService;

import java.sql.Timestamp;

public class DiscardMenuItem {
    private int discardId;
    private int foodId;
    private String name;
    private double averageRating;
    private Timestamp date;

    // Getters and Setters
    public int getDiscardId() {
        return discardId;
    }

    public void setDiscardId(int discardId) {
        this.discardId = discardId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}

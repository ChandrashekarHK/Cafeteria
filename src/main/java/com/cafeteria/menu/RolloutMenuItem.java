package com.cafeteria.menu;

import java.math.BigDecimal;

public class RolloutMenuItem {
    private int foodItemId;
    private String name;
    private BigDecimal price;
    private int vote;

    public RolloutMenuItem(int foodItemId, String name, BigDecimal price, int vote) {
        this.foodItemId = foodItemId;
        this.name = name;
        this.price = price;
        this.vote = vote;
    }

    // Getters
    public int getFoodItemId() {
        return foodItemId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getVote() {
        return vote;
    }

    // Setters
    public void setFoodItemId(int foodItemId) {
        this.foodItemId = foodItemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Food Item ID: " + foodItemId +
                ", Name: " + name +
                ", Price: " + price +
                ", Vote: " + vote;
    }
}

package com.cafeteria.server.menu;

import java.time.LocalDateTime;

public class RolloutMenuItem {
    private int foodId;
    private String name;
    private int rolloutID;
    private LocalDateTime rolloutDate;

    public RolloutMenuItem(int foodId, String name, int rolloutID, LocalDateTime rolloutDate) {
        this.foodId = foodId;
        this.name = name;
        this.rolloutID = rolloutID;
        this.rolloutDate = rolloutDate;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getName() {
        return name;
    }

    public int getRolloutID() {
        return rolloutID;
    }

    public LocalDateTime getRolloutDate() {
        return rolloutDate;
    }

    // Setters
    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRolloutID(int rolloutID) {
        this.rolloutID = rolloutID;
    }

    public void setRolloutDate(LocalDateTime rolloutDate) {
        this.rolloutDate = rolloutDate;
    }

    @Override
    public String toString() {
        return "Food Item ID: " + foodId +
                ", Name: " + name +
                ", RolloutID: " + rolloutID +
                ", Date: " + rolloutDate;
    }
}

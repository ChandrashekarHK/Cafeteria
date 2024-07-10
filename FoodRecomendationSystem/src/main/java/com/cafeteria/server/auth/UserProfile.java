package com.cafeteria.server.auth;

import java.time.LocalDateTime;

public class UserProfile {
    private int profileID;
    private String userId;
    private String userName;
    private String foodType;
    private int spiceLevel;
    private int sweetness;
    private int saltiness;
    private String cuisineType;
    private LocalDateTime createdAt;


    public UserProfile(String userId, String foodType, int spiceLevel, int sweetness, int saltiness, String cuisineType) {
        this.userId = userId;
        this.foodType = foodType;
        this.spiceLevel = spiceLevel;
        this.sweetness = sweetness;
        this.saltiness = saltiness;
        this.cuisineType = cuisineType;
    }


    public UserProfile(int profileID, String userId, String foodType, int spiceLevel, int sweetness, int saltiness, String cuisineType, LocalDateTime createdAt) {
        this.profileID = profileID;
        this.userId = userId;
        this.foodType = foodType;
        this.spiceLevel = spiceLevel;
        this.sweetness = sweetness;
        this.saltiness = saltiness;
        this.cuisineType = cuisineType;
        this.createdAt = createdAt;
    }

    // Getters and setters for all fields
    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getSpiceLevel() {
        return spiceLevel;
    }

    public void setSpiceLevel(int spiceLevel) {
        this.spiceLevel = spiceLevel;
    }

    public int getSweetness() {
        return sweetness;
    }

    public void setSweetness(int sweetness) {
        this.sweetness = sweetness;
    }

    public int getSaltiness() {
        return saltiness;
    }

    public void setSaltiness(int saltiness) {
        this.saltiness = saltiness;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Optional: Override toString() method for debugging purposes
    @Override
    public String toString() {
        return "UserProfile{" +
                "profileID=" + profileID +
                ", userName='" + userName + '\'' +
                ", foodType='" + foodType + '\'' +
                ", spiceLevel=" + spiceLevel +
                ", sweetness=" + sweetness +
                ", saltiness=" + saltiness +
                ", cuisineType='" + cuisineType + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

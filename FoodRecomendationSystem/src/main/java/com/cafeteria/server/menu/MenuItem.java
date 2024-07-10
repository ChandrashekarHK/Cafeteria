package com.cafeteria.server.menu;

import java.math.BigDecimal;

public class MenuItem {
    private int foodItemID;
    private String name;
    private BigDecimal price;
    private boolean availability;
    private String cuisineType;
    private int spiceLevel;
    private String foodType;
    private int saltiness;
    private int sweetness;
    private String category;

    public MenuItem(int foodItemID, String name, BigDecimal price, boolean availability, String cuisineType, int spiceLevel, String foodType, int saltiness, int sweetness, String category) {
        this.foodItemID = foodItemID;
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.cuisineType = cuisineType;
        this.spiceLevel = spiceLevel;
        this.foodType = foodType;
        this.saltiness = saltiness;
        this.sweetness = sweetness;
        this.category = category;
    }

    // Getters and setters for all fields
    public int getFoodItemID() {
        return foodItemID;
    }

    public void setFoodItemID(int foodItemID) {
        this.foodItemID = foodItemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public int getSpiceLevel() {
        return spiceLevel;
    }

    public void setSpiceLevel(int spiceLevel) {
        this.spiceLevel = spiceLevel;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getSaltiness() {
        return saltiness;
    }

    public void setSaltiness(int saltiness) {
        this.saltiness = saltiness;
    }

    public int getSweetness() {
        return sweetness;
    }

    public void setSweetness(int sweetness) {
        this.sweetness = sweetness;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}


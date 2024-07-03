package com.cafeteria.server.menu;

import java.math.BigDecimal;

public class MenuItem {
    private int foodId;
    private String name;
    private boolean availability;
    private BigDecimal price;

    public MenuItem(int foodId, String name, BigDecimal price, boolean availability) {
        this.foodId = foodId;
        this.name = name;
        this.availability = availability;
        this.price = price;
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

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String toString() {
        return "Food Item ID: " + foodId +
                ", Name: " + name +
                ", Price: " + price +
                ", Availability: " + (availability ? "Available" : "Not Available");
    }
}

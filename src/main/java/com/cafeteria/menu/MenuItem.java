package com.cafeteria.menu;

import java.math.BigDecimal;

public class MenuItem {
    private int id;
    private String name;
    private boolean availability;
    private BigDecimal price;

    public MenuItem(int id, String name, BigDecimal price,boolean availability) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Food Item ID: " + id +
                ", Name: " + name +
                ", Price: " + price +
                ", Availability: " + (availability ? "Available" : "Not Available");
    }
}

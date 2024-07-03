package com.cafeteria.server.feedback;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FeedbackItem {
    private int feedbackId;
    private int foodItemId;
    private float rating;
    private String comments;
    private String userID;
    private Timestamp date;

    public FeedbackItem(int feedbackId, int foodItemId, float rating, String comments,String userID,Timestamp date) {
        this.feedbackId = feedbackId;
        this.foodItemId = foodItemId;
        this.userID = userID;
        this.rating = rating;
        this.comments = comments;
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(int foodItemId) {
        this.foodItemId = foodItemId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FeedbackItem{" +
                "feedbackId=" + feedbackId +
                ", foodItemId=" + foodItemId +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                ", date=" + date +
                '}';
    }
}

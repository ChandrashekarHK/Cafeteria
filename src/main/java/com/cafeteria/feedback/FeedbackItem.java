package com.cafeteria.feedback;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FeedbackItem {
    private int feedbackId;
    private int foodItemId;
    private float rating;
    private String comments;
    private Timestamp date;

    public FeedbackItem(int feedbackId, int foodItemId, float rating, String comments, Timestamp date) {
        this.feedbackId = feedbackId;
        this.foodItemId = foodItemId;

        this.rating = rating;
        this.comments = comments;
        this.date = date;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public int getFoodItemId() {
        return foodItemId;
    }



    public float getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    public Timestamp getDate() {
        return date;
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

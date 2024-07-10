package com.cafeteria.server.feedback;


import java.time.LocalDateTime;


public class DiscardItemFeedback {
    private int feedbackID;
    private String userId;
    private int discardID;
    private String questionOne;
    private String questionTwo;
    private String questionThree;

    public DiscardItemFeedback(String userId, int discardID, String questionOne, String questionTwo, String questionThree) {
        this.userId = userId;
        this.discardID = discardID;
        this.questionOne = questionOne;
        this.questionTwo = questionTwo;
        this.questionThree = questionThree;
    }

    public DiscardItemFeedback(int feedbackID, String userId, int discardID, String questionOne, String questionTwo, String questionThree) {
        this.feedbackID = feedbackID;
        this.userId = userId;
        this.discardID = discardID;
        this.questionOne = questionOne;
        this.questionTwo = questionTwo;
        this.questionThree = questionThree;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public String getUserId() {
        return userId;
    }

    public int getDiscardID() {
        return discardID;
    }

    public String getQuestionOne() {
        return questionOne;
    }

    public String getQuestionTwo() {
        return questionTwo;
    }

    public String getQuestionThree() {
        return questionThree;
    }
}

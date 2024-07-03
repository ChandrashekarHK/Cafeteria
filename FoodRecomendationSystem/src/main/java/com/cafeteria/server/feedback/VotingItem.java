package com.cafeteria.server.feedback;


import java.sql.Timestamp;

public class VotingItem {
    private int votingId;
    private int foodItemId;
    private int vote;
    private String voterUserId;
    private Timestamp date;
    private int rolloutID;

    public VotingItem(int votingId, int foodItemId, int vote, String voterUserId, Timestamp date,int rolloutID) {
        this.votingId = votingId;
        this.foodItemId = foodItemId;
        this.vote = vote;
        this.voterUserId = voterUserId;
        this.date = date;
        this.rolloutID = rolloutID;
    }

    public int getVotingId() {
        return votingId;
    }

    public void setVotingId(int votingId) {
        this.votingId = votingId;
    }

    public int getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(int foodItemId) {
        this.foodItemId = foodItemId;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getVoterUserId() {
        return voterUserId;
    }

    public void setVoterUserId(String voterUserId) {
        this.voterUserId = voterUserId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getRolloutID() {
        return rolloutID;
    }

    public void setRolloutID(int rolloutID) {
        this.rolloutID = rolloutID;
    }
}

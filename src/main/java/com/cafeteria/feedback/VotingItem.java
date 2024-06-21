package com.cafeteria.feedback;


import java.sql.Timestamp;

public class VotingItem {
    private int votingId;
    private int foodItemId;
    private int vote;
    private int voterUserId;
    private Timestamp date;

    public VotingItem(int votingId, int foodItemId, int vote, int voterUserId, Timestamp date) {
        this.votingId = votingId;
        this.foodItemId = foodItemId;
        this.vote = vote;
        this.voterUserId = voterUserId;
        this.date = date;
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

    public int getVoterUserId() {
        return voterUserId;
    }

    public void setVoterUserId(int voterUserId) {
        this.voterUserId = voterUserId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}

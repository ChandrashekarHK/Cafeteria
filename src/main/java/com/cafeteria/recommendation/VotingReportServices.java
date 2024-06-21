package com.cafeteria.recommendation;

import com.cafeteria.db.DBOperationsHandler;
import com.cafeteria.feedback.VotingItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VotingReportServices {
    private DBOperationsHandler dbOperationsHandler;

    public VotingReportServices(DBOperationsHandler dbOperationsHandler) {
        this.dbOperationsHandler = dbOperationsHandler;
    }

    public List<VotingItem> getAllVoting() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        Timestamp timestampOneMonthAgo = Timestamp.valueOf(oneMonthAgo);

        String query = "SELECT * FROM Voting WHERE date >= ?";
        ResultSet resultSet = dbOperationsHandler.executeQuery(query, timestampOneMonthAgo);
        List<VotingItem> votingList = new ArrayList<>();
        try {
            while (resultSet != null && resultSet.next()) {
                VotingItem voting = new VotingItem(
                        resultSet.getInt("votingId"),
                        resultSet.getInt("foodItemId"),
                        resultSet.getInt("vote"),
                        resultSet.getInt("voterUserId"),
                        resultSet.getTimestamp("date")
                );
                votingList.add(voting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return votingList;
    }

    public Map<Integer, List<VotingItem>> groupVotingByFoodItem(List<VotingItem> votingList) {
        Map<Integer, List<VotingItem>> groupedVoting = new HashMap<>();
        for (VotingItem voting : votingList) {
            groupedVoting.computeIfAbsent(voting.getFoodItemId(), k -> new ArrayList<>()).add(voting);
        }
        return groupedVoting;
    }

    public Map<Integer, Double> calculateAverageVotes(Map<Integer, List<VotingItem>> groupedVoting) {
        Map<Integer, Double> averageVotes = new HashMap<>();
        for (Map.Entry<Integer, List<VotingItem>> entry : groupedVoting.entrySet()) {
            int foodItemId = entry.getKey();
            List<VotingItem> votings = entry.getValue();
            double sum = 0;
            for (VotingItem voting : votings) {
                sum += voting.getVote();
            }
            double average = sum / votings.size();
            averageVotes.put(foodItemId, average);
        }
        return averageVotes;
    }

    public void storeRolloutItem(int foodItemId, double averageVote) {
        String query = "UPDATE RollOutItems SET vote = ? WHERE foodItemId = ?";
        dbOperationsHandler.executeQuery(query, averageVote, foodItemId);
    }

    public void calculateAndStoreVotingResults() {
        List<VotingItem> votingList = getAllVoting();
        Map<Integer, List<VotingItem>> groupedVoting = groupVotingByFoodItem(votingList);
        Map<Integer, Double> averageVotes = calculateAverageVotes(groupedVoting);

        // Store average votes into RollOutItems table if the foodItemId exists in the table
        for (Map.Entry<Integer, Double> entry : averageVotes.entrySet()) {
            int foodItemId = entry.getKey();
            double averageVote = entry.getValue();
            storeRolloutItem(foodItemId, averageVote);
        }
    }
}

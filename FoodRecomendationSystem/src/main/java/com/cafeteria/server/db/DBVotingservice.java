package com.cafeteria.server.db;

import com.cafeteria.server.feedback.VotingItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBVotingservice {

  //  private Connection connection;
    public DBVotingservice() throws SQLException {
       // this.connection = DatabaseConnector.getConnection();
    }

    public List<VotingItem> getAllVoting() {
        String query = "SELECT * FROM Voting WHERE RolloutID IS NOT NULL";
        List<VotingItem> votingList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int votingId = rs.getInt("voteID");
                int foodItemId = rs.getInt("foodItemId");
                int vote = rs.getInt("vote");
                String voterUserId = rs.getString("userID");
                Timestamp date = rs.getTimestamp("date");
                int rolloutID = rs.getInt("RolloutID");

                VotingItem votingItem = new VotingItem(votingId, foodItemId, vote, voterUserId, date,rolloutID);
                votingList.add(votingItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return votingList;
    }

        public boolean addVotingrecord(VotingItem votingItem) {
            String query = "INSERT INTO Voting (foodItemId, vote, rolloutID, userID, date) VALUES (?, ?, ?, ?, ?)";

            try (Connection connection = DatabaseConnector.getInstance().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, votingItem.getFoodItemId());
                stmt.setInt(2, votingItem.getVote());
                stmt.setInt(3, votingItem.getRolloutID());
                stmt.setString(4, votingItem.getVoterUserId());
                stmt.setTimestamp(5, votingItem.getDate());

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected == 1;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }


}

package com.cafeteria.feedback;

import com.cafeteria.db.DBOperationsHandler;
import com.cafeteria.server.ClientHandler;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeedbackService {
//    private DBOperationsHandler dbOperationsHandler;
//    private ClientHandler clientHandler;
//    //private FeedbackItem feedback;
//
//    public FeedbackService(ClintHandler clientHandler) throws SQLException {
//        this.dbOperationsHandler = new DBOperationsHandler();
//        this,clientHandler = clientHandler;
//    }
//
//    public ResultSet giveFeedback() throws IOException {
//        String query = "INSERT INTO Feedback (foodItemId, qualityRating, rating, comments, date) VALUES (?, ?, ?, ?, ?)";
//        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now());
//
//        clientHandler.writeToClient("Enter item ID to update: ");
//        int foodItemId = Integer.parseInt(clientHandler.readFromClient());
//        clientHandler.writeToClient("Enter item ID to update: ");
//        Float rating = (float) Integer.parseInt(clientHandler.readFromClient());
//        clientHandler.writeToClient("Enter your comment to food ID: ");
//        String comments = clientHandler.readFromClient();
//
//        return dbOperationsHandler.executeQuery(query, foodItemId, rating, comments, currentDate);
//    }
//
//    public List<FeedbackItem> viewRecentFeedback(int foodItemId) {
//        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);
//        Timestamp timestampThreeDaysAgo = Timestamp.valueOf(threeDaysAgo);
//
//        String query = "SELECT * FROM Feedback WHERE foodItemId = ? AND date >= ?";
//        ResultSet resultSet = dbOperationsHandler.executeQuery(query, foodItemId, timestampThreeDaysAgo);
//
//        List<FeedbackItem> feedbackList = new ArrayList<>();
//        try {
//            while (resultSet != null && resultSet.next()) {
//                FeedbackItem feedback = new FeedbackItem(
//                        resultSet.getInt("feedbackId"),
//                        resultSet.getInt("foodItemId"),
//                        resultSet.getFloat("rating"),
//                        resultSet.getString("comments"),
//                        resultSet.getTimestamp("date")
//                );
//                feedbackList.add(feedback);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return feedbackList;
//    }
}

package com.cafeteria.server.db;

import com.cafeteria.server.feedback.DiscardItemFeedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDiscardItemFeedbackService {

    public List<DiscardItemFeedback> getAllFeedbacks() {
        String query = "SELECT * FROM DiscardItemFeedback";
        List<DiscardItemFeedback> feedbacks = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet != null && resultSet.next()) {
                DiscardItemFeedback feedback = new DiscardItemFeedback(
                        resultSet.getInt("feedbackID"),
                        resultSet.getString("userId"),
                        resultSet.getInt("discardID"),
                        resultSet.getString("Q1"),
                        resultSet.getString("Q2"),
                        resultSet.getString("Q3"));
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

    public boolean addDiscardItemFeedback(DiscardItemFeedback feedback) {
        String checkQuery = "SELECT COUNT(*) FROM DiscardItemFeedback WHERE discardID = ? AND userId = ?";
        String insertQuery = "INSERT INTO DiscardItemFeedback (userId, discardID, Q1, Q2, Q3) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getInstance().getConnection()) {
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, feedback.getDiscardID());
                checkStmt.setString(2, feedback.getUserId());

                try (ResultSet resultSet = checkStmt.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        System.err.println("Feedback with the same discardID and userId already exists.");
                        return false;
                    }
                }
            }

            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setString(1, feedback.getUserId());
                insertStmt.setInt(2, feedback.getDiscardID());
                insertStmt.setString(3, feedback.getQuestionOne());
                insertStmt.setString(4, feedback.getQuestionTwo());
                insertStmt.setString(5, feedback.getQuestionThree());

                int rowsAffected = insertStmt.executeUpdate();
                return rowsAffected == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

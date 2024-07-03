package com.cafeteria.server.db;
import com.cafeteria.server.feedback.FeedbackItem;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
public class DBFeedbackMenueService {

   // private Connection connection;
    public DBFeedbackMenueService() throws SQLException {
        //this.connection = DatabaseConnector.getConnection();
    }

        public List<FeedbackItem> getFeedbackFromLast30Days() {
            String query = "SELECT * FROM Feedback WHERE date >= ?";
            List<FeedbackItem> feedbackItems = new ArrayList<>();
            LocalDateTime thirtyDaysAgo = LocalDateTime.now().minus(30, ChronoUnit.DAYS);
            Timestamp thirtyDaysAgoTimestamp = Timestamp.valueOf(thirtyDaysAgo);

            try (Connection connection = DatabaseConnector.getInstance().getConnection();
                    PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setTimestamp(1, thirtyDaysAgoTimestamp);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int feedbackId = rs.getInt("feedbackId");
                        int foodItemId = rs.getInt("foodItemId");
                        float rating = rs.getFloat("rating");
                        String comments = rs.getString("comment");
                        String userID = rs.getString("userID");
                        Timestamp date = rs.getTimestamp("date");

                        FeedbackItem feedbackItem = new FeedbackItem(feedbackId, foodItemId, rating, comments, userID,date);
                        feedbackItems.add(feedbackItem);
                    }
                }
                //DatabaseConnector.getInstance().closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return feedbackItems;
        }

        public boolean insertFeedback(FeedbackItem item) {
            String query = "INSERT INTO Feedback (feedbackId, foodItemId, rating, comment, userID) VALUES (?, ?, ?, ?, ?)";

            try (Connection connection = DatabaseConnector.getInstance().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query))
            {
                stmt.setInt(1, item.getFeedbackId());
                stmt.setInt(2, item.getFoodItemId());
                stmt.setFloat(3, item.getRating());
                stmt.setString(4, item.getComments());
                stmt.setString(5, item.getUserID());
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected == 1;

            }
            catch (SQLException e)
            {
                e.printStackTrace();
                return false;
            }

        }

}

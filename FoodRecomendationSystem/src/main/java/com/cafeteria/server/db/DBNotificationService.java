package com.cafeteria.server.db;

import com.cafeteria.server.notification.Notification;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBNotificationService {

    public DBNotificationService() throws SQLException {
        // Initialization if necessary
    }

    public List<Notification> viewRecentNotifications() {
        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(2);
        Timestamp timestampThreeDaysAgo = Timestamp.valueOf(threeDaysAgo);

        String query = "SELECT * FROM Notification WHERE date >= ?";
        List<Notification> notifications = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setTimestamp(1, timestampThreeDaysAgo);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet != null && resultSet.next()) {
                Notification notification = new Notification(
                        resultSet.getInt("notificationId"),
                        resultSet.getString("message"),
                        resultSet.getTimestamp("date")
                );
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    public boolean sendNotification(String message) {
        String query = "INSERT INTO Notification (message, date) VALUES (?, ?)";
        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now());

        try (Connection connection = DatabaseConnector.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, message);
            stmt.setTimestamp(2, currentDate);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

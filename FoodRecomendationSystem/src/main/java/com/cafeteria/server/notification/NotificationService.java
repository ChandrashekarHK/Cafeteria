package com.cafeteria.server.notification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {
//    private DBOperationsHandler dbOperationsHandler;
//
//    public NotificationService() throws SQLException {
//        this.dbOperationsHandler = new DBOperationsHandler();
//    }
//
//    public List<Notification> viewRecentNotifications() {
//        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);
//        Timestamp timestampThreeDaysAgo = Timestamp.valueOf(threeDaysAgo);
//
//        String query = "SELECT * FROM Notifications WHERE date >= ?";
//        ResultSet resultSet = dbOperationsHandler.executeQuery(query, timestampThreeDaysAgo);
//        List<Notification> notifications = new ArrayList<>();
//        try {
//            while (resultSet != null && resultSet.next()) {
//                Notification notification = new Notification(
//                        resultSet.getInt("notificationId"),
//                        resultSet.getString("message"),
//                        resultSet.getTimestamp("date")
//                );
//                notifications.add(notification);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return notifications;
//    }
//
//    public ResultSet sendNotification(String message) throws SQLException {
//        String query = "INSERT INTO Notifications (message, date) VALUES (?, ?)";
//        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now());
//        return dbOperationsHandler.executeQuery(query, message, currentDate);
//
//    }
}

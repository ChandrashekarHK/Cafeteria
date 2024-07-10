package com.cafeteria.server.notification;

import java.sql.SQLException;

import com.cafeteria.server.db.DBNotificationService;
import java.util.List;

public class NotificationService {

    private DBNotificationService dbNotificationService;

    public NotificationService() throws SQLException {
        this.dbNotificationService = new DBNotificationService();

    }

    public List<Notification> viewRecentNotifications()
    {
        List<Notification> notificationList = dbNotificationService.viewRecentNotifications();
        return notificationList;
    }

    public boolean sendNotification(String message) throws SQLException {

        return dbNotificationService.sendNotification(message);

    }
}
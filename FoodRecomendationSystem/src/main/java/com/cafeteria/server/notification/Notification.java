package com.cafeteria.server.notification;

import java.sql.Timestamp;

public class Notification {
    private int notificationId;
    private String message;
    private Timestamp date;

    public Notification(int notificationId, String message, Timestamp date) {
        this.notificationId = notificationId;
        this.message = message;
        this.date = date;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getDate() {
        return date;
    }

}

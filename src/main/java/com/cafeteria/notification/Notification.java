package com.cafeteria.notification;

import java.sql.Timestamp;

public class Notification {
    private int id;
    private String message;
    private Timestamp date;

    public Notification(int id, String message, Timestamp date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}

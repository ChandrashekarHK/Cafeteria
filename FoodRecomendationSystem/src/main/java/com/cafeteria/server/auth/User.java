package com.cafeteria.server.auth;

public class User {

    private String password;
    private String name;
    private String role;
    private String userID;

    public User(String userId, String name, String password, String role) {
        this.userID = userId;
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }
}

package com.cafeteria.server.auth;

import com.cafeteria.server.db.DBUserService;

import java.sql.SQLException;

public class UserService {
    private DBUserService dbUserService;

    public UserService() throws SQLException {
        this.dbUserService = new DBUserService();
    }

    public User getUserById(String userId) throws SQLException {
        return dbUserService.getUserbyID(userId);
    }

}

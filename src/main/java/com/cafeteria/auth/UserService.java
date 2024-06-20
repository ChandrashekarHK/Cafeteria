package com.cafeteria.auth;
import com.cafeteria.auth.User;
import com.cafeteria.db.DBUserService;

import java.sql.SQLException;

public class UserService
{
    private DBUserService dbUserService;
    public UserService() throws SQLException {
        this.dbUserService =new DBUserService();
    }

    public User getUserById(String userId) throws SQLException
    {
        return dbUserService.getUserbyID(userId);
    }


}

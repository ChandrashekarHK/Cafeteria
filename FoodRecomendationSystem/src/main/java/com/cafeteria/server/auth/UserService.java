package com.cafeteria.server.auth;
import com.cafeteria.server.db.DBUserService;

import java.sql.SQLException;

public class UserService
{
    private DBUserService dbUserService;
    private  String userID;
    private  String pawssword;

    public UserService() throws SQLException {
        this.dbUserService =new DBUserService();
    }
    public void getUserInput()
    {

     }
     public void perfoermLogin()
     {

     }
    public User getUserById(String userId) throws SQLException
    {
        return dbUserService.getUserbyID(userId);
    }
    public void logUserUserLogin()
    {

    }
    public void logUserLogOut()
    {

    }

}
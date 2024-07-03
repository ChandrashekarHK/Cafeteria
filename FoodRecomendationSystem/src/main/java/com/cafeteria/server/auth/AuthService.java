package com.cafeteria.server.auth;

//import com.cafeteria.server.auth.User;
//import com.cafeteria.server.auth.UserService;

import java.sql.SQLException;

public class AuthService {

    private UserService userService;

    public AuthService() throws SQLException {
        this.userService = new UserService();
    }

    public boolean login(String userID,String password) throws SQLException
    {
          String inputedUserID = userID;
          String inputedPassword = password;

          User user = userService.getUserById(inputedUserID);

          if(user !=null && inputedPassword.equals(user.getPassword()))
          {
              return true;
          }
          else {
              return false;
          }

    }
}

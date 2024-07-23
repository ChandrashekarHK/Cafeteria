package com.cafeteria.server.Controllers;

import com.cafeteria.server.auth.AuthService;
import com.cafeteria.server.auth.User;
import com.cafeteria.server.auth.UserService;
import org.json.JSONObject;
import java.sql.SQLException;

public class UserController {
    private AuthService authService;
    private UserService userService;

    public UserController() throws SQLException {
        this.authService = new AuthService();
        this.userService = new UserService();
    }

    public JSONObject handleUserRequest(JSONObject jsonRequest) throws SQLException {
        String action = jsonRequest.getString("action");
        JSONObject jsonResponse = new JSONObject();

        switch (action) {
            case "AUTHENTICATION":
                String userId = jsonRequest.getString("userId");
                String password = jsonRequest.getString("password");
                boolean isAuthenticated = authService.login(userId, password);
                jsonResponse.put("success", isAuthenticated);
                if (!isAuthenticated) {
                    jsonResponse.put("error", "Invalid credentials");
                } else {
                    User user = userService.getUserById(userId);
                    jsonResponse.put("role", user.getRole());
                }
                break;
            default:
                jsonResponse.put("success", false);
                jsonResponse.put("error", "Invalid user action");
                break;
        }

        return jsonResponse;
    }
}

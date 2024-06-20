package com.cafeteria.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cafeteria.auth.User;

public class DBUserService {
    private Connection connection;
    public DBUserService() throws SQLException {
        this.connection = DatabaseConnector.getConnection();
    }

    public User getUserbyID(String userId) throws SQLException {
        String query = "SELECT * FROM [User] WHERE userId = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    return new User(userId, name, password, role);
                } else {
                    return null;
                }
            }
    }
}


}

package com.cafeteria.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cafeteria.server.auth.User;

public class DBUserService {
    //private Connection connection;
    public DBUserService() throws SQLException {
       // this.connection = DatabaseConnector.getInstance().getConnection();
    }

    public User getUserbyID(String userId) throws SQLException {
        String query = "SELECT * FROM [User] WHERE userId = ?";
        try ( Connection connection = DatabaseConnector.getInstance().getConnection();
              PreparedStatement stmt = connection.prepareStatement(query))
        {
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

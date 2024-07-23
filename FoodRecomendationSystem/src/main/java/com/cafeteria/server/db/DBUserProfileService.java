package com.cafeteria.server.db;

import com.cafeteria.server.auth.UserProfile;

import java.sql.*;
import java.time.LocalDateTime;

public class DBUserProfileService {

    public boolean saveOrUpdateUserProfile(UserProfile profile) {
        String selectQuery = "SELECT COUNT(*) FROM UserProfile WHERE userId = ?";
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
            selectStmt.setString(1, profile.getUserId());
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return updateProfile(profile, connection);
                } else {
                    return saveProfile(profile, connection);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean saveProfile(UserProfile profile, Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO UserProfile (userId, foodType, spiceLevel, sweetness, saltiness, cuisineType, createdAt) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
            stmt.setString(1, profile.getUserId());
            stmt.setString(2, profile.getFoodType());
            stmt.setInt(3, profile.getSpiceLevel());
            stmt.setInt(4, profile.getSweetness());
            stmt.setInt(5, profile.getSaltiness());
            stmt.setString(6, profile.getCuisineType());
            stmt.setTimestamp(7, Timestamp.valueOf(profile.getCreatedAt()));
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        }
    }

    private boolean updateProfile(UserProfile profile, Connection connection) throws SQLException {
        String updateQuery = "UPDATE UserProfile SET  foodType = ?, spiceLevel = ?, sweetness = ?, saltiness = ?, cuisineType = ? "
                +
                "WHERE userId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            stmt.setString(1, profile.getFoodType());
            stmt.setInt(2, profile.getSpiceLevel());
            stmt.setInt(3, profile.getSweetness());
            stmt.setInt(4, profile.getSaltiness());
            stmt.setString(5, profile.getCuisineType());
            stmt.setString(6, profile.getUserId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        }
    }

    public UserProfile getUserProfileByUserId(String userId) {
        String query = "SELECT * FROM UserProfile WHERE userId = ?";
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int profileID = rs.getInt("profileID");
                    String foodType = rs.getString("foodType");
                    int spiceLevel = rs.getInt("spiceLevel");
                    int sweetness = rs.getInt("sweetness");
                    int saltiness = rs.getInt("saltiness");
                    String cuisineType = rs.getString("cuisineType");
                    LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();
                    return new UserProfile(profileID, userId, foodType, spiceLevel, sweetness, saltiness, cuisineType,
                            createdAt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

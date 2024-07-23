package com.cafeteria.server.db;

import com.cafeteria.server.menu.RolloutMenuItem;

import java.sql.*;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBRolloutMenuService {

    public DBRolloutMenuService() {

    }

    public boolean addRolloutMenuItem(RolloutMenuItem rolloutMenuItem) {
        String query = "INSERT INTO RollOutItem (RolloutID, foodItemId, name, Date) VALUES (?, ?, ?,?)";
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rolloutMenuItem.getRolloutID());
            stmt.setInt(2, rolloutMenuItem.getFoodId());
            stmt.setString(3, rolloutMenuItem.getName());
            stmt.setDate(4, sqlDate);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<RolloutMenuItem> readAllRolloutMenuItems() {
        String query = "SELECT * FROM RollOutItem";
        List<RolloutMenuItem> rolloutMenuItems = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int rolloutID = rs.getInt("RolloutID");
                int foodItemId = rs.getInt("foodItemId");
                String name = rs.getString("name");
                LocalDateTime date = rs.getTimestamp("Date").toLocalDateTime();
                rolloutMenuItems.add(new RolloutMenuItem(foodItemId, name, rolloutID, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rolloutMenuItems;
    }

    public boolean deleteAllRolloutMenuItems() {
        String query = "DELETE FROM RollOutItem";
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isRolloutItemIdPresent(int rolloutId) {
        String query = "SELECT COUNT(*) FROM RollOutItem WHERE RolloutID = ?";
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rolloutId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRolloutMenuItem(int rolloutID) {
        String query = "DELETE FROM RollOutItem WHERE RolloutID = ?";
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rolloutID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

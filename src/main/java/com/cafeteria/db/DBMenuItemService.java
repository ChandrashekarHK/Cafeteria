package com.cafeteria.db;

import  com.cafeteria.menu.MenuItem;
import com.cafeteria.db.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DBMenuItemService {
    private Connection connection;

    public DBMenuItemService() throws SQLException {
        this.connection = DatabaseConnector.getConnection();
    }


    public boolean addMenuItem(MenuItem menuItem) throws SQLException {
        String query = "INSERT INTO MenuItem (foodItemId, name, price, availability) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, menuItem.getId());
            stmt.setString(2, menuItem.getName());
            stmt.setBigDecimal(3, menuItem.getPrice());
            stmt.setBoolean(4, menuItem.getAvailability());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MenuItem> readAllMenuItems() {
        String query = "SELECT * FROM MenuItem";
        List<MenuItem> menuItems = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int foodItemId = rs.getInt("foodItemId");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                boolean availability = rs.getBoolean("availability");
                menuItems.add(new MenuItem(foodItemId, name, price, availability));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    public boolean updateMenuItem(MenuItem menuItem) {
        String query = "UPDATE MenuItem SET name = ?, price = ?, availability = ? WHERE foodItemId = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, menuItem.getName());
            stmt.setBigDecimal(2, menuItem.getPrice());
            stmt.setBoolean(3, menuItem.getAvailability());
            stmt.setInt(4, menuItem.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMenuItem(int foodItemId) {
        String query = "BEGIN TRANSACTION;" +
                "DELETE FROM Feedback WHERE foodItemId = ?;" +
                "DELETE FROM RollOutItem WHERE foodItemId = ?;" +
                "DELETE FROM Voting WHERE foodItemId = ?;" +
                "DELETE FROM DiscardMenu WHERE foodItemId = ?;" +
                "" +
                "DELETE FROM MenuItem WHERE foodItemId = ?;" +
                "" +
                "COMMIT";
        try ( PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, foodItemId);
            stmt.setInt(2, foodItemId);
            stmt.setInt(3, foodItemId);
            stmt.setInt(4, foodItemId);
            stmt.setInt(5, foodItemId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

package com.cafeteria.server.db;

import com.cafeteria.server.menu.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DBMenuItemService {

    public DBMenuItemService() {

    }

    public boolean addMenuItem(MenuItem menuItem) {

        String query = "INSERT INTO MenuItem (foodItemId, name, price, availability, cuisineType, spiceLevel, foodType, saltiness, sweetness, category) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, menuItem.getFoodItemID());
            preparedStatement.setString(2, menuItem.getName());
            preparedStatement.setBigDecimal(3, menuItem.getPrice());
            preparedStatement.setBoolean(4, menuItem.getAvailability());
            preparedStatement.setString(5, menuItem.getCuisineType());
            preparedStatement.setInt(6, menuItem.getSpiceLevel());
            preparedStatement.setString(7, menuItem.getFoodType());
            preparedStatement.setInt(8, menuItem.getSaltiness());
            preparedStatement.setInt(9, menuItem.getSweetness());
            preparedStatement.setString(10, menuItem.getCategory());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MenuItem> readAllMenuItems() {

        String query = "SELECT * FROM MenuItem";
        List<MenuItem> menuItems = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int foodItemId = resultSet.getInt("foodItemId");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                boolean availability = resultSet.getBoolean("availability");
                String cuisineType = resultSet.getString("cuisineType");
                int spiceLevel = resultSet.getInt("spiceLevel");
                String foodType = resultSet.getString("foodType");
                int saltiness = resultSet.getInt("saltiness");
                int sweetness = resultSet.getInt("sweetness");
                String category = resultSet.getString("category");
                MenuItem menuItem = new MenuItem(foodItemId, name, price, availability, cuisineType, spiceLevel,
                        foodType, saltiness, sweetness, category);
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    public boolean updateMenuItem(MenuItem menuItem) {
        String query = "UPDATE MenuItem SET name = ?, price = ?, availability = ?, cuisineType = ?, spiceLevel = ?, foodType = ?, saltiness = ?, sweetness = ?, category = ? WHERE foodItemId = ?";
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, menuItem.getName());
            stmt.setBigDecimal(2, menuItem.getPrice());
            stmt.setBoolean(3, menuItem.getAvailability());
            stmt.setString(4, menuItem.getCuisineType());
            stmt.setInt(5, menuItem.getSpiceLevel());
            stmt.setString(6, menuItem.getFoodType());
            stmt.setInt(7, menuItem.getSaltiness());
            stmt.setInt(8, menuItem.getSweetness());
            stmt.setString(9, menuItem.getCategory());
            stmt.setInt(10, menuItem.getFoodItemID());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isFoodItemIdPresent(int foodItemId) {
        String query = "SELECT COUNT(*) FROM MenuItem WHERE foodItemId = ?";
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, foodItemId);
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

    public boolean deleteMenuItem(int foodItemId) {
        String query = "DELETE FROM MenuItem WHERE foodItemId = ?";
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, foodItemId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MenuItem getMenuItemById(int foodItemId) {
        String query = "SELECT * FROM MenuItem WHERE foodItemId = ?";
        try (Connection connection = DatabaseConnector.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, foodItemId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    BigDecimal price = rs.getBigDecimal("price");
                    boolean availability = rs.getBoolean("availability");
                    String cuisineType = rs.getString("cuisineType");
                    int spiceLevel = rs.getInt("spiceLevel");
                    String foodType = rs.getString("foodType");
                    int saltiness = rs.getInt("saltiness");
                    int sweetness = rs.getInt("sweetness");
                    String category = rs.getString("category");

                    return new MenuItem(foodItemId, name, price, availability, cuisineType, spiceLevel, foodType,
                            saltiness, sweetness, category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

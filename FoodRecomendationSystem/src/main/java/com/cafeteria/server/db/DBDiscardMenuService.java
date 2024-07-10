package com.cafeteria.server.db;

import com.cafeteria.server.discardMenuService.DiscardMenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDiscardMenuService {


    public DBDiscardMenuService() {

    }
    public void addDiscardItem(DiscardMenuItem discardItem) throws SQLException {
        String sql = "INSERT INTO DiscardFoodItem (foodItemId, foodName, averageRating, discardDate) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, discardItem.getFoodId());
            pstmt.setString(2, discardItem.getName());
            pstmt.setDouble(3, discardItem.getAverageRating());
            pstmt.setTimestamp(4, discardItem.getDate());

            pstmt.executeUpdate();
        }
    }

    public List<DiscardMenuItem> getAllDiscardItems(){
        String sql = "SELECT * FROM DiscardFoodItem";
        List<DiscardMenuItem> discardItems = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getInstance(). getConnection();
                Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DiscardMenuItem discardItem = new DiscardMenuItem();
                discardItem.setDiscardId(rs.getInt("discardId"));
                discardItem.setFoodId(rs.getInt("foodItemId"));
                discardItem.setName(rs.getString("foodName"));
                discardItem.setAverageRating(rs.getDouble("averageRating"));
                discardItem.setDate(rs.getTimestamp("discardDate"));

                discardItems.add(discardItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discardItems;
    }
}

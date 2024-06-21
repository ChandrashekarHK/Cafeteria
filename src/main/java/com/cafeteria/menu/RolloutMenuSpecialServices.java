package com.cafeteria.menu;

import com.cafeteria.db.DBOperationsHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolloutMenuSpecialServices {
    private DBOperationsHandler dbOperationsHandler;

    public RolloutMenuSpecialServices() throws SQLException {
        this.dbOperationsHandler = new DBOperationsHandler();
    }

    public ResultSet addRolloutMenuItem(RolloutMenuItem item) {
        String query = "INSERT INTO RollOutItems (foodItemId, name, price, vote) VALUES (?, ?, ?, ?)";
        return dbOperationsHandler.executeQuery(query, item.getFoodItemId(), item.getName(), item.getPrice(), item.getVote());

    }

    public ResultSet updateRolloutMenuItem(RolloutMenuItem item){
        String query = "UPDATE RollOutItems SET name = ?, price = ?, vote = ? WHERE foodItemId = ?";
       return  dbOperationsHandler.executeQuery(query, item.getName(), item.getPrice(), item.getVote(), item.getFoodItemId());

    }

    public ResultSet deleteRolloutMenuItem(int foodItemId) {
            String query = "DELETE FROM RollOutItems WHERE foodItemId = ?";
        return dbOperationsHandler.executeQuery(query, foodItemId);

    }
}

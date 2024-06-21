package com.cafeteria.menu;


import com.cafeteria.db.DBOperationsHandler;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RolloutMenuCommonService {
    private DBOperationsHandler dbOperationsHandler;

    public RolloutMenuCommonService() throws SQLException {
        this.dbOperationsHandler = new DBOperationsHandler();
    }

    public List<RolloutMenuItem> getRolloutMenu() {
        String query = "SELECT * FROM RollOutItems";
        ResultSet resultSet = dbOperationsHandler.executeQuery(query);
        List<RolloutMenuItem> menu = new ArrayList<>();
        try {
            while (resultSet != null && resultSet.next()) {
                RolloutMenuItem item = new RolloutMenuItem(
                        resultSet.getInt("foodItemId"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getInt("vote")
                );
                menu.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    public ResultSet voteForRolloutMenuItem(int id, float vote,int userID) {
        String query = "INSERT INTO Voting (foodItemId, vote, voterUserId,date) VALUES (?, ?, ?,?)";
        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now());
        return  dbOperationsHandler.executeQuery(query,id,vote,userID,currentDate);
    }


//    public void displayRolloutMenu() {
//        List<RolloutMenuItem> menu = getRolloutMenu();
//        System.out.println("Rollout Menu:");
//        for (RolloutMenuItem item : menu) {
//            System.out.println(item);
//        }
//    }
}


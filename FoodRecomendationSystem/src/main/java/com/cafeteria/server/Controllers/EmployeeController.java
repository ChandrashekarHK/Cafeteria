package com.cafeteria.server.Controllers;

import com.cafeteria.server.feedback.VotingItem;
import com.cafeteria.server.menu.MenuItem;

import com.cafeteria.server.menu.RolloutMenuItem;

import com.cafeteria.server.userOperations.EmployeeService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeeController {


    private EmployeeService employeeService;


    public EmployeeController() throws SQLException {
        this.employeeService  = new EmployeeService();

    }
    public JSONObject handleEmploeeActions(JSONObject jsonRequest) throws IOException, SQLException {
        String chefAction = jsonRequest.getString("employeeAction");
        JSONObject jsonResponse = new JSONObject();
        List<RolloutMenuItem> rolloutMenuItemList = new ArrayList<>();

        switch (chefAction) {
            case "VIEW_MENU_ITEMS":
                jsonResponse= employeeService.viewMenu();
                break;

            case "VIEW_RECOMMENDED_MENU":
                int numberOfItems = jsonRequest.getInt("numberOfFoodItems");
                boolean sentimentNecessity = false;//jsonRequest.getBoolean("sentimentNecessity");
                jsonResponse = employeeService.viewRecommendations(numberOfItems,sentimentNecessity);
                break;

            case "VOTE_ROLLOUT_ITEMS":
                int foodId = jsonResponse.getInt("foodId");
                String userId = jsonResponse.getString("userId");
                int vote = jsonResponse.getInt("vote");
                Timestamp date = Timestamp.valueOf(jsonResponse.getString("date"));
                int rolloutId = jsonResponse.getInt("rolloutId");
                int autoIncerementedVoteID =0;
                VotingItem voteItem = new VotingItem(autoIncerementedVoteID,foodId,vote,userId,date,rolloutId);
                jsonResponse= employeeService.castVote(voteItem);

                break;

            case "VIEW_ROLLOUT_MENU":
                //jsonResponse.put("success", true);
                jsonResponse = employeeService.viewRolloutMenu();

                break;
            case "VIEW_NOTIFICATIONS":
                jsonResponse = employeeService.viewNotification();
                break;

            default:
                jsonResponse.put("success", false);
                jsonResponse.put("error", "Invalid chef action");
                break;
        }

        return jsonResponse;
    }



}

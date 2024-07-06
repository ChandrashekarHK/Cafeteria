package com.cafeteria.server.Controllers;

import com.cafeteria.server.feedback.FeedbackItem;
import com.cafeteria.server.feedback.VotingItem;

import com.cafeteria.server.menu.RolloutMenuItem;

import com.cafeteria.server.userOperations.EmployeeService;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {


    private EmployeeService employeeService;


    public EmployeeController() throws SQLException {
        this.employeeService  = new EmployeeService();

    }

    public JSONObject handleEmployeeActions(JSONObject jsonRequest) throws IOException, SQLException {
        String chefAction = jsonRequest.getString("employeeAction");
        JSONObject jsonResponse = new JSONObject();
        List<RolloutMenuItem> rolloutMenuItemList = new ArrayList<>();
        int foodId;
        String userId;
        int vote;
        Timestamp date;
        int rolloutId ;
        int autoIncerementedID =0;
        float rating;

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
                 foodId = jsonRequest.getInt("foodId");
                 userId = jsonRequest.getString("userId");
                 vote = jsonRequest.getInt("vote");
                 date = Timestamp.valueOf(jsonRequest.getString("date"));
                 rolloutId = jsonRequest.getInt("rolloutId");
                 autoIncerementedID =0;
                VotingItem voteItem = new VotingItem(autoIncerementedID,foodId,vote,userId,date,rolloutId);
                jsonResponse= employeeService.castVote(voteItem);

                break;

            case "VIEW_ROLLOUT_MENU":
                jsonResponse = employeeService.viewRolloutMenu();

                break;
            case "VIEW_NOTIFICATIONS":
                jsonResponse = employeeService.viewNotification();
                break;
            case "PROVIDE_FEEDBACK":
                foodId = jsonRequest.getInt("foodId");
                String comment= jsonRequest.getString("comment");
                rating = jsonRequest.getFloat("rating");
                date = Timestamp.valueOf(jsonRequest.getString("date"));
                userId = jsonRequest.getString("userId");
                FeedbackItem feedbackItem = new FeedbackItem(autoIncerementedID,foodId,rating,comment,userId,date);
                jsonResponse = employeeService.addFeedback(feedbackItem);
                break;

            default:
                jsonResponse.put("success", false);
                jsonResponse.put("error", "Invalid Employee action");
                break;
        }

        return jsonResponse;
    }



}

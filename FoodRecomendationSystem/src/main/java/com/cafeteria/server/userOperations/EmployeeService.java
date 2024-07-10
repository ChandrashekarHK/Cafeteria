package com.cafeteria.server.userOperations;

import com.cafeteria.server.auth.UserProfile;
import com.cafeteria.server.db.DBUserProfileService;
import com.cafeteria.server.feedback.*;
import com.cafeteria.server.menu.MenuItem;
import com.cafeteria.server.menu.MenuService;
import com.cafeteria.server.menu.RolloutMenuItem;
import com.cafeteria.server.notification.Notification;
import com.cafeteria.server.notification.NotificationService;
import com.cafeteria.server.recommendation.RecommendationEngine;
import com.cafeteria.server.menu.RolloutService;

import org.json.JSONObject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {

    private RecommendationEngine recommendationEngine;
    private VoterService voterService;
    private MenuService menueService;
    private RolloutService rolloutService;
    private NotificationService notificationService;
    private FeedbackService feedbackService;
    private DBUserProfileService dbUserProfileService;
    private DiscardItemFeedbackService discardItemFeedbackService;
    public EmployeeService() throws SQLException {


        this.rolloutService = new RolloutService();
        this.menueService = new MenuService();
        this.recommendationEngine = new RecommendationEngine();
        this.voterService = new VoterService();
        this.notificationService = new NotificationService();
        this.feedbackService = new FeedbackService();
        this.dbUserProfileService = new DBUserProfileService();
        this.discardItemFeedbackService = new DiscardItemFeedbackService();
    }

    JSONObject jsonResponse = new JSONObject();

    public JSONObject viewRecommendations(int topN, boolean includeSentiment) {
        jsonResponse = recommendationEngine.generateRecommendation(topN,includeSentiment);
        jsonResponse.put("success", true);
        return jsonResponse;

    }

    public JSONObject castVote(VotingItem voteItem)
    {
        if(voterService.castvote(voteItem))
        {
            return jsonResponse.put("success", true);
        }
        else {
            jsonResponse.put("error", "Not able add your vote Please try again  ");
            return jsonResponse.put("success", false);
        }

    }

    public JSONObject viewMenu() {
        JSONObject jsonResponse = new JSONObject();
        List<MenuItem> menu = menueService.getMenu();
        if( menu != null && !menu.isEmpty()) {
            jsonResponse.put("menu", menu);
            return jsonResponse.put("success", true);
        }
        else {
            jsonResponse.put("error", "Not able get the Food Menu Now Please try again  ");
            return jsonResponse.put("success", false);
        }
    }

    public JSONObject viewRecommendedRolloutMenu(String userID) {
        JSONObject jsonResponse = new JSONObject();
        List<RolloutMenuItem> rolloutMenu = rolloutService.getRecommendedRolloutMenu(userID);
        if( rolloutMenu != null && !rolloutMenu.isEmpty()) {
            jsonResponse.put("rolloutItems", rolloutMenu);
            return jsonResponse.put("success", true);
        }
        else {
            jsonResponse.put("error", "Not able get the  Recommended Rollout  Menu Now Please try again  ");
            return jsonResponse.put("success", false);
        }
    }


    public JSONObject viewNotification() {
            List<Notification> notificationList = notificationService.viewRecentNotifications();
           jsonResponse.put("notifications", notificationList);
          jsonResponse.put("success", true);
          return jsonResponse;
    }
    public JSONObject addFeedback(FeedbackItem feedbackItem) throws SQLException, IOException {
        if(feedbackService.giveFeedback(feedbackItem))
        {
            return jsonResponse.put("success", true);
        }
        else {
            jsonResponse.put("error", "Not able to get your feedback  Please try again  ");
            return jsonResponse.put("success", false);
        }
    }
    public JSONObject saveUserProfile(UserProfile userProfile) throws SQLException, IOException {
        if(dbUserProfileService.saveOrUpdateUserProfile(userProfile))
        {
            return jsonResponse.put("success", true);
        }
        else {
            jsonResponse.put("error", "Not able to Save your profile Please try again  ");
            return jsonResponse.put("success", false);
        }
    }
    public JSONObject addDiscardItemFeedback(DiscardItemFeedback feedbackItem) throws SQLException, IOException {
        if(discardItemFeedbackService.addDiscardItemFeedback(feedbackItem))
        {
            return jsonResponse.put("success", true);
        }
        else {
            jsonResponse.put("error", "you are already given feed back to that discard item");
            return jsonResponse.put("success", false);
        }
    }


}

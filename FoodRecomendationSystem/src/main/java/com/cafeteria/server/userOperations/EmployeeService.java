package com.cafeteria.server.userOperations;

import com.cafeteria.server.feedback.FeedbackItem;
import com.cafeteria.server.feedback.FeedbackService;
import com.cafeteria.server.menu.MenuItem;
import com.cafeteria.server.menu.MenuService;
import com.cafeteria.server.feedback.VoterService;
import com.cafeteria.server.feedback.VotingItem;
import com.cafeteria.server.menu.RolloutMenuItem;
import com.cafeteria.server.notification.Notification;
import com.cafeteria.server.notification.NotificationService;
import com.cafeteria.server.recommendation.RecommendationEngine;
import com.cafeteria.server.menu.RolloutService;

import org.json.JSONObject;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

public class EmployeeService {

    private RecommendationEngine recommendationEngine;
    private VoterService voterService;
    private MenuService menueService;
    private RolloutService rolloutService;
    private NotificationService notificationService;
    private FeedbackService feedbackService;

    public EmployeeService() throws SQLException {


        this.rolloutService = new RolloutService();
        this.menueService = new MenuService();
        this.recommendationEngine = new RecommendationEngine();
        this.voterService = new VoterService();
        this.notificationService = new NotificationService();
        this.feedbackService = new FeedbackService();
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
            return jsonResponse.put("success", false);
        }

    }

    public JSONObject viewMenu() {
        JSONObject jsonResponse = new JSONObject();
        List<MenuItem> menu = menueService.getMenu();
        jsonResponse.put("success", true);
        jsonResponse.put("menu", menu);
        return jsonResponse;
    }

    public JSONObject viewRolloutMenu() {
        JSONObject jsonResponse = new JSONObject();
        List<RolloutMenuItem> rolloutMenu = rolloutService.getRolloutMenu();
        jsonResponse.put("success", true);
        jsonResponse.put("rolloutItems", rolloutMenu);
        return jsonResponse;
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
            return jsonResponse.put("success", false);
        }
    }

}

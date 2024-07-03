package com.cafeteria.server.userOperations;

import com.cafeteria.server.feedback.VoterService;
import com.cafeteria.server.menu.MenuItem;
import com.cafeteria.server.menu.MenuService;
import com.cafeteria.server.menu.RolloutMenuItem;
import com.cafeteria.server.menu.RolloutService;
import com.cafeteria.server.notification.NotificationService;
import com.cafeteria.server.recommendation.RecommendationEngine;
import org.json.JSONObject;
import java.sql.SQLException;
import java.util.List;

public class ChefService {

    private VoterService voterService;
    private RecommendationEngine recommendationEngine;
    private MenuService menuService;
    private RolloutService rolloutService;
    private NotificationService notificationService;
    JSONObject jsonResponse = new JSONObject();
    public ChefService() throws SQLException {

        this.voterService = new VoterService();
        this.rolloutService = new RolloutService();
        this.recommendationEngine = new RecommendationEngine();
        this.menuService =new MenuService();
        this.notificationService = new NotificationService();

    }

    public JSONObject viewRecommendations(int topN, boolean includeSentiment) {
           jsonResponse = recommendationEngine.generateRecommendation(topN,includeSentiment);
           jsonResponse.put("success", true);
        return jsonResponse;
    }

    public JSONObject viewFinalVoteResult() {
        jsonResponse = voterService.getFinalVoteResult();
        jsonResponse.put("success", true);
        return jsonResponse;
    }
    public JSONObject viewMenu() {
        JSONObject jsonResponse = new JSONObject();
        List<MenuItem> menu = menuService.getMenu();
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
    public JSONObject rolloutNewItems(List<RolloutMenuItem> items) throws SQLException {

        if (rolloutService.deleteAllRolloutMenuItems())
        {
            boolean allAdded = true;
            for (RolloutMenuItem item : items) {
                if (!rolloutService.addRolloutMenuItem(item)) {
                    allAdded = false;
                    break;
                }
            }
            if (allAdded) {
                return jsonResponse.put("success", true);
            } else {
                return jsonResponse.put("success", false).put("error", "Failed to add all new items.");
            }
        } else {
            return jsonResponse.put("success", false).put("error", "Failed to delete existing items.");
        }
    }



    public JSONObject sendNotification(String message) throws SQLException {
        if(notificationService.sendNotification(message))
        {
            return jsonResponse.put("success", true);
        }
        else {
            return jsonResponse.put("success", false);
        }

    }
}

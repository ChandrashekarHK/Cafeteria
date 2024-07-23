
package com.cafeteria.server.menu;

import com.cafeteria.server.db.DBRolloutMenuService;
import com.cafeteria.server.recommendation.RecommendedRolloutService;

import java.util.List;

public class RolloutService {
    private DBRolloutMenuService dbRolloutMenuService;
    private RecommendedRolloutService recommendedRolloutService;

    public RolloutService() {
        this.dbRolloutMenuService = new DBRolloutMenuService();
        this.recommendedRolloutService = new RecommendedRolloutService();
    }

    public boolean addRolloutMenuItem(RolloutMenuItem item) {
        return dbRolloutMenuService.addRolloutMenuItem(item);
    }

    public boolean deleteAllRolloutMenuItems() {
        return dbRolloutMenuService.deleteAllRolloutMenuItems();
    }

    public boolean checkRolloutMenuItem(int rolloutID) {
        if (dbRolloutMenuService.isRolloutItemIdPresent(rolloutID)) {
            return true;
        } else {
            return false;
        }
    }

    public List<RolloutMenuItem> getRolloutMenu() {
        return dbRolloutMenuService.readAllRolloutMenuItems();
    }

    public List<RolloutMenuItem> getRecommendedRolloutMenu(String userId) {

        return recommendedRolloutService.getRecommendedRolloutMenu(userId);
    }
}

package com.cafeteria.server.recommendation;

import com.cafeteria.server.auth.UserProfile;
import com.cafeteria.server.db.DBUserProfileService;
import com.cafeteria.server.db.DBRolloutMenuService;
import com.cafeteria.server.menu.MenuItem;
import com.cafeteria.server.menu.MenuService;
import com.cafeteria.server.menu.RolloutMenuItem;

import java.util.List;
import java.util.ArrayList;

public class RecommendedRolloutService {
    private DBRolloutMenuService dbRolloutMenuService;
    private DBUserProfileService dbUserProfileService;
    private MenuService menuService;

    public RecommendedRolloutService() {
        this.dbRolloutMenuService = new DBRolloutMenuService();
        this.dbUserProfileService = new DBUserProfileService();
        this.menuService = new MenuService();
    }

    public List<RolloutMenuItem> getRecommendedRolloutMenu(String userId) {
        UserProfile userProfile = dbUserProfileService.getUserProfileByUserId(userId);
        if (userProfile == null) {
            throw new IllegalArgumentException("User profile not found for userId: " + userId);
        }

        List<RolloutMenuItem> rolloutMenuItems = dbRolloutMenuService.readAllRolloutMenuItems();

        return sortRolloutMenuItems(rolloutMenuItems, userProfile);
    }

    private int compareFoodItems(UserProfile userProfile, MenuItem food1, MenuItem food2) {
        int score1 = calculateMatchingScore(userProfile, food1);
        int score2 = calculateMatchingScore(userProfile, food2);
        return Integer.compare(score2, score1);
    }

    private List<RolloutMenuItem> sortRolloutMenuItems(List<RolloutMenuItem> rolloutMenuItems,
            UserProfile userProfile) {
        List<RolloutMenuItem> sortedRolloutMenuItems = new ArrayList<>(rolloutMenuItems);
        sortedRolloutMenuItems.sort((item1, item2) -> {
            MenuItem food1 = menuService.getMenuIteamByFoodId(item1.getFoodId());
            MenuItem food2 = menuService.getMenuIteamByFoodId(item2.getFoodId());
            return compareFoodItems(userProfile, food1, food2);
        });
        return sortedRolloutMenuItems;
    }

    private int calculateMatchingScore(UserProfile userProfile, MenuItem foodItem) {
        if (foodItem == null) {
            return 0;
        }

        int score = 0;

        if (userProfile.getFoodType().equals(foodItem.getFoodType())) {
            score += 5;
        }
        score += 5 - Math.abs(userProfile.getSpiceLevel() - foodItem.getSpiceLevel());
        score += 5 - Math.abs(userProfile.getSweetness() - foodItem.getSweetness());
        score += 5 - Math.abs(userProfile.getSaltiness() - foodItem.getSaltiness());

        if (userProfile.getCuisineType().equals(foodItem.getCuisineType())) {
            score += 5;
        }

        return score;
    }

}

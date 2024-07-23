package com.cafeteria.server.Controllers;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cafeteria.server.menu.MenuItem;
import com.cafeteria.server.menu.MenuService;
import com.cafeteria.server.menu.RolloutMenuItem;
import com.cafeteria.server.userOperations.ChefService;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChefController {
    private ChefService chefService;
    private MenuService menuService;

    public ChefController() throws SQLException {
        this.chefService = new ChefService();
        this.menuService = new MenuService();
    }

    public JSONObject handleChefActions(JSONObject jsonRequest) throws SQLException {
        String chefAction = jsonRequest.getString("chefAction");
        JSONObject jsonResponse = new JSONObject();
        List<RolloutMenuItem> rolloutMenuItemList = new ArrayList<>();

        switch (chefAction) {
            case "VIEW_MENU_ITEMS":
                jsonResponse = chefService.viewMenu();
                break;

            case "VIEW_RECOMMENDED_MENU":
                int numberOfItems = jsonRequest.getInt("numberOfFoodItems");
                boolean sentimentNecessity = false;
                jsonResponse = chefService.viewRecommendations(numberOfItems, sentimentNecessity);
                break;

            case "ADD_TO_ROLLOUT_MENU":
                numberOfItems = jsonRequest.getInt("numberOfItems");
                JSONArray items = jsonRequest.getJSONArray("items");
                LocalDateTime rolloutDate = LocalDateTime.now();

                for (int itemIndex = 0; itemIndex < numberOfItems; itemIndex++) {
                    JSONObject item = items.getJSONObject(itemIndex);
                    int foodId = item.getInt("foodId");
                    int rolloutID = new Random().nextInt((500 - 200) + 1) + 100;
                    if (menuService.checkFoodId(foodId)) {
                        MenuItem menuItem = menuService.getMenuIteamByFoodId(foodId);
                        RolloutMenuItem rolloutMenuItem = new RolloutMenuItem(menuItem.getFoodItemID(),
                                menuItem.getName(), rolloutID, rolloutDate);
                        rolloutMenuItemList.add(rolloutMenuItem);
                    } else {
                        jsonResponse.put("message", foodId);
                    }
                }

                jsonResponse = chefService.rolloutNewItems(rolloutMenuItemList);

                break;

            case "VIEW_ROLLOUT_MENU":
                jsonResponse = chefService.viewRolloutMenu();
                break;

            case "VIEW_FINAL_VOTING":
                jsonResponse = chefService.viewFinalVoteResult();
                break;

            case "SEND_NOTIFICATION":
                String message = jsonRequest.getString("message");
                jsonResponse = chefService.sendNotification(message);
                break;

            case "VIEW_DISCARD_MENU_ITEMS":
                jsonResponse = chefService.viewDiscardMenu();
                break;
            case "VIEW_DISCARD_ITEM_FEEDBACK":
                jsonResponse = chefService.viewDiscardFeedbackMenu();
                break;

            default:
                jsonResponse.put("success", false);
                jsonResponse.put("error", "Invalid chef action");
                break;
        }

        return jsonResponse;
    }

}

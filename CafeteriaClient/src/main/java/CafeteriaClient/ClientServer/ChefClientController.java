package CafeteriaClient.ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import CafeteriaClient.ClientServer.Constants.ChefActions;
import CafeteriaClient.utils.ConsolePrintUtils;
import CafeteriaClient.utils.ConsoleReadUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChefClientController {

    static void handleChefActions(PrintWriter writer, BufferedReader reader) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Chef Actions:");
            for (ChefActions action : ChefActions.values()) {
                System.out.println(action.getValue() + ". " + action.getDescription());
            }

            int choice = ConsoleReadUtils.getIntInput("Enter your choice: ");

            ChefActions action;
            try {
                action = ChefActions.fromValue(choice);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            JSONObject jsonRequest = createJsonRequest(action);
            if (jsonRequest == null) {
                if (action == ChefActions.LOGOUT) {
                    exit = true;
                } else {
                    System.out.println("Invalid choice.");
                }
                continue;
            }

            sendRequest(writer, jsonRequest);
            handleResponse(reader, action);
        }
    }

    private static JSONObject createJsonRequest(ChefActions action) {
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("action", "CHEF_ACTION");

        switch (action) {
            case VIEW_MENU_ITEMS:
                jsonRequest.put("chefAction", "VIEW_MENU_ITEMS");
                break;
            case VIEW_RECOMMENDED_MENU:
                jsonRequest.put("chefAction", "VIEW_RECOMMENDED_MENU");
                int numberOfFoodItems = ConsoleReadUtils.getIntInput("Enter number of items you want to get Recommendation: ");
                jsonRequest.put("numberOfFoodItems", numberOfFoodItems);
                break;
            case ADD_TO_ROLLOUT_MENU:
                jsonRequest.put("chefAction", "ADD_TO_ROLLOUT_MENU");
                int rolloutItemCount = ConsoleReadUtils.getIntInput("Enter number of items you want to Rollout: ");
                jsonRequest.put("numberOfItems", rolloutItemCount);
                List<JSONObject> rolloutItems = new ArrayList<>();
                for (int rolloutItem = 0; rolloutItem < rolloutItemCount; rolloutItem++) {
                    JSONObject item = new JSONObject();
                    int foodId = ConsoleReadUtils.getIntInput("Enter Food Id: ");
                    item.put("foodId", foodId);
                    rolloutItems.add(item);
                }
                jsonRequest.put("items", new JSONArray(rolloutItems));
                LocalDateTime currentDate = LocalDateTime.now();
                jsonRequest.put("RolloutDate", currentDate);
                break;
            case VIEW_ROLLOUT_MENU:
                jsonRequest.put("chefAction", "VIEW_ROLLOUT_MENU");
                break;
            case VIEW_FINAL_VOTING:
                jsonRequest.put("chefAction", "VIEW_FINAL_VOTING");
                break;
            case VIEW_DISCARD_ITEM_FEEDBACK:
                jsonRequest.put("chefAction", "VIEW_DISCARD_ITEM_FEEDBACK");
                break;
            case SEND_NOTIFICATION:
                jsonRequest.put("chefAction", "SEND_NOTIFICATION");
                String message = ConsoleReadUtils.getStringInput("Enter the message by mentioning the DATE of Rollout");
                jsonRequest.put("message", message);
                break;
            case VIEW_DISCARD_MENU_ITEMS:
                jsonRequest.put("chefAction", "VIEW_DISCARD_MENU_ITEMS");
                break;
            default:
                System.out.println("Invalid choice.");
                return null;
        }
        return jsonRequest;
    }

    private static void sendRequest(PrintWriter writer, JSONObject jsonRequest) {
        writer.println(jsonRequest.toString());
    }

    private static void handleResponse(BufferedReader reader, ChefActions action) throws IOException {
        JSONObject jsonResponse = new JSONObject(reader.readLine());
        if (jsonResponse.getBoolean("success")) {
            System.out.println("Action successful.");
            switch (action) {
                case VIEW_MENU_ITEMS:
                    ConsolePrintUtils.printMenuItems(jsonResponse.getJSONArray("menu"));
                    break;
                case VIEW_RECOMMENDED_MENU:
                    ConsolePrintUtils.printRecommendedMenu(jsonResponse.getJSONArray("recommendations"));
                    break;
                case ADD_TO_ROLLOUT_MENU:
                    System.out.println("Successfully added Item Rollout Menu");
                    break;
                case VIEW_ROLLOUT_MENU:
                    ConsolePrintUtils.printRolloutMenuItems(jsonResponse.getJSONArray("rolloutItems"));
                    break;
                case VIEW_FINAL_VOTING:
                    ConsolePrintUtils.finalVotingResult(jsonResponse.getJSONArray("votingResult"));
                    break;
                case VIEW_DISCARD_ITEM_FEEDBACK:
                    ConsolePrintUtils.printDiscardItemFeedback(jsonResponse.getJSONArray("discardFeedback"));
                    break;
                case SEND_NOTIFICATION:
                    System.out.println("Notification sent to employee");
                    break;
                case VIEW_DISCARD_MENU_ITEMS:
                    System.out.println("*************" + jsonResponse.getString("message") + "*************");
                    ConsolePrintUtils.printDiscardMenuItems(jsonResponse.getJSONArray("discardItems"));
                    break;
            }
        } else {
            System.out.println("Action failed: " + jsonResponse.getString("error"));
        }
    }
}

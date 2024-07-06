package CafeteriaClient.ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import CafeteriaClient.utils.ConsolePrintUtils;
import CafeteriaClient.utils.ConsoleReadUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChefClientController {

    static void handleChefActions(PrintWriter writer, BufferedReader reader) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Chef Actions:");
            System.out.println("1. View Menu Items");
            System.out.println("2. View Recommend Menu");
            System.out.println("3. Add Items to Rollout Menu");
            System.out.println("4. View Rollout Menu");
            System.out.println("5. View Final Voting");
            System.out.println("6. Send Notification");
            System.out.println("7. View Discard Menu Items");
            System.out.println("8. Logout");

            int choice = ConsoleReadUtils.getIntInput("Enter your choice: ");

            JSONObject jsonRequest = createJsonRequest(choice);
            if (jsonRequest == null) {
                if (choice == 8) {
                    exit = true;
                } else {
                    System.out.println("Invalid choice.");
                }
                continue;
            }

            sendRequest(writer, jsonRequest);
            handleResponse(reader, choice);
        }
    }

    private static JSONObject createJsonRequest(int choice) {
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("action", "CHEF_ACTION");

        switch (choice) {
            case 1:
                jsonRequest.put("chefAction", "VIEW_MENU_ITEMS");
                break;
            case 2:
                jsonRequest.put("chefAction", "VIEW_RECOMMENDED_MENU");
                int numberOfFoodItems = ConsoleReadUtils.getIntInput("Enter number of items you want to get Recommendation: ");
                jsonRequest.put("numberOfFoodItems", numberOfFoodItems);
                break;
            case 3:
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
            case 4:
                jsonRequest.put("chefAction", "VIEW_ROLLOUT_MENU");
                break;
            case 5:
                jsonRequest.put("chefAction", "VIEW_FINAL_VOTING");
                break;
            case 6:
                jsonRequest.put("chefAction", "SEND_NOTIFICATION");
                String message = ConsoleReadUtils.getStringInput("Enter the message by mentioning the DATE of Rollout");
                jsonRequest.put("message", message);
                break;
            case 7:
                jsonRequest.put("chefAction", "VIEW_DISCARD_MENU_ITEMS");
                break;
            case 8:
                return null;
            default:
                return null;
        }
        return jsonRequest;
    }

    private static void sendRequest(PrintWriter writer, JSONObject jsonRequest) {
        writer.println(jsonRequest.toString());
    }

    private static void handleResponse(BufferedReader reader, int choice) throws IOException {
        JSONObject jsonResponse = new JSONObject(reader.readLine());
        if (jsonResponse.getBoolean("success")) {
            System.out.println("Action successful.");
            switch (choice) {
                case 1:
                    ConsolePrintUtils.printMenuItems(jsonResponse.getJSONArray("menu"));
                    break;
                case 2:
                    ConsolePrintUtils.printRecommendedMenu(jsonResponse.getJSONArray("recommendations"));
                    break;
                case 3:
                    System.out.println("Successfully added Item Rollout Menu");
                    break;
                case 4:
                    ConsolePrintUtils.printRolloutMenuItems(jsonResponse.getJSONArray("rolloutItems"));
                    break;
                case 5:
                    ConsolePrintUtils.finalVotingResult(jsonResponse.getJSONArray("votingResult"));
                    break;
                case 6:
                    System.out.println("Notification sent to employee");
                    break;
                case 7:
                    System.out.println("*************"+jsonResponse.getString("message")+"*************");
                    ConsolePrintUtils.printDiscardMenuItems(jsonResponse.getJSONArray("discardItems"));
                    break;
            }
        } else {
            System.out.println("Action failed: " + jsonResponse.getString("error"));
        }
    }
}

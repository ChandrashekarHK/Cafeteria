package CafeteriaClient.ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import CafeteriaClient.utils.ConsolePrintUtils;
import CafeteriaClient.utils.ConsoleReadUtils;
import org.json.JSONArray;
import org.json.JSONObject;



public class ChefClientController {

    static void handleChefActions(Scanner scanner, PrintWriter writer, BufferedReader reader) throws IOException {

        boolean exit = false;
        while (!exit) {
            System.out.println("Chef Actions:");
            System.out.println("1. View Menu Items");
            System.out.println("2. View Recommend Menu");
            System.out.println("3. Add Items to Rollout Menu");
            System.out.println("4. View Rollout Menu");
            System.out.println("5. View Final Voting");
            System.out.println("6. Send Notification");
            System.out.println("7. Logout");

            int choice = ConsoleReadUtils.getIntInput("Enter your choice: ");

            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("action", "CHEF_ACTION");

            switch (choice) {
                case 1:
                    jsonRequest.put("chefAction", "VIEW_MENU_ITEMS");
                    break;
                case 2:
                    jsonRequest.put("chefAction", "VIEW_RECOMMENDED_MENU");
                    int numberOfFoodItems = ConsoleReadUtils.getIntInput("Enter number of items you want to Rollout: ");
                    jsonRequest.put("numberOfFoodItems", numberOfFoodItems);
                    break;
                case 3:
                    jsonRequest.put("chefAction", "ADD_TO_ROLLOUT_MENU");
                    int itemCount = ConsoleReadUtils.getIntInput("Enter number of items you want to Rollout: ");
                    jsonRequest.put("numberOfItems", itemCount);
                    List<JSONObject> items = new ArrayList<>();
                    for (int i = 0; i < itemCount; i++) {
                        JSONObject item = new JSONObject();
                        int foodId = ConsoleReadUtils.getIntInput("Enter Food Id: ");
                        item.put("foodId", foodId);
                        items.add(item);
                    }
                    jsonRequest.put("items", new JSONArray(items));

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
                case 7 :
                    exit = true;
                    continue;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            writer.println(jsonRequest.toString());

            JSONObject jsonResponse = new JSONObject(reader.readLine());
            if (jsonResponse.getBoolean("success")) {
                System.out.println("Action successful.");
                if(choice == 1) {
                    ConsolePrintUtils.printMenuItems(jsonResponse.getJSONArray("menu"));
                }
                else if (choice == 2) {
                    ConsolePrintUtils.printRecommendedMenu(jsonResponse.getJSONArray("recommendations"));
                }
                else if (choice == 3) {
                    System.out.println("Successfully added Item Rollout Menu");
                }
                else if(choice == 4) {
                    ConsolePrintUtils.printRolloutMenuItems(jsonResponse.getJSONArray("rolloutItems"));
                }
                else if(choice == 5) {
                    ConsolePrintUtils.finalVotingResult(jsonResponse.getJSONArray("votingResult"));
                }
                else if(choice == 6) {
                    System.out.println("Notification sent to employee");
                }
            } else {
                System.out.println("Action failed: " + jsonResponse.getString("error"));
            }
        }
    }
}
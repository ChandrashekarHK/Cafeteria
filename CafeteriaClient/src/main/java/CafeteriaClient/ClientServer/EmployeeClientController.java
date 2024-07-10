package CafeteriaClient.ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import CafeteriaClient.utils.ConsolePrintUtils;
import CafeteriaClient.utils.ConsoleReadUtils;
import CafeteriaClient.utils.MenuInputHelper;
import org.json.JSONObject;



public class EmployeeClientController {

    static void handleEmployeeActions(PrintWriter writer, BufferedReader reader,String userId) throws IOException {
        int foodId,vote, rolloutId ;
        Timestamp date;
        float rating;
        boolean exit = false;
        String cuisineType, foodType;
        int spiceLevel, saltiness, sweetness;
        while (!exit) {
            System.out.println("Employee Actions:");
            System.out.println("1. View Main Menu");
            System.out.println("2. View Notifications");
            System.out.println("3. View recommended rollout Menu");
            System.out.println("4. Vote for Rollout Items");
            System.out.println("5. Provide your  Feedback on Food ordered");
            System.out.println("6. Provide your Feedback on Discard Food Item in notification");
            System.out.println("7. update userProfile");

            System.out.println("8. Logout");

            int choice = ConsoleReadUtils.getIntInput("Enter your choice: ");

            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("action", "EMPLOYEE_ACTION");

            switch (choice) {
                case 1 :
                    jsonRequest.put("employeeAction", "VIEW_MENU_ITEMS");
                    break;
                case 2 :
                    jsonRequest.put("employeeAction", "VIEW_NOTIFICATIONS");
                    break;
                case 3 :
                    jsonRequest.put("employeeAction", "VIEW_RECOMMENDED_ROLLOUT_MENU");
                    jsonRequest.put("userId", userId);

                    break;
                case 4 :
                    jsonRequest.put("employeeAction", "VOTE_ROLLOUT_ITEMS");
                    foodId = ConsoleReadUtils.getIntInput("Enter Food Id: ");
                    jsonRequest.put("foodId", foodId);
                    rolloutId = ConsoleReadUtils.getIntInput("Enter the Rollout ID present in the Rollout menu: ");
                    jsonRequest.put("rolloutId", rolloutId);
                    jsonRequest.put("userId", userId);
                    vote = ConsoleReadUtils.getIntInput("Enter your vote 1 or 0: ");
                    jsonRequest.put("vote", vote);
                    date = Timestamp.valueOf(LocalDateTime.now());
                    jsonRequest.put("date", date);

                    System.out.println("foodId:  " + jsonRequest.getInt("foodId"));

                    break;
                case 5 :
                    jsonRequest.put("employeeAction", "PROVIDE_FEEDBACK");
                    int food_Id = ConsoleReadUtils.getIntInput("Enter Food Id: ");
                    String comment = ConsoleReadUtils.getStringInput("Enter your comment: ");
                    rating = (float) ConsoleReadUtils.getRatingInput("Enter Rating (1-5): ");
                    date = Timestamp.valueOf(LocalDateTime.now());
                    jsonRequest.put("foodId", food_Id);
                    jsonRequest.put("comment", comment);
                    jsonRequest.put("rating", rating);
                    jsonRequest.put("date", date);
                    jsonRequest.put("userId", userId);
                    break;
                case 6 :
                    jsonRequest.put("employeeAction", "PROVIDE_DISCARD_ITEM_FEEDBACK");
                    int discardId = ConsoleReadUtils.getIntInput("Enter Discard Food Id: ");
                    String answerOne = ConsoleReadUtils.getStringInput("What did’t you like about dish with discard ID "+discardId+"  ? ");
                    String answerTwo=ConsoleReadUtils.getStringInput("How would you like  dish with discard ID "+discardId + " to taste?");
                    String answerThree= ConsoleReadUtils.getStringInput("Share your Moms recipe: ");

                    jsonRequest.put("discardId", discardId);
                    jsonRequest.put("answerOne", answerOne);
                    jsonRequest.put("answerTwo", answerTwo);
                    jsonRequest.put("answerThree", answerThree);
                    jsonRequest.put("userId", userId);
                    break;
                case 7 :
                    jsonRequest.put("employeeAction", "SAVE_USER_PROFILE");
                    jsonRequest.put("userId", userId);
                    cuisineType = ConsoleReadUtils.getStringInput("Enter Cuisine Type: ");
                    spiceLevel = MenuInputHelper.getSpiceLevelInput();
                    foodType = MenuInputHelper.getFoodTypeInput();
                    saltiness = MenuInputHelper.getSaltinessInput();
                    sweetness = MenuInputHelper.getSweetnessInput();
                    jsonRequest.put("cuisineType", cuisineType);
                    jsonRequest.put("spiceLevel", spiceLevel);
                    jsonRequest.put("foodType", foodType);
                    jsonRequest.put("saltiness", saltiness);
                    jsonRequest.put("sweetness", sweetness);
                    break;
                case 8 :
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
                    ConsolePrintUtils.printNotifications(jsonResponse.getJSONArray("notifications"));
                }
                else if(choice == 3) {
                    ConsolePrintUtils.printRolloutMenuItems(jsonResponse.getJSONArray("rolloutItems"));
                }
                else if(choice == 4) {
                    System.out.println("Thank you for your Vote");
                }
                else if(choice == 5) {
                    System.out.println("Thank you for your Feedback on Ordered Item");
                }
                else if(choice == 6) {
                    System.out.println("Thank you for your Feedback on discarded Item");
                }
            } else {
                System.out.println("Action failed: " + jsonResponse.getString("error"));
            }
        }
    }
}
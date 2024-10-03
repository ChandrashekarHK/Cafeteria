package CafeteriaClient.ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import CafeteriaClient.ClientServer.Constants.EmployeeActions;
import CafeteriaClient.utils.ConsolePrintUtils;
import CafeteriaClient.utils.ConsoleReadUtils;
import CafeteriaClient.utils.MenuInputHelper;
import org.json.JSONObject;

public class EmployeeClientController {

    static void handleEmployeeActions(PrintWriter writer, BufferedReader reader, String userId) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Employee Actions:");
            for (EmployeeActions action : EmployeeActions.values()) {
                System.out.println(action.getValue() + ". " + action.getDescription());
            }

            int choice = ConsoleReadUtils.getIntInput("Enter your choice: ");

            EmployeeActions action;
            try {
                action = EmployeeActions.fromValue(choice);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            JSONObject jsonRequest = createJsonRequest(action, userId);
            if (jsonRequest == null) {
                if (action == EmployeeActions.LOGOUT) {
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

    private static JSONObject createJsonRequest(EmployeeActions action, String userId) {
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("action", "EMPLOYEE_ACTION");
        int foodId, vote, rolloutId;
        Timestamp date;
        float rating;
        String cuisineType, foodType;
        int spiceLevel, saltiness, sweetness;

        switch (action) {
            case VIEW_MENU_ITEMS:
                jsonRequest.put("employeeAction", "VIEW_MENU_ITEMS");
                break;
            case VIEW_NOTIFICATIONS:
                jsonRequest.put("employeeAction", "VIEW_NOTIFICATIONS");
                break;
            case VIEW_RECOMMENDED_ROLLOUT_MENU:
                jsonRequest.put("employeeAction", "VIEW_RECOMMENDED_ROLLOUT_MENU");
                jsonRequest.put("userId", userId);
                break;
            case VOTE_ROLLOUT_ITEMS:
                jsonRequest.put("employeeAction", "VOTE_ROLLOUT_ITEMS");
                foodId = ConsoleReadUtils.getIntInput("Enter Food Id: ");
                jsonRequest.put("foodId", foodId);
                rolloutId = ConsoleReadUtils.getIntInput("Enter the Rollout ID present in the Rollout menu: ");
                jsonRequest.put("rolloutId", rolloutId);
                jsonRequest.put("userId", userId);
                vote = ConsoleReadUtils.getVoteInput("If you want this food to be cooked then enter 1 else 0 : ");
                jsonRequest.put("vote", vote);
                date = Timestamp.valueOf(LocalDateTime.now());
                jsonRequest.put("date", date);
                break;
            case PROVIDE_FEEDBACK:
                jsonRequest.put("employeeAction", "PROVIDE_FEEDBACK");
                foodId = ConsoleReadUtils.getIntInput("Enter Food Id: ");
                String comment = ConsoleReadUtils.getStringInput("Enter your comment: ");
                rating = (float) ConsoleReadUtils.getRatingInput("Enter Rating (1-5): ");
                Timestamp feedbackDate = Timestamp.valueOf(LocalDateTime.now());
                jsonRequest.put("foodId", foodId);
                jsonRequest.put("comment", comment);
                jsonRequest.put("rating", rating);
                jsonRequest.put("date", feedbackDate);
                jsonRequest.put("userId", userId);
                break;
            case PROVIDE_DISCARD_ITEM_FEEDBACK:
                jsonRequest.put("employeeAction", "PROVIDE_DISCARD_ITEM_FEEDBACK");
                int discardId = ConsoleReadUtils.getIntInput("Enter Discard Food Id: ");
                String answerOne = ConsoleReadUtils.getStringInput("What did’t you like about dish with discard ID " + discardId + "  ? ");
                String answerTwo = ConsoleReadUtils.getStringInput("How would you like dish with discard ID " + discardId + " to taste?");
                String answerThree = ConsoleReadUtils.getStringInput("Share your Mom's recipe: ");
                jsonRequest.put("discardId", discardId);
                jsonRequest.put("answerOne", answerOne);
                jsonRequest.put("answerTwo", answerTwo);
                jsonRequest.put("answerThree", answerThree);
                jsonRequest.put("userId", userId);
                break;
            case SAVE_USER_PROFILE:
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
            case LOGOUT:
                return null;
            default:
                System.out.println("Invalid choice.");
                return null;
        }
        return jsonRequest;
    }

    private static void sendRequest(PrintWriter writer, JSONObject jsonRequest) {
        writer.println(jsonRequest.toString());
    }

    private static void handleResponse(BufferedReader reader, EmployeeActions action) throws IOException {
        JSONObject jsonResponse = new JSONObject(reader.readLine());
        if (jsonResponse.getBoolean("success")) {
            System.out.println("Action successful.");
            switch (action) {
                case VIEW_MENU_ITEMS:
                    ConsolePrintUtils.printMenuItems(jsonResponse.getJSONArray("menu"));
                    break;
                case VIEW_NOTIFICATIONS:
                    ConsolePrintUtils.printNotifications(jsonResponse.getJSONArray("notifications"));
                    break;
                case VIEW_RECOMMENDED_ROLLOUT_MENU:
                    ConsolePrintUtils.printRolloutMenuItems(jsonResponse.getJSONArray("rolloutItems"));
                    break;
                case VOTE_ROLLOUT_ITEMS:
                    System.out.println("Thank you for your Vote");
                    break;
                case PROVIDE_FEEDBACK:
                    System.out.println("Thank you for your Feedback on Ordered Item");
                    break;
                case PROVIDE_DISCARD_ITEM_FEEDBACK:
                    System.out.println("Thank you for your Feedback on discarded Item");
                    break;
                case SAVE_USER_PROFILE:
                    System.out.println("User profile updated successfully.");
                    break;
                default:
                    System.out.println("Unknown success action.");
                    break;
            }
        } else {
            System.out.println("Action failed: " + jsonResponse.getString("error"));
        }
    }
}

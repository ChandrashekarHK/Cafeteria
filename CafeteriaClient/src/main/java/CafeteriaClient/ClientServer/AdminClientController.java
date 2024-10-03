package CafeteriaClient.ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import CafeteriaClient.ClientServer.Constants.AdminAction;
import CafeteriaClient.utils.ConsolePrintUtils;
import CafeteriaClient.utils.ConsoleReadUtils;
import CafeteriaClient.utils.MenuInputHelper;
import org.json.JSONObject;

public class AdminClientController {

    static void handleAdminActions(PrintWriter writer, BufferedReader reader) throws IOException {

        int foodId;
        String foodName, cuisineType, foodType, category;
        BigDecimal price;
        boolean availability;
        int spiceLevel, saltiness, sweetness;
        boolean exit = false;

        while (!exit) {
            System.out.println("Admin Actions:");
            for (AdminAction action : AdminAction.values()) {
                System.out.println(action.getValue() + ". " + action.getDescription());
            }

            int choice = ConsoleReadUtils.getIntInput("Enter your choice: ");

            AdminAction action;
            try {
                action = AdminAction.fromValue(choice);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("action", "ADMIN_ACTION");

            switch (action) {
                case VIEW_MENU_ITEMS:
                    jsonRequest.put("adminAction", "VIEW_MENU_ITEMS");
                    break;
                case ADD_MENU_ITEM:
                    foodId = ConsoleReadUtils.getIntInput("Enter Food Id: ");
                    foodName = ConsoleReadUtils.getStringInput("Enter Food name: ");
                    price = ConsoleReadUtils.getBigDecimalInput("Enter Food price: ");
                    availability = MenuInputHelper.getAvailabilityInput();
                    cuisineType = ConsoleReadUtils.getStringInput("Enter Cuisine Type: ");
                    spiceLevel = MenuInputHelper.getSpiceLevelInput();
                    foodType = MenuInputHelper.getFoodTypeInput();
                    saltiness = MenuInputHelper.getSaltinessInput();
                    sweetness = MenuInputHelper.getSweetnessInput();
                    category = MenuInputHelper.getCategoryInput();

                    jsonRequest.put("adminAction", "ADD_MENU_ITEM");
                    jsonRequest.put("foodId", foodId);
                    jsonRequest.put("name", foodName);
                    jsonRequest.put("price", price);
                    jsonRequest.put("availability", availability);
                    jsonRequest.put("cuisineType", cuisineType);
                    jsonRequest.put("spiceLevel", spiceLevel);
                    jsonRequest.put("foodType", foodType);
                    jsonRequest.put("saltiness", saltiness);
                    jsonRequest.put("sweetness", sweetness);
                    jsonRequest.put("category", category);
                    break;

                case UPDATE_MENU_ITEM:
                    foodId = ConsoleReadUtils.getIntInput("Enter Food Id: ");
                    foodName = ConsoleReadUtils.getStringInput("Enter Food name: ");
                    price = ConsoleReadUtils.getBigDecimalInput("Enter Food price: ");
                    availability = MenuInputHelper.getAvailabilityInput();
                    cuisineType = ConsoleReadUtils.getStringInput("Enter Cuisine Type: ");
                    spiceLevel = MenuInputHelper.getSpiceLevelInput();
                    foodType = MenuInputHelper.getFoodTypeInput();
                    saltiness = MenuInputHelper.getSaltinessInput();
                    sweetness = MenuInputHelper.getSweetnessInput();
                    category = MenuInputHelper.getCategoryInput();

                    jsonRequest.put("adminAction", "UPDATE_MENU_ITEM");
                    jsonRequest.put("foodId", foodId);
                    jsonRequest.put("name", foodName);
                    jsonRequest.put("price", price);
                    jsonRequest.put("availability", availability);
                    jsonRequest.put("cuisineType", cuisineType);
                    jsonRequest.put("spiceLevel", spiceLevel);
                    jsonRequest.put("foodType", foodType);
                    jsonRequest.put("saltiness", saltiness);
                    jsonRequest.put("sweetness", sweetness);
                    jsonRequest.put("category", category);
                    break;
                case DELETE_MENU_ITEM:
                    foodId = ConsoleReadUtils.getIntInput("Enter Food Id: ");
                    jsonRequest.put("adminAction", "DELETE_MENU_ITEM");
                    jsonRequest.put("foodId", foodId);
                    break;

                case VIEW_DISCARD_MENU_ITEMS:
                    jsonRequest.put("adminAction", "VIEW_DISCARD_MENU_ITEMS");
                    break;

                case LOGOUT:
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
                switch (action) {
                    case VIEW_MENU_ITEMS:
                        ConsolePrintUtils.printMenuItems(jsonResponse.getJSONArray("menu"));
                        break;
                    case ADD_MENU_ITEM:
                        if (jsonResponse.getBoolean("ItemExist")) {
                            System.out.println("Food ID already exists. Please view the menu and use a unique Food ID.");
                        } else {
                            System.out.println("Successfully added food item to menu.");
                        }
                        break;
                    case UPDATE_MENU_ITEM:
                        System.out.println("Successfully updated food item in menu.");
                        break;
                    case DELETE_MENU_ITEM:
                        System.out.println("Successfully deleted food item from menu.");
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
}

package CafeteriaClient.ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;


import CafeteriaClient.utils.ConsolePrintUtils;
import CafeteriaClient.utils.ConsoleReadUtils;
import org.json.JSONObject;


public class AdminClientController {

    static void handleAdminActions(PrintWriter writer, BufferedReader reader) throws IOException {

        int foodId;
        String foodName;
        BigDecimal price;
        boolean availability;
        boolean exit = false;
        while (!exit) {
            System.out.println("Admin Actions:");
            System.out.println("1. View Main Menu Item");
            System.out.println("2. Add Menu Item");
            System.out.println("3. Update Menu Item");
            System.out.println("4. Delete Menu Item");
            System.out.println("5. Logout");

            int choice = ConsoleReadUtils.getIntInput("Enter your choice: ");

            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("action", "ADMIN_ACTION");

            switch (choice) {
                case 1:
                    jsonRequest.put("adminAction", "VIEW_MENU_ITEMS");
                    break;
                case 2:
                    foodId = ConsoleReadUtils.getIntInput("Enter Food Id: ");
                    foodName = ConsoleReadUtils.getStringInput("Enter Food name: ");
                    price = ConsoleReadUtils.getBigDecimalInput("Enter Food price: ");
                    availability = ConsoleReadUtils.getBooleanInput("Enter 1 if food available else 0 ");
                    jsonRequest.put("adminAction", "ADD_MENU_ITEM");
                    jsonRequest.put("foodId", foodId);
                    jsonRequest.put("name", foodName);
                    jsonRequest.put("price", price);
                    jsonRequest.put("Availability", availability);
                    break;
                case 3:
                    foodId = ConsoleReadUtils.getIntInput("Enter Food Id: ");
                    foodName = ConsoleReadUtils.getStringInput("Enter Food name: ");
                    price = ConsoleReadUtils.getBigDecimalInput("Enter Food price: ");
                    jsonRequest.put("adminAction", "UPDATE_MENU_ITEM");
                    jsonRequest.put("foodId", foodId);
                    jsonRequest.put("name", foodName);
                    jsonRequest.put("price", price);
                    break;
                case 4:
                    foodId = ConsoleReadUtils.getIntInput("Enter Food Id: ");
                    jsonRequest.put("adminAction", "DELETE_MENU_ITEM");
                    jsonRequest.put("foodId", foodId);
                    break;
                case 5 :
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
                else if(choice == 2) {
                    if(jsonResponse.getBoolean("ItemExist"))
                    {
                        System.out.println("Food ID is already exist So please View menue and use Unique FoodID");
                    }
                    else
                    {
                        System.out.println("Successfully Added food item in menu");
                    }
                }
                else if(choice == 3) {
                    System.out.println("Successfully Updated food item in menu");
                }
                else if(choice == 4) {
                    System.out.println("Successfully Deleted food item in menu");
                }

            } else {
                System.out.println("Action failed: " + jsonResponse.getString("error"));
            }
        }
    }
}
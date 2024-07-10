package com.cafeteria.server.Controllers;
import com.cafeteria.server.menu.MenuItem;
import com.cafeteria.server.userOperations.AdminService;

import org.json.JSONObject;
import java.math.BigDecimal;
import java.sql.SQLException;

public class AdminController {
    private AdminService adminService;

    public AdminController() throws SQLException {
        this.adminService =new AdminService();

    }

    public JSONObject handleAdminActions(JSONObject jsonRequest) throws SQLException {
        String adminAction = jsonRequest.getString("adminAction");
        JSONObject jsonResponse = new JSONObject();

        int foodId ;
        String name ;
        BigDecimal price;
        boolean availability ;
        MenuItem item;
        String cuisineType;
        int spiceLevel ;
        String foodType ;
        int saltiness;
        int sweetness ;
        String category ;


        switch (adminAction) {
            case "VIEW_MENU_ITEMS":
                jsonResponse = adminService.viewMenu();
                break;

            case "ADD_MENU_ITEM":
                foodId = jsonRequest.getInt("foodId");
                name = jsonRequest.getString("name");
                price = jsonRequest.getBigDecimal("price");
                availability = jsonRequest.getBoolean("availability");
                cuisineType = jsonRequest.getString("cuisineType");
                spiceLevel = jsonRequest.getInt("spiceLevel");
                foodType = jsonRequest.getString("foodType");
                saltiness = jsonRequest.getInt("saltiness");
                sweetness = jsonRequest.getInt("sweetness");
                category = jsonRequest.getString("category");

                item = new MenuItem(foodId, name, price, availability, cuisineType, spiceLevel, foodType, saltiness, sweetness, category);
                jsonResponse = adminService.addMenuItem(item);
                break;

            case "UPDATE_MENU_ITEM":
                foodId = jsonRequest.getInt("foodId");
                name = jsonRequest.getString("name");
                price = jsonRequest.getBigDecimal("price");
                availability = jsonRequest.getBoolean("availability");
                cuisineType = jsonRequest.getString("cuisineType");
                spiceLevel = jsonRequest.getInt("spiceLevel");
                foodType = jsonRequest.getString("foodType");
                saltiness = jsonRequest.getInt("saltiness");
                sweetness = jsonRequest.getInt("sweetness");
                category = jsonRequest.getString("category");
                item = new MenuItem(foodId, name, price, availability, cuisineType, spiceLevel, foodType, saltiness, sweetness, category);
                jsonResponse =  adminService.updateMenuItem(item);

                break;

            case "DELETE_MENU_ITEM":
                foodId = jsonRequest.getInt("foodId");
                jsonResponse = adminService.deleteMenuItem(foodId);
                break;
            case "VIEW_DISCARD_MENU_ITEMS":
                jsonResponse = adminService.viewDiscardMenu();
                break;

            default:
                jsonResponse.put("success", false);
                jsonResponse.put("error", "Invalid admin action");
                break;
        }

        return jsonResponse;
    }

}

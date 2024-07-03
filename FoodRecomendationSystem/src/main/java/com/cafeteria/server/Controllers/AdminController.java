package com.cafeteria.server.Controllers;
import com.cafeteria.server.menu.MenuItem;
import com.cafeteria.server.userOperations.AdminService;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class AdminController {
    private AdminService adminService;

    public AdminController() throws SQLException {
        this.adminService =new AdminService();

    }

    public JSONObject start(JSONObject jsonRequest) throws SQLException, IOException {
        String adminAction = jsonRequest.getString("adminAction");
        JSONObject jsonResponse = new JSONObject();
        int foodId ;
        String name ;
        BigDecimal price;
        boolean availability ;
        MenuItem item;

        switch (adminAction) {
            case "VIEW_MENU_ITEMS":
                jsonResponse = adminService.viewMenu();

                break;

            case "ADD_MENU_ITEM":
                foodId = jsonRequest.getInt("foodId");
                name = jsonRequest.getString("name");
                price = jsonRequest.getBigDecimal("price");
                availability = jsonRequest.getBoolean("Availability");
                item = new MenuItem(foodId, name,price,availability);
                jsonResponse = adminService.addMenuItem(item);
                break;

            case "UPDATE_MENU_ITEM":
                foodId = jsonRequest.getInt("foodId");
                name = jsonRequest.getString("name");
                price = jsonRequest.getBigDecimal("price");
                availability = jsonRequest.getBoolean("Availability");
                item = new MenuItem(foodId, name,price,availability);
                jsonResponse =  adminService.updateMenuItem(item);

                break;

            case "DELETE_MENU_ITEM":
                foodId = jsonRequest.getInt("foodId");
                jsonResponse = adminService.deleteMenuItem(foodId);
                break;

            default:
                jsonResponse.put("success", false);
                jsonResponse.put("error", "Invalid admin action");
                break;
        }

        return jsonResponse;
    }

}

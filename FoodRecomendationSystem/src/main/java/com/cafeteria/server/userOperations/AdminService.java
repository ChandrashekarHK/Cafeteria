package com.cafeteria.server.userOperations;

import com.cafeteria.server.menu.MenuService;
import com.cafeteria.server.menu.MenuItem;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminService {

    private MenuService menuServices;
    JSONObject jsonResponse = new JSONObject();
    public AdminService() throws SQLException {

        this.menuServices = new MenuService();

    }
 public JSONObject viewMenu() {
    JSONObject jsonResponse = new JSONObject();
    List<MenuItem> menu = menuServices.getMenu();
    jsonResponse.put("success", true);
    jsonResponse.put("menu", menu);
    return jsonResponse;
}

public JSONObject addMenuItem(MenuItem item) throws IOException, SQLException {
   if (menuServices.checkFoodId(item.getFoodId()))
   {
       jsonResponse.put("success", true);
       return jsonResponse.put("ItemExist", true);

   }
   else
   {
       jsonResponse.put("ItemExist",false);
       if(menuServices.addMenuItem(item))
       {
           return jsonResponse.put("success", true);
       }
       else {
           return jsonResponse.put("success", false);
       }

   }


}

public JSONObject updateMenuItem(MenuItem item) throws IOException, SQLException {

    if(menuServices.updateMenuItem(item)) {
        return jsonResponse.put("success", true);
    }
    else {
        return jsonResponse.put("success", false);
    }
}

public JSONObject deleteMenuItem(int foodId) throws IOException, SQLException {

    if (menuServices.deleteMenuItem(foodId)) {
        return jsonResponse.put("success", true);
    } else {
        return jsonResponse.put("success", false);
    }
}

}

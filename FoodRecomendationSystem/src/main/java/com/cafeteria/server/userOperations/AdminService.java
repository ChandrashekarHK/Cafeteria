package com.cafeteria.server.userOperations;
import com.cafeteria.server.discardMenuService.DiscardMenuItem;
import com.cafeteria.server.discardMenuService.DiscardMenuService;
import com.cafeteria.server.menu.MenuService;
import com.cafeteria.server.menu.MenuItem;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AdminService {

    private MenuService menuServices;
    private DiscardMenuService discardMenuService;
    JSONObject jsonResponse = new JSONObject();
    public AdminService() throws SQLException {

        this.menuServices = new MenuService();
        this.discardMenuService = new DiscardMenuService();

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
    public JSONObject viewDiscardMenu() throws IOException, SQLException {
        JSONObject jsonResponse = new JSONObject();
        List<DiscardMenuItem> discardItems = discardMenuService.getAllDiscardItems();
        if (discardItems != null && !discardItems.isEmpty())
        {
            jsonResponse.put("message", "See Discard table.");
            jsonResponse.put("discardItems", discardItems);
        }
        else
        {
            discardMenuService.createAndSaveDiscardItems();
            jsonResponse.put("message", "Discard menu for " + LocalDate.now().getMonth() + " has been created.");
            discardItems = discardMenuService.getAllDiscardItems();
            jsonResponse.put("discardItems", discardItems);

        }
        jsonResponse.put("success", true);
        return jsonResponse;
    }

}

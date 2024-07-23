package com.cafeteria.server.menu;

import com.cafeteria.server.db.DBMenuItemService;

import java.util.List;

public class MenuService {
    private DBMenuItemService dbMenuItemService;

    public MenuService() {
        this.dbMenuItemService = new DBMenuItemService();
    }

    public boolean addMenuItem(MenuItem item) {
        if (dbMenuItemService.addMenuItem(item)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateMenuItem(MenuItem item) {

        if (dbMenuItemService.updateMenuItem(item)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkFoodId(int id) {
        if (dbMenuItemService.isFoodItemIdPresent(id)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteMenuItem(int id) {
        if (dbMenuItemService.deleteMenuItem(id)) {
            return true;
        } else {
            return false;
        }

    }

    public List<MenuItem> getMenu() {
        List<MenuItem> Menue = dbMenuItemService.readAllMenuItems();
        return Menue;
    }

    public MenuItem getMenuIteamByFoodId(int foodId) {
        MenuItem menuItem = dbMenuItemService.getMenuItemById(foodId);
        return menuItem;
    }
}

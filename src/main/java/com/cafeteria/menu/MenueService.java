package com.cafeteria.menu;

import com.cafeteria.db.DBMenuItemService;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenueService {
    private DBMenuItemService dbMenuItemService;

    public MenueService() throws SQLException {
        this.dbMenuItemService = new DBMenuItemService();
    }

    public boolean addMenuItem(MenuItem item) throws SQLException {
        if(dbMenuItemService.addMenuItem(item))
        {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean updateMenuItem(MenuItem item) {

        if(dbMenuItemService.updateMenuItem(item))
        {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean deleteMenuItem(int id) {
        if(dbMenuItemService.deleteMenuItem(id))
        {
            return true;
        }
        else{
            return false;
        }

    }
    public List<MenuItem> getMenu()
    {
        List<MenuItem> Menue = dbMenuItemService.readAllMenuItems();
        return Menue;
    }
}

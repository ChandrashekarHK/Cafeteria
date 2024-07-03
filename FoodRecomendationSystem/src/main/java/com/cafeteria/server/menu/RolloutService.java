
package com.cafeteria.server.menu;

import com.cafeteria.server.db.DBRolloutMenuService;

import java.sql.SQLException;
import java.util.List;

public class RolloutService {
    private DBRolloutMenuService dbRolloutMenuService;

    public RolloutService() throws SQLException {
        this.dbRolloutMenuService = new DBRolloutMenuService();
    }

    public boolean addRolloutMenuItem(RolloutMenuItem item) throws SQLException {
        return dbRolloutMenuService.addRolloutMenuItem(item);
    }

    public boolean deleteAllRolloutMenuItems() {
        return dbRolloutMenuService.deleteAllRolloutMenuItems();
    }

    public boolean deleteRolloutMenuItem(int rolloutID) {
        return dbRolloutMenuService.deleteRolloutMenuItem(rolloutID);
    }

    public List<RolloutMenuItem> getRolloutMenu() {
        return dbRolloutMenuService.readAllRolloutMenuItems();
    }
}

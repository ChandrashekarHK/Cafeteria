package com.cafeteria.userOperations;


import com.cafeteria.menu.RolloutMenuCommonService;
import com.cafeteria.menu.RolloutMenuItem;
import com.cafeteria.menu.RolloutMenuSpecialServices;
import com.cafeteria.server.ClientHandler;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class RollOutItemsService
{
    private RolloutMenuCommonService rolloutMenuCommonService;
    private RolloutMenuSpecialServices rolloutMenuSpecialService;
    private ClientHandler clientHandler;

    public RollOutItemsService(ClientHandler clientHandler) throws SQLException {
        this.rolloutMenuCommonService = new RolloutMenuCommonService();
        this.rolloutMenuSpecialService = new RolloutMenuSpecialServices();
        this.clientHandler = clientHandler;
    }

    public void start() {
        while (true) {
            try {
                clientHandler.writeToClient(getMenuPrompt());

                int choice = Integer.parseInt(clientHandler.readFromClient());

                switch (choice) {
                    case 1:
                        viewRolloutMenu();
                        break;
                    case 2:
                        addRolloutMenuItem();
                        break;
                    case 3:
                        updateRolloutMenuItem();
                        break;
                    case 4:
                        deleteRolloutMenuItem();
                        break;
                    case 5:
                        clientHandler.writeToClient("Exiting...");
                        return;
                    default:
                        clientHandler.writeToClient("Invalid choice. Please try again.");
                }
            } catch (IOException e) {
                clientHandler.writeToClient("Error reading input: " + e.getMessage());
            } catch (SQLException e) {
                clientHandler.writeToClient("Database error: " + e.getMessage());
            } catch (NumberFormatException e) {
                clientHandler.writeToClient("Invalid input format. Please enter a number.");
            }
        }
    }

    private String getMenuPrompt() {
        return "Select an operation:  " +
                "1. View Rollout Menu  " +
                "2. Add Rollout Menu Item  " +
                "3. Update Rollout Menu Item  " +
                "4. Delete Rollout Menu Item  " +
                "5. Exit\n";
    }

    private void viewRolloutMenu() {
        List<RolloutMenuItem> menu = rolloutMenuCommonService.getRolloutMenu();
        for (RolloutMenuItem item : menu) {
            clientHandler.writeToClient(item.toString());
        }
        clientHandler.writeToClient("Wait till the menu executes");
    }

    private void addRolloutMenuItem() throws IOException, SQLException {
        clientHandler.writeToClient("Enter item name: ");
        String name = clientHandler.readFromClient();
        clientHandler.writeToClient("Enter item ID: ");
        int id = Integer.parseInt(clientHandler.readFromClient());
        clientHandler.writeToClient("Enter item vote count: ");
        int vote = Integer.parseInt(clientHandler.readFromClient());
        clientHandler.writeToClient("Enter item price: ");
        BigDecimal price = new BigDecimal(clientHandler.readFromClient());

        RolloutMenuItem item = new RolloutMenuItem(id, name, price, vote);
        rolloutMenuSpecialService.addRolloutMenuItem(item);
        clientHandler.writeToClient("Item added successfully.");
    }


    private void updateRolloutMenuItem() throws IOException, SQLException {
        clientHandler.writeToClient("Enter item ID to update: ");
        int id = Integer.parseInt(clientHandler.readFromClient());
        clientHandler.writeToClient("Enter new item name: ");
        String name = clientHandler.readFromClient();
        clientHandler.writeToClient("Enter new item vote count: ");
        int vote = Integer.parseInt(clientHandler.readFromClient());
        clientHandler.writeToClient("Enter new item price: ");
        BigDecimal price = new BigDecimal(clientHandler.readFromClient());

        RolloutMenuItem item = new RolloutMenuItem(id, name, price, vote);
        rolloutMenuSpecialService.updateRolloutMenuItem(item);
        clientHandler.writeToClient("Item updated successfully.");
    }

    private void deleteRolloutMenuItem() throws IOException, SQLException {
        clientHandler.writeToClient("Enter item ID to delete: ");
        int id = Integer.parseInt(clientHandler.readFromClient());
        rolloutMenuSpecialService.deleteRolloutMenuItem(id);
        clientHandler.writeToClient("Item deleted successfully.");
    }
}

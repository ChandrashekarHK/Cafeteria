package com.cafeteria.userOperations;

import com.cafeteria.menu.MenueService;
import com.cafeteria.menu.MenuItem;
import com.cafeteria.server.ClientHandler;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class AdminOperationsHandler {

    private MenueService menuServices;
    private ClientHandler clientHandler;

    public AdminOperationsHandler(ClientHandler clientHandler) throws SQLException {
        this.menuServices = new MenueService();
        this.clientHandler = clientHandler;
    }

    public void start() {
        while (true) {
            try {
                clientHandler.writeToClient(getMenuPrompt());

                int choice = Integer.parseInt(clientHandler.readFromClient());

                switch (choice) {
                    case 1:
                        viewMenu();
                        break;
                    case 2:
                        addMenuItem();
                        break;
                    case 3:
                        updateMenuItem();
                        break;
                    case 4:
                        deleteMenuItem();
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
                "1. View Menu  " +
                "2. Add Menu Item  " +
                "3. Update Menu Item  " +
                "4. Delete Menu Item  " +
                "5. Exit\n";
    }

    private void viewMenu() {
        List<MenuItem> menu = menuServices.getMenu();
        if(menu != null){
            for (MenuItem item : menu) {
                clientHandler.writeToClient(String.valueOf(item));
            }
            clientHandler.writeToClient("menue executes");
        }
        else {
            clientHandler.writeToClient("Failed menue executes");
        }

    }

    private void addMenuItem() throws IOException, SQLException {
        clientHandler.writeToClient("Enter item name: ");
        String name = clientHandler.readFromClient();
        clientHandler.writeToClient("Enter item ID to update: ");
        int id = Integer.parseInt(clientHandler.readFromClient());
        clientHandler.writeToClient("Enter new item availability 1 for available and 0 for unavailable ");
        boolean availability = Boolean.parseBoolean(clientHandler.readFromClient());
        clientHandler.writeToClient("Enter item price: ");
        BigDecimal price = new BigDecimal(clientHandler.readFromClient());

        MenuItem item = new MenuItem(id, name,price,availability);
        if(menuServices.addMenuItem(item)) {
            clientHandler.writeToClient("Item added successfully.");
        }
    }

    private void updateMenuItem() throws IOException, SQLException {
        clientHandler.writeToClient("Enter item ID to update: ");
        int id = Integer.parseInt(clientHandler.readFromClient());
        clientHandler.writeToClient("Enter new item name: ");
        String name = clientHandler.readFromClient();
        clientHandler.writeToClient("Enter new item availability 1 for available and 0 for unavailable ");
        boolean availability = Boolean.parseBoolean(clientHandler.readFromClient());
        clientHandler.writeToClient("Enter new item price: ");
        BigDecimal price = new BigDecimal(clientHandler.readFromClient());

        MenuItem item = new MenuItem(id, name,price,availability );

        if(menuServices.updateMenuItem(item)) {
            clientHandler.writeToClient("Item Updated successfully.");
        }
        else {
            clientHandler.writeToClient("Failed  updated.");
        }
    }

    private void deleteMenuItem() throws IOException, SQLException {
        clientHandler.writeToClient("Enter item ID to delete: ");
        int id = Integer.parseInt(clientHandler.readFromClient());
        if(menuServices.deleteMenuItem(id)) {
            clientHandler.writeToClient("Item Delete successfully.");
        }
        else {
            clientHandler.writeToClient("Failed  Delete.");
        }
    }
}

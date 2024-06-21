package com.cafeteria.userOperations;


import com.cafeteria.db.DBOperationsHandler;
import  com.cafeteria.notification.NotificationService;
import com.cafeteria.recommendation.RecommendationEngine;
import com.cafeteria.server.ClientHandler;

import java.io.IOException;
import java.sql.SQLException;


public class ChefOperationsHandler {
    private RollOutItemsService rollOutItemsService;
    private MenuCommonService MenuCommonService;
    private NotificationService notification;
    private ClientHandler clientHandler;
 private RecommendationEngine recommendationEngine;
    private DBOperationsHandler dbOperationsHandler;
    public ChefOperationsHandler(ClientHandler clientHandler) throws SQLException {
       this.dbOperationsHandler = new DBOperationsHandler();
        this.notification = new NotificationService();
        this.clientHandler = clientHandler;
        this.rollOutItemsService = new RollOutItemsService(clientHandler);
        this.recommendationEngine = new RecommendationEngine(dbOperationsHandler,clientHandler);
    }

    public void start() {
        while (true) {
            try {
                clientHandler.writeToClient(getMenuPrompt());

                int choice = Integer.parseInt(clientHandler.readFromClient());

                switch (choice) {
                    case 1:
                        recommendationEngine.generateRecommendations();
                        break;
                    case 2:
                        recommendationEngine.generateFinelvotingresult();
                        break;
                    case 3:

                        rollOutItemsService.start();
                        break;
                    case 4:
                        clientHandler.writeToClient("Enter message To send: ");
                        String message = clientHandler.readFromClient();
                       notification.sendNotification(message);
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
                "1. View Recomendation Menu  " +
                "2. view voting for previous rollout menu:   " +
                "3. perform Rollout Operations   " +
                "4. Send notification  " +
                "5. Exit\n";
    }

}

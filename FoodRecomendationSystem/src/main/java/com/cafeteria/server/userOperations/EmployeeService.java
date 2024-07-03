package com.cafeteria.server.userOperations;

import com.cafeteria.server.menu.MenuItem;
import com.cafeteria.server.menu.MenuService;
import com.cafeteria.server.feedback.VoterService;
import com.cafeteria.server.feedback.VotingItem;
import com.cafeteria.server.menu.RolloutMenuItem;
import com.cafeteria.server.recommendation.RecommendationEngine;
import com.cafeteria.server.menu.RolloutService;
import com.cafeteria.server.ClientHandler;
import com.cafeteria.server.auth.User;


import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

public class EmployeeService {

//    private RecommendationEngine recommendationEngine;
//    private ClientHandler clientHandler;
//    private VoterService voterService;
//    private MenuService menueService;
//    private RolloutService rolloutService;
//    private User user;
//
//    public EmployeeService(ClientHandler clientHandler,User user) throws SQLException {
//        this.clientHandler = clientHandler;
//        this.user = user;
//        this.rolloutService = new RolloutService();
//        this.menueService = new MenuService();
//        this.recommendationEngine = new RecommendationEngine();
//        this.voterService = new VoterService();
//    }
//
//
//    public void start() {
//        while (true) {
//            try {
//                clientHandler.writeToClient(getMenuPrompt());
//
//                int choice = Integer.parseInt(clientHandler.readFromClient());
//
//                switch (choice) {
//                    case 1:
//                        viewMainMenu();
//                        break;
//                    case 2:
//                        viewRolloutMenu();
//                        break;
//                    case 3:
//                        viewRecommendations();
//
//                        break;
//                    case 4:
//                        voteForRolloutItem(user);
//                        break;
//                    case 5:
//                        clientHandler.writeToClient("Exiting...");
//                        return;
//                    default:
//                        clientHandler.writeToClient("Invalid choice. Please try again.");
//                }
//            } catch (IOException e) {
//                clientHandler.writeToClient("Error reading input: " + e.getMessage());
//            } catch (SQLException e) {
//                clientHandler.writeToClient("Database error: " + e.getMessage());
//            } catch (NumberFormatException e) {
//                clientHandler.writeToClient("Invalid input format. Please enter a number.");
//            }
//        }
//    }
//
//    private String getMenuPrompt() {
//        return "Select an operation:  " +
//                "1. View Main Menu  " +
//                "2. View Rollout Menu  " +
//                "3.View Recommendations  " +
//                "4. Vote for Rollout Item  " +
//                "5. Exit\n";
//    }
//
//    private void viewMainMenu() {
//        List<MenuItem> menu = menueService.getMenu();
//        for (MenuItem item : menu) {
//            clientHandler.writeToClient(item.toString());
//        }
//        clientHandler.writeToClient("Main menu displayed.");
//    }
//
//    private void viewRolloutMenu() {
//        List<RolloutMenuItem> rolloutMenu = rolloutService.getRolloutMenu();
//        for (RolloutMenuItem item : rolloutMenu) {
//            clientHandler.writeToClient(item.toString());
//        }
//        clientHandler.writeToClient("Rollout menu displayed.");
//    }
//
//    private void voteForRolloutItem(User user) throws IOException, SQLException {
//        Random random = new Random();
//        int voteID = random.nextInt();
//
//        clientHandler.writeToClient("Enter Rollout Food Item ID to vote for: ");
//        int foodid = Integer.parseInt(clientHandler.readFromClient());
//
//        clientHandler.writeToClient("Enter Rollout ID to vote for: ");
//        int rolloutID = Integer.parseInt(clientHandler.readFromClient());
//
//        clientHandler.writeToClient("Enter your vote: ");
//        int vote = Integer.parseInt(clientHandler.readFromClient());
//        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
//        VotingItem item = new VotingItem(voteID,foodid,vote,user.getUserID(), currentTimestamp,rolloutID);
//        if(voterService.castvote(item))
//        {
//            clientHandler.writeToClient("Vote submitted successfully.");
//        }
//        else{
//            clientHandler.writeToClient(" Failed to Vote .");
//        }
//
//    }
//
//    private void viewRecommendations() throws IOException {
//        clientHandler.writeToClient("Enter number of products to view:");
//        int numberOfItems = Integer.parseInt(clientHandler.readFromClient());
//        recommendationEngine.displayTopRecommendationsFromDB(numberOfItems);
//    }
}

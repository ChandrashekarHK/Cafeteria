package com.cafeteria.userOperations;

public class EmployeeOperationsHandler {
//    private MenuCommonService menuCommonService;
//    private MenuSpecialServices menuSpecialServices;
//    private RolloutMenuCommonService rolloutService;
//    private RecommendationEngine recommendationEngine;
//    private ClientHandler clientHandler;
//    private User user;
//
//    public EmployeeOperationsHandler(ClientHandler clientHandler,User user) throws SQLException {
//        DBOperationsHandler dbOperationsHandler = new DBOperationsHandler();
//        this.menuCommonService = new MenuCommonService();
//        this.menuSpecialServices = new MenuSpecialServices(dbOperationsHandler);
//        this.rolloutService = new RolloutMenuCommonService();
//
//        this.clientHandler = clientHandler;
//        this.recommendationEngine = new RecommendationEngine(dbOperationsHandler,clientHandler);
//        this.user = user;
//    }
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
//        List<MenuItem> menu = menuCommonService.getMenu();
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
//        clientHandler.writeToClient("Enter Rollout Food Item ID to vote for: ");
//        int id = Integer.parseInt(clientHandler.readFromClient());
//
//        clientHandler.writeToClient("Enter your vote: ");
//        float vote = Integer.parseInt(clientHandler.readFromClient());
//
//        rolloutService.voteForRolloutMenuItem(id, vote,user.getUserID());
//        clientHandler.writeToClient("Vote submitted successfully.");
//    }
//
//    private void viewRecommendations() {
//         recommendationEngine.generateRecommendations();
//        clientHandler.writeToClient("Recommendations displayed.");
//    }
}

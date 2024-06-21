package com.cafeteria.recommendation;

import com.cafeteria.db.DBOperationsHandler;
import com.cafeteria.menu.RolloutMenuItem;
import com.cafeteria.server.ClientHandler;
import com.cafeteria.menu.RolloutMenuCommonService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RecommendationEngine {
    private ReportService reportService;
    private DBOperationsHandler dbOperationsHandler;
    private ClientHandler clientHandler;
    private RolloutMenuCommonService rolloutMenuCommonService;
 private VotingReportServices votingservices;
    public RecommendationEngine(DBOperationsHandler dbOperationsHandler,ClientHandler clientHandler) throws SQLException {
        this.reportService = new ReportService(dbOperationsHandler);
        this.votingservices = new VotingReportServices(dbOperationsHandler);
        this.dbOperationsHandler = dbOperationsHandler;
        this.rolloutMenuCommonService =new RolloutMenuCommonService();
        this.clientHandler = clientHandler;
    }

    public void displayTop10RecommendationsFromDB() {
        String query = "SELECT TOP 5 *  FROM RecommendedMenu ORDER BY averageRating DESC";
        ResultSet resultSet =dbOperationsHandler.executeQuery(query);
        try {
            while (resultSet != null && resultSet.next()) {
                int foodItemId = resultSet.getInt("foodItemId");
                String name = resultSet.getString("name");
                float rating = resultSet.getFloat("averageRating");
                clientHandler.writeToClient("Food Item ID: " + foodItemId + ", Adjusted Rating: " + rating +" Name:"+name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void generateRecommendations() {
        reportService.calculateAndStoreRecommendations();
        displayTop10RecommendationsFromDB();
    }

    public void generateFinelvotingresult()
    {
        votingservices.calculateAndStoreVotingResults();
        rolloutMenuCommonService.getRolloutMenu();
        List<RolloutMenuItem> menu = rolloutMenuCommonService.getRolloutMenu();
        for (RolloutMenuItem item : menu) {
            clientHandler.writeToClient(item.toString());
        }
        clientHandler.writeToClient("Wait till the menu executes");
    }
}

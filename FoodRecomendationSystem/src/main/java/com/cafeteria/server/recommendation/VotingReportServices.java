package com.cafeteria.server.recommendation;

import com.cafeteria.server.db.DBVotingService;
import com.cafeteria.server.feedback.VotingItem;
import com.cafeteria.server.menu.MenuItem;
import com.cafeteria.server.menu.MenuService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VotingReportServices {
    private DBVotingService voterService;
    private MenuService menuService;

    public VotingReportServices() throws SQLException {
        this.voterService = new DBVotingService();
        this.menuService = new MenuService();
    }

    public Map<Integer, List<VotingItem>> groupVotingByFoodItem(List<VotingItem> votingList) {
        Map<Integer, List<VotingItem>> groupedVoting = new HashMap<>();
        for (VotingItem voting : votingList) {
            groupedVoting.computeIfAbsent(voting.getFoodItemId(), k -> new ArrayList<>()).add(voting);
        }
        return groupedVoting;
    }

    public Map<Integer, Double> calculateTotalVotes(Map<Integer, List<VotingItem>> groupedVoting) {
        Map<Integer, Double> totalVotes = new HashMap<>();
        for (Map.Entry<Integer, List<VotingItem>> entry : groupedVoting.entrySet()) {
            int foodItemId = entry.getKey();
            List<VotingItem> votes = entry.getValue();
            double totalNumberOfVotes = 0;
            for (VotingItem voting : votes) {
                totalNumberOfVotes += voting.getVote();
            }
            totalVotes.put(foodItemId, totalNumberOfVotes);
        }
        return totalVotes;
    }

    public JSONObject getVotingResults() {
        List<VotingItem> votingList = voterService.getAllVoting();
        Map<Integer, List<VotingItem>> groupedVoting = groupVotingByFoodItem(votingList);
        Map<Integer, Double> totalVotes = calculateTotalVotes(groupedVoting);

        JSONArray votingResultsArray = new JSONArray();

        for (Map.Entry<Integer, Double> entry : totalVotes.entrySet()) {
            int foodItemId = entry.getKey();
            double totalNumberOfVotes = entry.getValue();

            MenuItem menuItem = menuService.getMenuIteamByFoodId(foodItemId);

            JSONObject votingResult = new JSONObject();
            votingResult.put("foodId", menuItem.getFoodItemID());
            votingResult.put("foodName", menuItem.getName());
            votingResult.put("totalVotes", totalNumberOfVotes);
            votingResultsArray.put(votingResult);
        }

        JSONObject response = new JSONObject();
        response.put("votingResult", votingResultsArray);
        return response;
    }
}

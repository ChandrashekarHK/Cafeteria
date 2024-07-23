package com.cafeteria.server.recommendation;

import org.json.JSONObject;

import java.sql.SQLException;

public class RecommendationEngine {
    private ReportService reportService;
    private VotingReportServices votingservices;

    public RecommendationEngine() throws SQLException {
        this.reportService = new ReportService();
        this.votingservices = new VotingReportServices();
    }

    public JSONObject generateRecommendation(int topN, boolean includeSentiment) {
        return reportService.createRecommendationsJSON(topN, includeSentiment);
    }

}

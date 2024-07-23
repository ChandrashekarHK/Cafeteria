package com.cafeteria.server.feedback;

import com.cafeteria.server.db.DBVotingService;
import com.cafeteria.server.recommendation.VotingReportServices;
import org.json.JSONObject;

import java.sql.SQLException;

public class VoterService {
    private DBVotingService votingservice;
    private VotingReportServices reportServices;

    public VoterService() throws SQLException {
        this.votingservice = new DBVotingService();
        this.reportServices = new VotingReportServices();
    }

    public boolean castVote(VotingItem item) {
        return votingservice.addVotingRecord(item);
    }

    public JSONObject getFinalVoteResult() {
        JSONObject voteResult = reportServices.getVotingResults();
        return voteResult;
    }

}

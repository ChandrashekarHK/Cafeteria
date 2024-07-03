package com.cafeteria.server.feedback;

import com.cafeteria.server.db.DBVotingservice;
import com.cafeteria.server.recommendation.VotingReportServices;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

public class VoterService {
    private DBVotingservice votingservice;
    private VotingReportServices reportServices;
    public VoterService() throws SQLException {
        this.votingservice =new DBVotingservice();
        this.reportServices = new VotingReportServices();
    }

    public List<VotingItem> getallVotes()
    {
        return votingservice.getAllVoting();
    }

    public boolean castvote(VotingItem item){
        return votingservice.addVotingrecord(item);
    }

    public JSONObject getFinalVoteResult()
    {
        JSONObject voteResult=reportServices.getVotingResults();
        return voteResult;
    }

}

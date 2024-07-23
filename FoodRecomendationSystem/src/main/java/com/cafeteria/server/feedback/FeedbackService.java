package com.cafeteria.server.feedback;

import com.cafeteria.server.db.DBFeedbackMenueService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FeedbackService {
    private DBFeedbackMenueService dbFeedbackMenueService;

    public FeedbackService() throws SQLException {
        this.dbFeedbackMenueService = new DBFeedbackMenueService();

    }

    public boolean giveFeedback(FeedbackItem item) throws IOException, SQLException {

        if (dbFeedbackMenueService.insertFeedback(item)) {
            return true;
        } else {
            return false;
        }

    }

    public List<FeedbackItem> viewRecentFeedback() {
        List<FeedbackItem> feedbackItemList = dbFeedbackMenueService.getFeedbackFromLast30Days();
        return feedbackItemList;
    }
}

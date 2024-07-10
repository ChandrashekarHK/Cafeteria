package com.cafeteria.server.feedback;

import com.cafeteria.server.db.DBDiscardItemFeedbackService;
import com.cafeteria.server.db.DBUserProfileService;

import java.util.List;

public class DiscardItemFeedbackService {
    private DBDiscardItemFeedbackService dbDiscardItemFeedbackService;
    private DBUserProfileService dbUserProfileService;

    public DiscardItemFeedbackService()
    {
        this.dbDiscardItemFeedbackService = new DBDiscardItemFeedbackService();
        this.dbUserProfileService = new DBUserProfileService();
    }

    public boolean addDiscardItemFeedback(DiscardItemFeedback feedback) {
        return dbDiscardItemFeedbackService.addDiscardItemFeedback(feedback);
    }

    public List<DiscardItemFeedback> getAllFeedback()
    {
        List<DiscardItemFeedback> allFeedback = dbDiscardItemFeedbackService.getAllFeedbacks();

        return allFeedback;
    }

}

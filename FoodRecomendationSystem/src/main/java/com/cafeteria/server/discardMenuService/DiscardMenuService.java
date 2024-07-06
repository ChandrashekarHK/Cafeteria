package com.cafeteria.server.discardMenuService;

import com.cafeteria.server.db.DBDiscardMenuService;
import com.cafeteria.server.feedback.FeedbackItem;
import com.cafeteria.server.feedback.FeedbackService;
import com.cafeteria.server.menu.MenuItem;
import com.cafeteria.server.menu.MenuService;
import com.cafeteria.server.recommendation.ReportService;
import com.cafeteria.server.recommendation.SentimentAnalysisService;
import org.json.JSONArray;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class DiscardMenuService extends ReportService {
    private DBDiscardMenuService dbDiscardMenuService;
    private FeedbackService feedbackService;
    private SentimentAnalysisService sentimentAnalysisService;
    private MenuService menuService;
    public DiscardMenuService() throws SQLException {
        super();
        this.dbDiscardMenuService = new DBDiscardMenuService();
        this.feedbackService = new FeedbackService();
        this.sentimentAnalysisService = new SentimentAnalysisService();
        this.menuService = new MenuService();
    }

    public void createAndSaveDiscardItems() throws SQLException {
        List<FeedbackItem> feedbackList = feedbackService.viewRecentFeedback();
        Map<Integer, List<FeedbackItem>> groupedFeedback = groupFeedbackByFoodItem(feedbackList);
        Map<Integer, Double> averageRatings = calculateAverageRatings(groupedFeedback);

        JSONArray discardItems = new JSONArray();

        for (Map.Entry<Integer, Double> entry : averageRatings.entrySet()) {
            int foodItemId = entry.getKey();
            double averageRating = entry.getValue();

            if (averageRating <= 2.0) {
                MenuItem foodItem = menuService.getMenuIteamByFoodId(foodItemId);
                Timestamp discardedDate = Timestamp.valueOf(LocalDateTime.now());

                DiscardMenuItem discardItem = new DiscardMenuItem();
                discardItem.setFoodId(foodItem.getFoodId());
                discardItem.setName(foodItem.getName());
                discardItem.setAverageRating(averageRating);
                discardItem.setDate(discardedDate);

                dbDiscardMenuService.addDiscardItem(discardItem);
            }
        }
    }

    public List<DiscardMenuItem> getAllDiscardItems() throws SQLException {
        return dbDiscardMenuService.getAllDiscardItems();
    }
}

package CafeteriaClient.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConsolePrintUtils {

    private static final int WIDTH = 25;

    private static String format(String text, int width) {
        return String.format("%-" + width + "." + width + "s", text);
    }

    public static void printMenuItems(JSONArray menuItemList) {
        int WIDTH = 20;
        System.out.println(format("FoodID", WIDTH) +
                format("Name", WIDTH) +
                format("  Price", WIDTH) +
                format("Availability", WIDTH) +
                format("Cuisine Type", WIDTH) +
                format("Spice Level", WIDTH) +
                format("Food Type", WIDTH) +
                format("Saltiness", WIDTH) +
                format("Sweetness", WIDTH) +
                format("Category", WIDTH));
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int menuItem = 0; menuItem < menuItemList.length(); menuItem++) {
            JSONObject item = menuItemList.getJSONObject(menuItem);
            System.out.println(formatMenuItem(item, WIDTH));
        }
    }

    public static void printDiscardItemFeedback(JSONArray discardItemFeedbackList) {
        int WIDTH = 35;
        System.out.println(format("UserID", WIDTH) + format("DiscardID", WIDTH) + format("Dislikes about food", WIDTH)
                + format("How they like", WIDTH) + format("Moms recipe", WIDTH));
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < discardItemFeedbackList.length(); i++) {
            JSONObject feedbackItem = discardItemFeedbackList.getJSONObject(i);
            System.out.println(format(feedbackItem.getString("userId"), WIDTH) +
                    format(String.valueOf(feedbackItem.getInt("discardID")), WIDTH) +
                    format(feedbackItem.getString("questionOne"), WIDTH) +
                    format(feedbackItem.getString("questionTwo"), WIDTH) +
                    format(feedbackItem.getString("questionThree"), WIDTH));
        }
    }

    public static void printRolloutMenuItems(JSONArray rolloutItemList) {
        System.out.println(format("FoodID", WIDTH) + format("RolloutID", WIDTH) + format("Name", WIDTH)
                + format("Rollout Date", WIDTH));
        System.out.println("------------------------------------------------------------------------------");
        for (int rolloutItem = 0; rolloutItem < rolloutItemList.length(); rolloutItem++) {
            JSONObject item = rolloutItemList.getJSONObject(rolloutItem);
            System.out.println(format(String.valueOf(item.getInt("foodId")), WIDTH) +
                    format(String.valueOf(item.getInt("rolloutID")), WIDTH) +
                    format(item.getString("name"), WIDTH) +
                    format(item.getString("rolloutDate"), WIDTH));
        }
    }

    public static void finalVotingResult(JSONArray votingResultList) {

        if (votingResultList == null || votingResultList.length() == 0) {
            System.out.println("***************** No one had voted yet. Please wait. *******************");
        } else {
            System.out.println(format("FoodID", WIDTH) + format("Name", WIDTH) + format("Vote", WIDTH));
            System.out.println("--------------------------------------------------------------------------");
            for (int vote = 0; vote < votingResultList.length(); vote++) {
                JSONObject item = votingResultList.getJSONObject(vote);
                System.out.println(format(String.valueOf(item.getInt("foodId")), WIDTH) +
                        format(item.getString("foodName"), WIDTH) +
                        format(String.valueOf(item.getDouble("totalVotes")), WIDTH));
            }
        }
    }

    public static void printDiscardMenuItems(JSONArray discardItemsList) {
        int WIDTH = 20;
        System.out.println(format("DiscardID", WIDTH) + format("FoodID", WIDTH) + format("Name", WIDTH)
                + format("Average Rating", WIDTH) + format("Date", WIDTH));
        System.out
                .println("------------------------------------------------------------------------------------------");

        for (int i = 0; i < discardItemsList.length(); i++) {
            JSONObject discardItem = discardItemsList.getJSONObject(i);
            System.out.println(format(String.valueOf(discardItem.getInt("discardId")), WIDTH) +
                    format(String.valueOf(discardItem.getInt("foodId")), WIDTH) +
                    format(discardItem.getString("name"), WIDTH) +
                    format(String.valueOf(discardItem.getDouble("averageRating")), WIDTH) +
                    format(discardItem.getString("date"), WIDTH));
        }
    }

    public static void printRecommendedMenu(JSONArray recommendedMenuList) {
        System.out.println(
                format("FoodID", WIDTH) + format("Name", WIDTH) + format("Rating", WIDTH) + format("Price", WIDTH));
        System.out.println("--------------------------------------------------------------------------------------");
        for (int recommendedItem = 0; recommendedItem < recommendedMenuList.length(); recommendedItem++) {
            JSONObject menu = recommendedMenuList.getJSONObject(recommendedItem);
            System.out.println(format(String.valueOf(menu.getInt("foodId")), WIDTH) +
                    format(menu.getString("name"), WIDTH) +
                    format(String.valueOf(menu.getDouble("averageRating")), WIDTH) +
                    format(menu.getBigDecimal("price").toString(), WIDTH));
        }
    }

    public static void printNotifications(JSONArray notificationList) {
        System.out.println(format("Message", WIDTH) + format("Date", WIDTH));
        System.out.println(
                "----------------------------------------------------------------------------------------------------------");
        for (int notificationItem = 0; notificationItem < notificationList.length(); notificationItem++) {
            JSONObject notification = notificationList.getJSONObject(notificationItem);
            System.out.println(format(notification.getString("message"), WIDTH) +
                    format(notification.getString("date"), WIDTH));
        }
    }

    private static String formatMenuItem(JSONObject item, int width) {
        return format(String.valueOf(item.getInt("foodItemID")), width) +
                format(item.getString("name"), width) +
                format(item.getBigDecimal("price").toString(), width) +
                format(String.valueOf(item.getBoolean("availability")), width) +
                format(item.getString("cuisineType"), width) +
                format(mapTasteLevel(item.getInt("spiceLevel")), width) +
                format(item.getString("foodType"), width) +
                format(mapTasteLevel(item.getInt("saltiness")), width) +
                format(mapTasteLevel(item.getInt("sweetness")), width) +
                format(item.getString("category"), width);
    }

    private static String mapTasteLevel(int tasteLevel) {
        switch (tasteLevel) {
            case 1:
                return "Very Low";

            case 2:
                return "Low";
            case 3:

                return "Normal";
            case 4:
                return "High";
            case 5:
                return "Very High";
            default:
                return "Not Applicable";
        }
    }

}

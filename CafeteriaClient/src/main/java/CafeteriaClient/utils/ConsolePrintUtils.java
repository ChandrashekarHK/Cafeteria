package CafeteriaClient.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConsolePrintUtils {

    private static final int WIDTH = 20;

    private static String format(String text, int width) {
        return String.format("%-" + width + "." + width + "s", text);
    }

    public static void printMenuItems(JSONArray menuItemList) {
        System.out.println(format("FoodID", WIDTH) + format("Name", WIDTH) + format("Price", WIDTH));
        System.out.println("------------------------------------------------------------");
        for (int menuItem = 0; menuItem < menuItemList.length(); menuItem++) {
            JSONObject item = menuItemList.getJSONObject(menuItem);
            System.out.println(format(String.valueOf(item.getInt("foodId")), WIDTH) +
                    format(item.getString("name"), WIDTH) +
                    format(item.getBigDecimal("price").toString(), WIDTH));
        }
    }

    public static void printRolloutMenuItems(JSONArray rolloutItemList) {
        System.out.println(format("FoodID", WIDTH) + format("Name", WIDTH) + format("Rollout Date", WIDTH));
        System.out.println("------------------------------------------------------------------------------");
        for (int rolloutItem = 0; rolloutItem < rolloutItemList.length(); rolloutItem++) {
            JSONObject item = rolloutItemList.getJSONObject(rolloutItem);
            System.out.println(format(String.valueOf(item.getInt("foodId")), WIDTH) +
                    format(item.getString("name"), WIDTH) +
                    format(item.getString("rolloutDate"), WIDTH));
        }
    }

    public static void finalVotingResult(JSONArray votingResultList) {
        System.out.println(format("FoodID", WIDTH) + format("Name", WIDTH) + format("Vote", WIDTH));
        System.out.println("--------------------------------------------------------------------------");
        for (int vote = 0; vote < votingResultList.length(); vote++) {
            JSONObject item = votingResultList.getJSONObject(vote);
            System.out.println(format(String.valueOf(item.getInt("foodId")), WIDTH) +
                    format(item.getString("foodName"), WIDTH) +
                    format(String.valueOf(item.getDouble("averageVote")), WIDTH));
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
        System.out.println("-------------------------------------------------------------------------------------");
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
        System.out.println("----------------------------------------------------------------------");
        for (int notificationItem = 0; notificationItem < notificationList.length(); notificationItem++) {
            JSONObject notification = notificationList.getJSONObject(notificationItem);
            System.out.println(format(notification.getString("message"), WIDTH) +
                    format(notification.getString("date"), WIDTH));
        }
    }
}

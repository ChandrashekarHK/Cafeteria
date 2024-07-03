package CafeteriaClient.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConsolePrintUtils {
        public static void printMenuItems(JSONArray menuItems) {
            System.out.println("Menu Items:");
            for (int i = 0; i < menuItems.length(); i++) {
                JSONObject item = menuItems.getJSONObject(i);
                System.out.println("FoodID: " + item.getInt("foodId") +
                        ", Name: " + item.getString("name") +
                        ", Price: " + item.getBigDecimal("price"));
            }
        }

        public static void printRolloutMenuItems(JSONArray rolloutItems) {
            System.out.println("Rollout Items:");
            for (int i = 0; i < rolloutItems.length(); i++) {
                JSONObject item = rolloutItems.getJSONObject(i);
                System.out.println("FoodID: " + item.getInt("foodId") +
                        ", Name: " + item.getString("name") +
                        ", Rollout Date: " + item.getString("rolloutDate"));
            }
        }
        public static void finalVotingResult(JSONArray votingresult) {
            System.out.println("Voting results:");
            for (int i = 0; i < votingresult.length(); i++) {
                JSONObject item = votingresult.getJSONObject(i);
                System.out.println("FoodID: " + item.getInt("foodId") +
                        ", Name: " + item.getString("foodName") +
                        ", Vote: "+item.getDouble("averageVote"));
            }
        }

        public static void printFeedback(JSONArray feedbackList) {
            System.out.println("Feedback List:");
            for (int i = 0; i < feedbackList.length(); i++) {
                JSONObject feedback = feedbackList.getJSONObject(i);
                System.out.println("FoodID: " + feedback.getString("foodId") +
                        ", Name: " + feedback.getString("foodName") +
                        ", Comment: " + feedback.getString("comment") +
                        ", Rating: " + feedback.getDouble("rating") +
                        ", Date: " + feedback.getString("feedbackDate"));
            }
        }

        public static void printRecommendedMenu(JSONArray recommendedMenuList) {
            System.out.println("Recommended Menu:");
            for (int i = 0; i < recommendedMenuList.length(); i++) {
                JSONObject menu = recommendedMenuList.getJSONObject(i);
                System.out.println("FoodID: " + menu.getInt("foodId") +
                        ", Name: " + menu.getString("name") +
                        ", Rating: " + menu.getDouble("averageRating") +
                        ", Price: " + menu.getBigDecimal("price"));
//                        ", Comment: " + menu.getString("framedSentiment")
            }
        }

        public static void printNotifications(JSONArray notificationList) {
            System.out.println("Notifications:");
            for (int i = 0; i < notificationList.length(); i++) {
                JSONObject notification = notificationList.getJSONObject(i);
                System.out.println("Message: " + notification.getString("message") +
                        ",  Date: " + notification.getString("notificationDate"));
            }
        }
    }

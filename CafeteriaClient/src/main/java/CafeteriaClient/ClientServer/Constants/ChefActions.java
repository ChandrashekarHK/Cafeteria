package CafeteriaClient.ClientServer.Constants;

public enum ChefActions {
    VIEW_MENU_ITEMS(1, "View Menu Items"),
    VIEW_RECOMMENDED_MENU(2, "View Recommend Menu"),
    ADD_TO_ROLLOUT_MENU(3, "Add Items to Rollout Menu"),
    VIEW_ROLLOUT_MENU(4, "View Rollout Menu"),
    VIEW_FINAL_VOTING(5, "View Final Voting"),
    VIEW_DISCARD_ITEM_FEEDBACK(6, "View Discard Menu ItemsFeedback"),
    SEND_NOTIFICATION(7, "Send Notification"),
    VIEW_DISCARD_MENU_ITEMS(8, "View Discard Menu Items"),
    LOGOUT(9, "Logout");

    private final int value;
    private final String description;

    ChefActions(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static ChefActions fromValue(int value) {
        for (ChefActions action : ChefActions.values()) {
            if (action.getValue() == value) {
                return action;
            }
        }
        throw new IllegalArgumentException("Invalid choice: " + value);
    }
}

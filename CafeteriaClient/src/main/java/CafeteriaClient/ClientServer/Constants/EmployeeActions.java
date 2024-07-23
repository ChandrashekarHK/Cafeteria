package CafeteriaClient.ClientServer.Constants;

public enum EmployeeActions {
    VIEW_MENU_ITEMS(1, "View Main Menu"),
    VIEW_NOTIFICATIONS(2, "View Notifications"),
    VIEW_RECOMMENDED_ROLLOUT_MENU(3, "View recommended rollout Menu"),
    VOTE_ROLLOUT_ITEMS(4, "Vote for Rollout Items"),
    PROVIDE_FEEDBACK(5, "Provide your Feedback on Food ordered"),
    PROVIDE_DISCARD_ITEM_FEEDBACK(6, "Provide your Feedback on Discard Food Item in notification"),
    SAVE_USER_PROFILE(7, "Update User Profile"),
    LOGOUT(8, "Logout");

    private final int value;
    private final String description;

    EmployeeActions(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static EmployeeActions fromValue(int value) {
        for (EmployeeActions action : EmployeeActions.values()) {
            if (action.getValue() == value) {
                return action;
            }
        }
        throw new IllegalArgumentException("Invalid choice: " + value);
    }
}

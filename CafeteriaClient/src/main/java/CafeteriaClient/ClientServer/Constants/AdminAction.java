package CafeteriaClient.ClientServer.Constants;

public enum AdminAction
{
    VIEW_MENU_ITEMS(1, "View Main Menu Item"),
    ADD_MENU_ITEM(2, "Add Menu Item"),
    UPDATE_MENU_ITEM(3, "Update Menu Item"),
    DELETE_MENU_ITEM(4, "Delete Menu Item"),
    VIEW_DISCARD_MENU_ITEMS(5, "View Discard Menu Items"),
    LOGOUT(6, "Logout");

    private final int value;
    private final String description;

    AdminAction(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static AdminAction fromValue(int value) {
        for (AdminAction action : AdminAction.values()) {
            if (action.getValue() == value) {
                return action;
            }
        }
        throw new IllegalArgumentException("Invalid choice: " + value);
    }
}

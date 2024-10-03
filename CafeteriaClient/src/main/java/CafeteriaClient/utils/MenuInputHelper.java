package CafeteriaClient.utils;

import java.util.Scanner;

public class MenuInputHelper {

    public static int getSpiceLevelInput() {
        return getValidInput("Spice Level",
                "Spice Level Options: 1 - Very Low, 2 - Low, 3 - Normal, 4 - High, 5 - Very High", 1, 5);
    }

    public static int getSaltinessInput() {
        return getValidInput("Saltiness Level",
                "Saltiness Options: 1 - Very Low, 2 - Low, 3 - Normal, 4 - High, 5 - Very High", 1, 5);
    }

    public static int getSweetnessInput() {
        return getValidInput("Sweetness Level",
                "Sweetness Options: 1 - Very Low, 2 - Low, 3 - Normal, 4 - High, 5 - Very High", 1, 5);
    }

    public static boolean getAvailabilityInput() {
        return getBooleanInput("Availability", "Enter 1 if food available, else 0", 3);
    }

    public static String getFoodTypeInput() {
        int input = getValidInput("Food Type", "Food Type Options: 1 - Vegetarian, 2 - Non-Vegetarian", 1, 2);
        switch (input) {
            case 1:
                return "Vegetarian";
            case 2:
                return "Non-Vegetarian";
            default:
                return "Unknown";
        }
    }

    public static String getCategoryInput() {
        int input = getValidInput("Category", "Category Options: 1 - Breakfast, 2 - Lunch, 3 - Dinner, 4 - Dessert", 1,
                4);
        switch (input) {
            case 1:
                return "Breakfast";
            case 2:
                return "Lunch";
            case 3:
                return "Dinner";
            case 4:
                return "Dessert";
            default:
                return "Unknown";
        }
    }

    private static int getValidInput(String inputType, String promptMessage, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        int MaxNumberOfattempts = 3;
        while (MaxNumberOfattempts > 0) {
            try {
                System.out.println(promptMessage);
                System.out.print("Please enter a valid " + inputType + ": ");
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.println(
                            "Invalid " + inputType + ". Please enter a value between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
            MaxNumberOfattempts--;
            if (MaxNumberOfattempts == 0) {
                System.out.println("Maximum attempts reached. Using default value Normal.");
                input = 3;
            }
        }
        return input;
    }

    private static boolean getBooleanInput(String inputType, String promptMessage, int attempts) {
        Scanner scanner = new Scanner(System.in);
        int input = -1;

        while (attempts > 0) {
            try {
                System.out.println(promptMessage);
                System.out.print("Please enter 1 for available or 0 for not available: ");
                input = Integer.parseInt(scanner.nextLine());
                if (input == 1 || input == 0) {
                    return input == 1;
                } else {
                    System.out.println("Invalid " + inputType + ". Please enter 1 or 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 0.");
            }
            attempts--;
            if (attempts == 0) {
                System.out.println("Maximum attempts reached. Using default value (not available).");
            }
        }
        return false;
    }

}

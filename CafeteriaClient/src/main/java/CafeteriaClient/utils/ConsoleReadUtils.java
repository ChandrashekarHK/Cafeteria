package CafeteriaClient.utils;


import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConsoleReadUtils {
    public static final Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String prompt) {
        int input = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return input;
    }
    public static boolean getBooleanInput(String prompt) {
        boolean input = false;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                input = scanner.nextBoolean();
                scanner.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return input;
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    public static BigDecimal getBigDecimalInput(String prompt) {
        BigDecimal input = null;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                input = scanner.nextBigDecimal();
                scanner.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return input;
    }

    public static double getDoubleInput(String prompt) {
        double input = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                input = scanner.nextDouble();
                scanner.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        return input;
    }

    public static double getRatingInput(String prompt) {
        double rating;
        while (true) {
            rating = getDoubleInput(prompt);
            if (rating >= 1 && rating <= 5) {
                break;
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }
        return rating;
    }


}
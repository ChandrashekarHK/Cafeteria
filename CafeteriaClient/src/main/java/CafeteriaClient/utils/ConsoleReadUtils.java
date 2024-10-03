package CafeteriaClient.utils;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleReadUtils {
    public static final Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String prompt) {
        int input = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                isValidInput = true;
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
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.print(prompt);
            try {
                input = scanner.nextBigDecimal();
                scanner.nextLine();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
                scanner.next();
            }
        }
        return input;
    }

    public static double getFloatInput(String prompt) {
        float input = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.print(prompt);
            try {
                input = scanner.nextFloat();
                input = Math.round(input * 100) / 100.0f;
                scanner.nextLine();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        return input;
    }
    public static int getVoteInput(String prompt) {
        int input = 0;
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                scanner.nextLine();

                if (input == 0 || input == 1) {
                    return input;
                } else {
                    System.out.println("Invalid input. Please enter 1 or 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }

            attempts++;
        }

        System.out.println("Maximum attempts reached. Defaulting vote to 0.");
        return 0;
    }

    public static double getRatingInput(String prompt) {
        double rating;
        while (true) {
            rating = getFloatInput(prompt);
            if (rating >= 1 && rating <= 5) {
                break;
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }
        return rating;
    }

}

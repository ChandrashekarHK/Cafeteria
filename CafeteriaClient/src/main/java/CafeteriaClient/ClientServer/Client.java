package CafeteriaClient.ClientServer;
import CafeteriaClient.utils.ConsoleReadUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 7777;

    public static void main(String[] args)
    {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)
        )
        {

            boolean logout = false;

            while (!logout) {
                System.out.println("1. Login");
                System.out.println("2. Exit");

                int choice = ConsoleReadUtils.getIntInput("Enter your choice: ");
                switch (choice) {
                    case 1:
                        UserClientController.authenticateUser(scanner, writer, reader);
                        break;
                    case 2:
                        logout = true;
                        System.out.println("Thank you for using Our Cafe App");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            }
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}

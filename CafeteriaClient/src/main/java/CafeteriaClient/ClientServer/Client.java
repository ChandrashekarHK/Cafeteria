package CafeteriaClient.ClientServer;
import CafeteriaClient.utils.ConsoleReadUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = System.getenv("SERVER_ADDRESS");
    private static final int SERVER_PORT = Integer.parseInt(System.getenv("SERVER_PORT"));
    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY_MS = 3000;

    public static void main(String[] args) {
        boolean logout = false;
        int retryAttempts = 0;

        while (!logout && retryAttempts < MAX_RETRIES) {
            try (
                    Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    Scanner scanner = new Scanner(System.in)
            ) {
                System.out.println("Connected to the server.");
                retryAttempts = 0;

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
            }
            catch (SocketException socketException)
            {
                System.out.println("Connection lost: " + socketException.getMessage());
                retryAttempts++;
                if (retryAttempts < MAX_RETRIES) {
                    System.out.println("Retrying connection... (" + retryAttempts + "/" + MAX_RETRIES + ")");
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                } else {
                    System.out.println("Max retries reached. Could not reconnect to the server.");
                    break;
                }
            }
            catch (IOException ioException)
            {
                System.out.println("Unable to connect to server: " + ioException.getMessage());
                retryAttempts++;
                if (retryAttempts < MAX_RETRIES) {
                    System.out.println("Retrying connection... (" + retryAttempts + "/" + MAX_RETRIES + ")");
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                } else {
                    System.out.println("Max retries reached. Could not connect to the server.");
                    break;
                }
            }
        }

        if (retryAttempts >= MAX_RETRIES) {
            System.out.println(" Please check your network connection and server status. Try later");
        }
    }
}

package com.cafeteria.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println("Server: " + serverMessage);

                if (serverMessage.contains("Enter")||serverMessage.contains("Select")) {
                    // Read user input from the console
                    String userInput = consoleInput.readLine();
                    // Send user input to the server
                    out.println(userInput);
                }

                if (serverMessage.equalsIgnoreCase("Invalid username or password.")
                        || serverMessage.equalsIgnoreCase("Operation for other roles not implemented.")
                        || serverMessage.startsWith("Logout")) {
                    break;  // Exit loop if login fails or operation is completed
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.cafeteria.server;

import com.cafeteria.auth.AuthService;
import com.cafeteria.auth.User;
import  com.cafeteria.auth.UserService;
import com.cafeteria.userOperations.AdminOperationsHandler;
import com.cafeteria.userOperations.ChefOperationsHandler;
import com.cafeteria.userOperations.EmployeeOperationsHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private AuthService authService;

    public ClientHandler(Socket socket) {

        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                writeToClient("Welcome to Cafeteria!");
                handleLogin();

        } catch (IOException | SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (in != null) in.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleLogin() throws IOException, SQLException {
        int attempts = 0;
        final int maxAttempts = 3;
        boolean isValidUser = false;
        String userID = null;
        String password = null;
        authService = new AuthService();
        UserService userService = new UserService();
        while (attempts < maxAttempts && !isValidUser) {
            writeToClient("Enter userID:");
            userID = readFromClient();
            writeToClient("Enter password:");
            password = readFromClient();

            isValidUser = authService.login(userID, password);
            if (!isValidUser) {
                attempts++;
                writeToClient("Invalid username or password. Attempt " + attempts + " of " + maxAttempts);
            }
        }

        if (isValidUser) {
            writeToClient("Login successful!");
            User user = userService.getUserById(userID);
            performOperationbyRole(user);
        } else {
            writeToClient("Maximum login attempts exceeded. Please try again later.");
        }
    }

    private void performOperationbyRole(User user) throws SQLException, IOException {
        switch (user.getRole().toLowerCase()) {
            case "admin":
                performAdminOperations();
                break;
            case "chef":
                writeToClient("Operation for Chef roles not implemented.");
                // performChefOperations();
                break;
            case "employee":
                writeToClient("Operation for Employee roles not implemented.");
                // performEmployeeOperations(user);
                break;
            default:
                writeToClient("Operation for other roles not implemented.");
                break;
        }
    }

    private void performAdminOperations() throws IOException, SQLException {
        AdminOperationsHandler adminHandler = new AdminOperationsHandler(this);
        adminHandler.start();
    }
    private void performChefOperations() throws IOException, SQLException {
        ChefOperationsHandler chefHandler = new ChefOperationsHandler(this);
        chefHandler.start();
    }
    private void performEmployeeOperations(User user) throws IOException, SQLException {
        writeToClient("Operation for other roles not implemented.");
//        EmployeeOperationsHandler employeeHandler = new EmployeeOperationsHandler(this,user);
//        employeeHandler.start();
    }
    public String readFromClient() throws IOException {
        return in.readLine();
    }
    public void writeToClient(String message) {
        out.println(message);
    }
}

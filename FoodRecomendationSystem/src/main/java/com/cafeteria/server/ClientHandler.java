package com.cafeteria.server;

import com.cafeteria.server.auth.AuthService;
import com.cafeteria.server.auth.User;
import com.cafeteria.server.auth.UserService;
import com.cafeteria.server.Controllers.AdminController;
import com.cafeteria.server.Controllers.ChefController;
import com.cafeteria.server.Controllers.EmployeeController;
import com.cafeteria.server.userOperations.EmployeeService;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private AuthService authService;
    private UserService userService;
    private AdminController adminController;
    private ChefController chefController;
    private EmployeeController employeeController;

    public ClientHandler(Socket socket) throws SQLException {

        this.clientSocket = socket;
        this.authService = new AuthService();
        this.userService = new UserService();
        this.adminController = new AdminController();
        this.chefController= new ChefController();
        this.employeeController =new EmployeeController();
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request;
            while ((request = reader.readLine()) != null) {
                JSONObject jsonRequest = new JSONObject(request);
                JSONObject jsonResponse = handleRequest(jsonRequest);
                writer.println(jsonResponse.toString());
            }
        } catch (IOException | SQLException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private JSONObject handleRequest(JSONObject jsonRequest) throws SQLException, IOException {
        String action = jsonRequest.getString("action");
        JSONObject jsonResponse = new JSONObject();

        switch (action) {
            case "AUTHENTICATION":
                String userId = jsonRequest.getString("userId");
                String password = jsonRequest.getString("password");
                boolean isAuthenticated = authService.login(userId, password);
                jsonResponse.put("success", isAuthenticated);
                if (!isAuthenticated) {
                    jsonResponse.put("error", "Invalid credentials");
                } else {
                    User user = userService.getUserById(userId);
                    jsonResponse.put("role",user.getRole());
                }
                break;
            case "ADMIN_ACTION":
                jsonResponse = adminController.start(jsonRequest);
                break;
            case "CHEF_ACTION":
                jsonResponse =chefController.handleChefActions(jsonRequest);
                break;
            case "EMPLOYEE_ACTION":
                jsonResponse = employeeController.handleEmploeeActions(jsonRequest);
                break;
            default:
                jsonResponse.put("success", false);
                jsonResponse.put("error", "Invalid action");
                break;
        }

        return jsonResponse;
    }

}

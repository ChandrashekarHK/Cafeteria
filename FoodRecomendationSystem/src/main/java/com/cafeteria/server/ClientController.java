package com.cafeteria.server;

import com.cafeteria.server.Controllers.AdminController;
import com.cafeteria.server.Controllers.ChefController;
import com.cafeteria.server.Controllers.EmployeeController;
import com.cafeteria.server.Controllers.UserController;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class ClientController implements Runnable {
    private Socket clientSocket;
    private UserController userController;
    private AdminController adminController;
    private ChefController chefController;
    private EmployeeController employeeController;

    public ClientController(Socket socket) throws SQLException {
        this.clientSocket = socket;
        this.userController = new UserController();
        this.adminController = new AdminController();
        this.chefController = new ChefController();
        this.employeeController = new EmployeeController();
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
                jsonResponse = userController.handleUserRequest(jsonRequest);
                break;
            case "ADMIN_ACTION":
                jsonResponse = adminController.handleAdminActions(jsonRequest);
                break;
            case "CHEF_ACTION":
                jsonResponse = chefController.handleChefActions(jsonRequest);
                break;
            case "EMPLOYEE_ACTION":
                jsonResponse = employeeController.handleEmployeeActions(jsonRequest);
                break;
            default:
                jsonResponse.put("success", false);
                jsonResponse.put("error", "Invalid action");
                break;
        }

        return jsonResponse;
    }
}

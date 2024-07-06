package CafeteriaClient.ClientServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.json.JSONObject;


public class UserClientController {

    static void authenticateUser(Scanner scanner, PrintWriter writer, BufferedReader reader) throws IOException
    {

        final int maxAttempts = 3;
        int attempts = 0;
        boolean isValidUser = false;

        while (attempts < maxAttempts && !isValidUser)
        {

            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("action", "AUTHENTICATION");
            jsonRequest.put("userId", userId);
            jsonRequest.put("password", password);

            writer.println(jsonRequest.toString());

            JSONObject jsonResponse = new JSONObject(reader.readLine());

            isValidUser = validateUser(jsonResponse);
            if (isValidUser) {
                String role = jsonResponse.getString("role");
                processRoleSpecificActions(role, writer, reader,userId);
            } else {
                attempts++;
                System.out.println("Login failed: " + jsonResponse.getString("error"));
                System.out.println("Invalid username or password. Attempt " + attempts + " of " + maxAttempts);
            }
        }
        if (!isValidUser && attempts >= maxAttempts) {
            System.out.println("Maximum login attempts exceeded. Please try again later.");
        }
    }


    private static boolean validateUser(JSONObject jsonResponse)
    {
        boolean success = jsonResponse.getBoolean("success");
        if (success) {
            return true;
        } else {
            return false;
        }
    }

    private static void processRoleSpecificActions(String role, PrintWriter writer, BufferedReader reader, String userId) throws IOException {
        switch (role) {
            case "Admin":
                AdminClientController.handleAdminActions(writer, reader);
                break;
            case "Chef":
               ChefClientController.handleChefActions(writer, reader);
                break;
            case "Employee":
               EmployeeClientController.handleEmployeeActions(writer, reader,userId);
                break;
            default:
                System.out.println("Invalid role.");
        }
    }

}
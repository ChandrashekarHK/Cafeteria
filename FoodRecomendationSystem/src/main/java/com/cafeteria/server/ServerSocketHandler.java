package com.cafeteria.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ServerSocketHandler {
    private static final int PORT = Integer.parseInt(System.getenv("CAFETERIA_SERVER_PORT"));
    private ServerSocket serverSocket;

    public ServerSocketHandler() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public void start() {
        System.out.println("Cafeteria Server is running...");
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientController(clientSocket)).start();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

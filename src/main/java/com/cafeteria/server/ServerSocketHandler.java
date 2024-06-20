package com.cafeteria.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketHandler {
    private static final int PORT = 12345;
    private ServerSocket serverSocket;

    public ServerSocketHandler() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public void start() {
        System.out.println("Cafeteria Server is running...");
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
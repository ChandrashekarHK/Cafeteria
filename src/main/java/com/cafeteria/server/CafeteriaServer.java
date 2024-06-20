package com.cafeteria.server;

public class CafeteriaServer {

    public static void main(String[] args) {
        try {

            ServerSocketHandler serverSocketHandler = new ServerSocketHandler();
            serverSocketHandler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

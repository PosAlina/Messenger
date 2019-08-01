package com.db.edu.Messenger.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(8081);
            ConnectionAcceptor connectionAcceptor = new ConnectionAcceptor(serverSocket);
            connectionAcceptor.start();
        } catch (IOException e) {
            System.out.println("Can`t start server");
        }
    }
}

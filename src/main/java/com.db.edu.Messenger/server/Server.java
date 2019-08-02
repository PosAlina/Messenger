package com.db.edu.Messenger.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Class for reading messages from client, processing and sending them to all clients
 * @author Alisa M, Anna K, Elisavetta Ph
 * @version 1.3
 */

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

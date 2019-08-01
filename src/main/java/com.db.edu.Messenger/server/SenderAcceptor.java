package com.db.edu.Messenger.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SenderAcceptor extends Thread {
    private static ServerSocket serverSocket;

    SenderAcceptor(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        System.out.println("sender acceptor");
        while (true) {
            try {
                Socket client = serverSocket.accept();
                ClientSenderSession clientSenderSession = new ClientSenderSession(client);
                clientSenderSession.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {

    }

}

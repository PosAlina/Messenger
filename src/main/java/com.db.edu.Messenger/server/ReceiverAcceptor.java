package com.db.edu.Messenger.server;

import com.db.edu.Messenger.client.ClientReceiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiverAcceptor extends Thread {
    private static ServerSocket serverSocket;

    ReceiverAcceptor(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        System.out.println("receiver acceptor");
        while (true) {
            try {
                Socket client = serverSocket.accept();
                ClientReceiverSession clientReceiverSession = new ClientReceiverSession(client);
                clientReceiverSession.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {

    }

}

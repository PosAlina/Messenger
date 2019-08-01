package com.db.edu.Messenger.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private static ServerSocket serverSocketSender;
    private static ServerSocket serverSocketReceiver;

    public static void main(String[] args) {
        try {
            serverSocketSender = new ServerSocket(8080);
            SenderAcceptor senderAcceptor = new SenderAcceptor(serverSocketSender);
            senderAcceptor.start();
        } catch (IOException e) {
            System.out.println("Can`t start sender server");
        }

        try {
            serverSocketReceiver = new ServerSocket(8081);
            ReceiverAcceptor receiverAcceptor = new ReceiverAcceptor(serverSocketReceiver);
            receiverAcceptor.start();
        } catch (IOException e) {
            System.out.println("Can`t start receiver server");
        }
    }
}

package com.db.edu.Messenger.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionAcceptor extends Thread {
    private static ServerSocket serverSocket;

    ConnectionAcceptor(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = serverSocket.accept();

                BufferedReader clientInputStream = new BufferedReader(
                        new InputStreamReader(
                                new BufferedInputStream(
                                        client.getInputStream())));

                String whoAreYou = clientInputStream.readLine();
                switch (whoAreYou) {
                    case "#sender":
                        ClientSenderSession clientSenderSession = new ClientSenderSession(client, "aaa");
                        clientSenderSession.start();
                        break;
                    case "#receiver":
                        ClientReceiverSession clientReceiverSession = new ClientReceiverSession(client, "aaa");
                        clientReceiverSession.start();
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {

    }

}

package com.db.edu.Messenger.server;

import com.db.edu.Messenger.server.command.SenderCommand;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class for establishing connection server with ReceiverClient
 * @author Alisa M, Anna K, Elisavetta Ph
 * @version 1.3
 */

public class ClientReceiverSession extends Thread {
    private Socket client;
    private String name;
    private ClientConnectionService clientConnectionService;

    ClientReceiverSession(Socket client, String name) {
        this.client = client;
        this.name = name;
        clientConnectionService = new ClientConnectionService(client);
        ClientConnectionService.addReceiver(clientConnectionService.getClientOutputStream());
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
        }
    }

    public void close() {
        try {
            if (client != null) {
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (clientConnectionService != null) {
            try {
                clientConnectionService.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.db.edu.Messenger.server;

import com.db.edu.Messenger.command.Command;
import com.db.edu.Messenger.command.SenderCommand;

import java.io.IOException;
import java.net.Socket;

public class ClientReceiverSession extends Thread {
    private Socket client;
    private String name;
    private ClientConnectionService clientConnectionService;

    ClientReceiverSession(Socket client, String name) {
        this.client = client;
        this.name = name;
        clientConnectionService = new ClientConnectionService(client);
        SenderCommand.receiversList.add(clientConnectionService.getClientOutputStream());
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

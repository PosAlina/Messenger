package com.db.edu.Messenger.server;

import com.db.edu.Messenger.command.Command;

import java.io.IOException;
import java.net.Socket;

public class ClientSenderSession extends Thread {
    private Socket client;
    private String name;
    private ClientConnectionService clientConnectionService;

    ClientSenderSession(Socket client, String name) {
        this.client = client;
        this.name = name;
        clientConnectionService = new ClientConnectionService(client);
    }

    @Override
    public void run() {
        logClientMessages();
    }

    public void logClientMessages() {
        String messageStatus = "OK";

        try {
            while (!isInterrupted()) {
                String messageClientRepresentation = clientConnectionService.getClientLogMessage();
                Command messageInternalRepresentation = CommandRequestHandler.parseClientMessage(messageClientRepresentation);
                messageInternalRepresentation.execute();
                clientConnectionService.passCommandExecutionStatus(messageStatus);
                messageStatus = "OK";
            }
        } catch (Exception e) {
            System.out.println("Client closed connection");
        } finally {
            close();
        }
    }

    public void close() {
        try {
            if (client != null) {
                client.close();
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }

        if (clientConnectionService != null) {
            try {
                clientConnectionService.close();
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }
}

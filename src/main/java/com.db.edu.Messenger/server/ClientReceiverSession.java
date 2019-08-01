package com.db.edu.Messenger.server;

import com.db.edu.Messenger.command.Command;
import com.db.edu.Messenger.command.SenderCommand;

import java.io.IOException;
import java.net.Socket;

public class ClientReceiverSession extends Thread {
    private Socket client;
    private ClientConnectionService clientConnectionService;

    ClientReceiverSession(Socket client) {
        this.client = client;
        clientConnectionService = new ClientConnectionService(client);
        SenderCommand.receiversList.add(clientConnectionService.getClientOutputStream());
    }

    @Override
    public void run() {
        logClientMessages();
    }

    public void logClientMessages() {
        String MessageStatus = "OK";

        try {
            while (!isInterrupted()) {
                String messageClientRepresentation = clientConnectionService.getClientLogMessage();
                Command messageInternalRepresentation = CommandRequestHandler.parseClientMessage(messageClientRepresentation);
                messageClientRepresentation.execute();
                clientConnectionService.passCommandExecutionStatus(MessageStatus);
                MessageStatus = "OK";
            }
        } catch (Exception e) {
            System.out.println("Client closed connection");
        }

        close();
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

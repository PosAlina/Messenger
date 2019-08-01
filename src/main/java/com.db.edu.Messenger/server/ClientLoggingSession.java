package com.db.edu.Messenger.server;

import java.io.IOException;
import java.net.Socket;

public class ClientLoggingSession extends Thread {
    private Socket client;
//    private LoggerController loggerController;
    private ClientConnectionService clientConnectionService;

    ClientLoggingSession(Socket client) {
//        this.loggerController = loggerController;
        this.client = client;
        clientConnectionService = new ClientConnectionService(client);
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
//                Command messageInternalRepresentation = LogRequestHandler.parseClientMessage(messageClientRepresentation);
//                try {
//                    if (messageInternalRepresentation instanceof FlushCommand) {
//                        loggerController.flush();
//                    } else {
//                        loggerController.log(messageInternalRepresentation);
//                    }
//                } catch (LogSaverException e) {
//                    MessageStatus = e.getMessage();
//                }

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

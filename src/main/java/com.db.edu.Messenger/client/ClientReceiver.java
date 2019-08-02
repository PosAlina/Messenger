package com.db.edu.Messenger.client;

import com.db.edu.Messenger.client.clientProcessor.ClientConnector;
import com.db.edu.Messenger.client.clientProcessor.ServiceCommands;
import com.db.edu.Messenger.exceptions.ClientConnectionException;

/**
 * Class for receiving messages from server and print them into console
 * @author Alina P, Anastasiya M
 * @version 1.3
 */

public class ClientReceiver {
    private ClientReceiver() { }

    public static void main(String[] args) {
        try {
            ClientConnector clientConnector = new ClientConnector();

            try {
                clientConnector.send(ServiceCommands.receiver());
                while (true) {
                    String message = clientConnector.receive();
                    clientConnector.print(message);
                }

            } catch (ClientConnectionException e) {
                clientConnector.closeConnection();
                throw new ClientConnectionException("Connection was closed");
            }

        } catch (ClientConnectionException e) {
            System.out.println(e.getMessage());
        }
    }
}
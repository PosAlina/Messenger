// отвечает за установку (accept) сетевого соединения, инкапсулирует все до бизнес-логики

package com.db.edu.Messenger.server;

import com.db.edu.Messenger.server.command.Command;
import com.db.edu.Messenger.server.command.HistoryCommand;
import com.db.edu.Messenger.server.command.SenderCommand;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class for establishing connection server with clients
 * @author Alisa M, Anna K, Elisavetta Ph
 * @version 1.3
 */

public class ClientConnectionService {
    private static List<BufferedWriter> receiversList = new CopyOnWriteArrayList<>();

    public static void addReceiver(BufferedWriter receiver) {
        ClientConnectionService.receiversList.add(receiver);
    }

    private BufferedReader clientInputStream;
    private BufferedWriter clientOutputStream;

    public ClientConnectionService(Socket client) {
        try {
            clientInputStream = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    client.getInputStream())));
            clientOutputStream = new BufferedWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream(
                                    client.getOutputStream())));
        } catch (IOException e) {
//            e.printStackTrace();
            close();
//            throw new FailEstablishConnectionException("can`t establish server connection to client", e);
        }
    }

    public Command parseClientMessage (String message) {
        String[] clientCommand = message.split("\\s+", 3);
        switch (clientCommand[1]) {
            case "/snd":
                return new SenderCommand(clientCommand[0], clientCommand[2], receiversList);
            case "/hist":
                return new HistoryCommand(clientOutputStream);
            default:
                return null;
        }
    }

    public String getClientLogMessage() {
        try {
            return clientInputStream.readLine();
        } catch (IOException e) {
//            e.printStackTrace();
//            throw new FailReceiveClientMessageException("can`t read client message", e);
        }
        return null;
    }

    public BufferedWriter getClientOutputStream() { return clientOutputStream; }

    public void passCommandExecutionStatus(String commandExecutionStatus) {
        try {
            clientOutputStream.write(commandExecutionStatus);
            clientOutputStream.newLine();
            clientOutputStream.flush();
        } catch (IOException e) {
//            e.printStackTrace();
//            throw new FailSendLogStatusToClientException("can`t send log execution status to client", e);
        }
    }

    public void close() {
        try {
            if (clientOutputStream != null) {
                clientOutputStream.close();
            }
            if (clientInputStream != null) {
                clientInputStream.close();
            }
        } catch (IOException e) {
//            e.printStackTrace();
//            throw new ShutdownServerException("can`t shutdown server", e);
        }
    }
}

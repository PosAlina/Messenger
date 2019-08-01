// отвечает за установку (accept) сетевого соединения, инкапсулирует все до бизнес-логики

package com.db.edu.Messenger.server;


import java.io.*;
import java.net.Socket;

public class ClientConnectionService {
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
            e.printStackTrace();
            close();
//            throw new FailEstablishConnectionException("can`t establish server connection to client", e);
        }
    }

    public String getClientLogMessage() {
        try {
            return clientInputStream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
//            throw new ShutdownServerException("can`t shutdown server", e);
        }
    }
}

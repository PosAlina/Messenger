package com.db.edu.Messenger.client;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.db.edu.Messenger.exceptions.ClientConnectionException;

public class ClientSender {
    private final static int PORT = 8080;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static BufferedReader console;
    private static String userName;

    public static void main(String[] args) {
        boolean connectionOpen = false;
        try (final Socket server = new Socket("localhost", PORT)) {
            connectionOpen = openConnection(connectionOpen, server);
            new Thread(() -> {
                try {
                    while (true) {
                        in.readLine();
                    }
                } catch (IOException e) {
                    try {
                        closeConnection();
                    } catch (ClientConnectionException e1) {
                        e1.printStackTrace();
                    }
                }
            }).start();
            authorization();
            while (connectionOpen) {
                String message = readMessage();
                String data = parseDate();

                message = parseMessage(message);
                if ("/close".equals(message)) {
                    break;
                }
                if ("/wrong".equals(message)) {
                    continue;
                }

                connectionOpen = send(data + " " + message);
            }
            closeConnection();
        } catch (IOException | ClientConnectionException e) {
            System.out.println("Connection isn`t establish");
        }
    }

    private static String parseMessage(String message) {
        String[] commandAndMessage = message.split(" ");

        switch (commandAndMessage[0]) {
            case ("/snd"): {
                return message;
            }
            case ("/hist"): {
                return "/hist";
            }
            case ("/close"): {
                return "/close";
            }
            default: {
                return "/wrong";
            }
        }
    }

    private static String parseDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    private static boolean openConnection(boolean connectionOpen, Socket server) throws ClientConnectionException {
        if (connectionOpen) return true;
        try {
            in = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    server.getInputStream())));
            out = new BufferedWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream(
                                    server.getOutputStream())));
            console = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    System.in)));
            return true;
        } catch (IOException e) {
            throw new ClientConnectionException("Don't connect in client part");
        }
    }

    private static void authorization() throws ClientConnectionException {
        try {
            inputUserName();
        } catch (IOException e) {
            throw new ClientConnectionException("Don't connect in client part");
        }
    }

    private static void inputUserName() throws IOException {
        System.out.println("Please, input your name with command '/chid'.");
        while (true) {
            String message = readMessage();
            String[] commandAndMessage = message.split(" ");
            if ("/chid".equals(commandAndMessage[0])) {
                if (commandAndMessage.length <= 1) {
                    System.out.println("Please, input your other name with command '/chid'.");
                } else {
                    userName = commandAndMessage[1];
                    checkCorrectName(userName);
                    break;
                }
            }
        }
    }

    private static void checkCorrectName(String userName) throws IOException {
        out.write("#sender " + userName);
    }

    private static void closeConnection() throws ClientConnectionException {
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            throw new ClientConnectionException("Error in close connection");
        }
    }

    private static boolean send(String message) {
        try {
            out.write(message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private static String readMessage() throws IOException {
        return console.readLine();
    }
}
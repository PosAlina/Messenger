package com.db.edu.Messenger.client;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        } catch (IOException e) {
            e.printStackTrace();
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

    private static boolean openConnection(boolean connectionOpen, Socket server) {
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
            //inputUserName();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static void inputUserName() throws IOException {
        while (true) {
            String message = readMessage();
            String[] commandAndMessage = message.split(" ");
            if ("/chid".equals(commandAndMessage[0]))  {
                userName = commandAndMessage[1];
            }
        }
    }

    private static void closeConnection() {
        try {
            out.close();
            in.close();
        } catch (IOException e) {
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
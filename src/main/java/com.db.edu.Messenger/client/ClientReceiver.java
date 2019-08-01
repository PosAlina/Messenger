package com.db.edu.Messenger.client;

import java.io.*;
import java.net.Socket;

public class ClientReceiver {
    private final static int PORT = 8081;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        boolean connectionOpen = false;
        try (final Socket server = new Socket("localhost", PORT)) {
            connectionOpen = openConnection(connectionOpen, server);
            while (connectionOpen) {
                String message = receiveMessage();
                printMessage(message);
            }
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static void closeConnection() {
        try {
            out.close();
            in.close();
        } catch (IOException e) {
        }
    }

    private static String receiveMessage() throws IOException {
        return in.readLine();
    }

    private static void printMessage(String message) throws IOException {
        System.out.println(message);
    }
}
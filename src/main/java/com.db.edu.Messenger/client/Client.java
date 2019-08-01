package com.db.edu.Messenger.client;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    private final static int PORT = 8080;
    private static BufferedReader in;
    private static BufferedWriter out;


    public static void main(String[] args) {
        boolean connectionOpen = false;
        try(final Socket server = new Socket("localhost", 8080)) {
            connectionOpen = openConnection(connectionOpen, server);
            try (BufferedReader console =
                         new BufferedReader(
                                 new InputStreamReader(
                                         new BufferedInputStream(
                                                 System.in)))) {
                while (connectionOpen) {
                    String message = console.readLine();
                    String data = parseDate();
                    message = parseMessage(message);
                    if ("/close".equals(message)) { break; }

                    System.out.println(data + " " + message);
                    connectionOpen = send(data + " " + message);
                }
                connectionOpen = closeConnection(connectionOpen);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String parseMessage(String message) {
        String[] commandAndMessage = message.split(" ");

        switch(commandAndMessage[0]) {
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
                return "";
            }
        }
    }

    private static String parseDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
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
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static boolean closeConnection(boolean connectionOpen) {
        if (!connectionOpen) return false;
        try {
            out.close();
            in.close();
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    private static boolean send(String message) {
        try {
                out.write(message);
                out.newLine();
                out.flush();
                if (!("OK".equals(in.readLine()))) {
                    return false;
                }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
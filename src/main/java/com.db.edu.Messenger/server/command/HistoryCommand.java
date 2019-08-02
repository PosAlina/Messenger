package com.db.edu.Messenger.server.command;

import java.io.*;

/**
 * Class for processing history for clients
 * @author Alisa M, Anna K, Elisavetta Ph
 * @version 1.3
 */

public class HistoryCommand extends Command {
    private BufferedWriter receiver;
    private final String messageDate;
    private BufferedReader fileReader;
    private final String fileName = "history.txt";

    public HistoryCommand(String messageDate, BufferedWriter receiver) {
        this.messageDate = messageDate;
        try {
            fileReader = new BufferedReader(new FileReader(new File(fileName)));
        } catch (IOException e) {
            System.out.println(
                    "Something went wrong with opening file with history! Sorry:("
            );
        }
        this.receiver = receiver;
    }

    @Override
    String generateAns() {
        String line;
        StringBuilder resultLine = new StringBuilder();
        do {
            try {
                line = fileReader.readLine();
            } catch (IOException e) {
                System.out.println(
                        "Something went wrong with reading file with history! Sorry:("
                );
                line = null;
            }
            resultLine.append(line + System.lineSeparator());
        } while (line != null);
        return resultLine.toString();
    }

    @Override
    void send() {
        try {
            receiver.write(generateAns());
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Can't send history of messages to client");
        }
    }
}
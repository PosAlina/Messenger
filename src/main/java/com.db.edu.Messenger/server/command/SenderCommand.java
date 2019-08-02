package com.db.edu.Messenger.server.command;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Class for processing messages from clients
 * @author Alisa M, Anna K, Elisavetta Ph
 * @version 1.3
 */

public class SenderCommand extends Command {
    private List<BufferedWriter> receiversList;

    //private final Date date;
    private final SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
    private String dateInString;
    private final String message;

    public SenderCommand(String dateInString, String message, List<BufferedWriter> receiversList) {
        this.message = message;
        this.dateInString = dateInString;
        this.receiversList = receiversList;
    }

    @Override
    String generateAns() {
        return dateInString + " " + message;
    }

    @Override
    void send() {
        String answer = generateAns();
        for(BufferedWriter receiver: receiversList){
            try {
                receiver.write(answer);
                receiver.newLine();
                receiver.flush();
            } catch (IOException e) {
                System.out.println("Can't send to message");
            }
        }

    }
}
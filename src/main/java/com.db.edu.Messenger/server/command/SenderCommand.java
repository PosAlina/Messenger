package com.db.edu.Messenger.server.command;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SenderCommand extends Command {
    protected static List<BufferedWriter> receiversList = new CopyOnWriteArrayList<>();

    //private final Date date;
    private final SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
    private String dateInString;
    private final String message;

    public SenderCommand(String dateInString, String message) {
        this.message = message;
        this.dateInString = dateInString;
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
package com.db.edu.Messenger.command;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SenderCommand extends Command {
    public static List<BufferedWriter> receiversList = new ArrayList<BufferedWriter>();

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
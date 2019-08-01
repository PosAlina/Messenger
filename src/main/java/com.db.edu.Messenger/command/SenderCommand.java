package com.db.edu.Messenger.command;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SenderCommand extends Command {


    private List<BufferedWriter> receiversList;
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
        return dateInString + message;
    }

    @Override
    void send() {
        for(BufferedWriter receiver: receiversList){
            try {
                receiver.write(generateAns());
            } catch (IOException e) {
                System.out.println("Can't send to message");
            }
        }

    }
}

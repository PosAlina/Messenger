package com.db.edu.Messenger.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryCommand extends Command {
    Date date;
    SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
    public HistoryCommand(String dateInString) {
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            System.out.println("Error! Wrong date format!");
        }
    }

    @Override
    String generateAns() {
        return null;
    }

    @Override
    void send() {

    }
}

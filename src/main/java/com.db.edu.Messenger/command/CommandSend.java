package com.db.edu.Messenger.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandSend extends Broadcast implements CommandInterface{

    private final Date date;
    private final SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
    private final String message;
    public CommandSend(String dateInString, String message) {
        Date date1;
        try {
            date1 = formatter.parse(dateInString);
        } catch (ParseException e) {
            date1 = null;
            System.out.println("Error! Wrong date format!");
        }
        date = date1;
        this.message = message;
    }
    @Override
    public void executeCommand() {

    }
}

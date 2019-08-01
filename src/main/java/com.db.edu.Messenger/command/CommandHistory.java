package com.db.edu.Messenger.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandHistory implements CommandInterface{
    Date date;
    SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
    public CommandHistory(String dateInString) {
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            System.out.println("Error! Wrong date format!");
        }
    }
    
    @Override
    public void executeCommand() {
        
    }
}

package com.db.edu.Messenger.server.command;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class SenderCommand extends Command {
    private List<BufferedWriter> receiversList;

    //private final Date date;
    private final SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
    private String messageDate;
    private final String message;
    private BufferedWriter fileWriter;
    private final String fileName = "history.txt";

    public SenderCommand(String messageDate, String message, List<BufferedWriter> receiversList) {
        this.message = message;
        this.messageDate = messageDate;
        this.receiversList = receiversList;
        try {
            fileWriter = new BufferedWriter(new FileWriter(new File(fileName), true));
        } catch (IOException e) {
            System.out.println(
                    "Something went wrong with opening file with history! Sorry:("
            );
        }
    }

    @Override
    String generateAns() {
        return messageDate + " " + message;
    }

    @Override
    void send() {
        String answer = generateAns();
        try {
            fileWriter.write(answer);
            fileWriter.newLine();
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Can't save message");
        }

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
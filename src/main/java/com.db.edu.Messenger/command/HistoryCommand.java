package com.db.edu.Messenger.command;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryCommand extends Command {
    private BufferedWriter receiver;
    private final String dateInString;
    private BufferedReader reader;
    private final File file;
    private FileReader fileReader;

    public HistoryCommand(String dateInString) {
        this.dateInString = dateInString;
        file = new File("1.txt"); //TODO change
        try {
            fileReader = new FileReader(file);
        } catch (IOException e) {
            System.out.println(
                    "Something went wrong with opening file with history! Sorry:("
            );
        }
        reader = new BufferedReader(fileReader);
        receiver = null;//TODO change
    }

    @Override
    String generateAns() {
        String line = "";
        String resultLine = "";
        do {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println(
                        "Something went wrong with reading file with history! Sorry:("
                );
                line = null;
            }
            resultLine +=line;
        } while (line != null);
        return resultLine;
    }

    @Override
    void send() {
        try {
            receiver.write(generateAns());
        } catch (IOException e) {
            System.out.println("Can't send to history of messages");
        }
    }
}
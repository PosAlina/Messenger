package com.db.edu.Messenger.server;

public class CommandRequestHandler {
    public static Command parseClientMessage (String message) {
        String[] clientCommand = message.split("\\s+", 2);
        switch (clientCommand[1]) {
            case "/snd":
                return new CommandSend(clientCommand[0], clientCommand[2]);
            case "/hist":
                return new CommandHistory(clientCommand[0]);
            default:
                return null;
        }
    }
}

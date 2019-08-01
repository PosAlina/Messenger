package com.db.edu.Messenger.server;

import com.db.edu.Messenger.command.CommandHistory;
import com.db.edu.Messenger.command.CommandSend;
import com.db.edu.Messenger.command.CommandInterface;

public class CommandRequestHandler {
    public static CommandInterface parseClientMessage (String message) {
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

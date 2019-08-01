package com.db.edu.Messenger.server;

import com.db.edu.Messenger.command.Command;
import com.db.edu.Messenger.command.HistoryCommand;
import com.db.edu.Messenger.command.SenderCommand;

public class CommandRequestHandler {
    public static Command parseClientMessage (String message) {
        String[] clientCommand = message.split("\\s+", 3);
        switch (clientCommand[1]) {
            case "/snd":
                return new SenderCommand(clientCommand[0], clientCommand[2]);
            case "/hist":
                return new HistoryCommand(clientCommand[0]);
            default:
                return null;
        }
    }
}

package com.db.edu.Messenger.server.command;

/**
 * Class for wrapper messages from clients
 * @author Alisa M, Anna K, Elisavetta Ph
 * @version 1.3
 */

public abstract class Command {
    abstract String generateAnswer();

    abstract void send();

    public void execute(){
        send();
    }
}
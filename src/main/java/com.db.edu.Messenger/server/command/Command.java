package com.db.edu.Messenger.server.command;

public abstract class Command {
    abstract String generateAns();

    abstract void send();

    public void execute(){
        send();
    }
}
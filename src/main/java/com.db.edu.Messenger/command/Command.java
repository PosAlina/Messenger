package com.db.edu.Messenger.command;

public abstract class Command {
    abstract String generateAns();

    abstract void send();

    public void execute(){
        send();
    }
}
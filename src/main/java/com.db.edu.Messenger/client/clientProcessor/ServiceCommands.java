package com.db.edu.Messenger.client.clientProcessor;

public class ServiceCommands {
    private static final String sender = "#sender";
    private static final String receiver = "#receiver";
    private final String close = "#close";

    public static String sender() { return sender; }

    public static String receiver() { return receiver; }

    public String close() { return close; }
}
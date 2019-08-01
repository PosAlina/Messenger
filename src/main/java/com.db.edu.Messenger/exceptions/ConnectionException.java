package com.db.edu.Messenger.exceptions;

public class ConnectionException extends Exception {
    public ConnectionException() {
        super("Error connection");
    }

    public ConnectionException(String exception) {
        super(exception);
    }
}

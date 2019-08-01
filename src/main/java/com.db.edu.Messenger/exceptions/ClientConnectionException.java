package com.db.edu.Messenger.exceptions;

public class ClientConnectionException extends ConnectionException {
    public ClientConnectionException() {
        super("Error connection in Client");
    }

    public ClientConnectionException(String exception) {
        super(exception);
    }
}

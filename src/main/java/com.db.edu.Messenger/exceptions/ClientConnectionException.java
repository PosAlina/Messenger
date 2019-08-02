package com.db.edu.Messenger.exceptions;

/**
 * Class for wrapper client exceptions
 * @author Alina P, Anastasiya M
 * @version 1.3
 */

public class ClientConnectionException extends ConnectionException {
    public ClientConnectionException() {
        super("Error connection in Client");
    }

    public ClientConnectionException(String exception) {
        super(exception);
    }
}

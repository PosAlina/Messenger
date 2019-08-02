package com.db.edu.Messenger.exceptions;

/**
 * Class for wrapper exceptions
 * @author Alina P, Anastasiya M
 * @version 1.3
 */

public class ConnectionException extends Exception {
    public ConnectionException() {
        super("Error connection");
    }

    public ConnectionException(String exception) {
        super(exception);
    }
}

package com.db.edu.Messenger.command;

import java.util.List;

public class Broadcast extends AnswerSender {
    static public List<String> sendToList;
    static public void addToSendList(String newClient) {
        sendToList.add(newClient);
    }
    @Override
    void send() {

    }
}

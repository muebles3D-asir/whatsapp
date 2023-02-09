package com.julen_demiguel.whatsapp.Models;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Chat extends RealmObject {

    @PrimaryKey
    private String id;
    private ArrayList<Message> messages;
    private User[] participants;

    public Chat() {}

    public Chat(User[] participants) {
//        this.id = MyApplication.chatID.incrementAndGet();
        this.participants = participants;
    }

    public Chat(ArrayList<Message> messages, User[] participants) {
        this(participants);
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public User[] getParticipants() {
        return participants;
    }

    public void setParticipants(User[] participants) {
        this.participants = participants;
    }
}

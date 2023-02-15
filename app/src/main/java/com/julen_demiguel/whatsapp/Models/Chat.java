package com.julen_demiguel.whatsapp.Models;

import com.julen_demiguel.whatsapp.Application.MyApplication;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Chat extends RealmObject {

    @PrimaryKey
    private int id;
    private RealmList<Message> messages;
    private RealmList<User> participants;


    public Chat() {}

    public Chat(RealmList<User> participants) {
      this.id = MyApplication.chatID.incrementAndGet();
        this.participants = participants;
    }

    public Chat(RealmList<Message> messages, RealmList<User> participants) {
        this(participants);
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public User getOtherUser() {
        for (User user : participants) {
            if (user.getId() != MyApplication.currentUser.getId())
                return user;
        }
        return null;
    }

    public String getLastMessage() {
        return messages.get(messages.size()-1).getText();
    }

    public RealmList<Message> getMessages() {
        return messages;
    }

    public void setMessages(RealmList<Message> messages) {
        this.messages = messages;
    }

    public RealmList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(RealmList<User> participants) {
        this.participants = participants;
    }
}

package com.julen_demiguel.whatsapp.Models;

import com.julen_demiguel.whatsapp.Application.MyApplication;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChatGroup extends RealmObject {

    @PrimaryKey

    private int id;
    private RealmList<Message> messages;
    private RealmList<User> participants;

    public ChatGroup() {
    }


    public ChatGroup(RealmList<User> participants) {
        this.id = MyApplication.chatID.incrementAndGet();
        this.participants = participants;
    }

    public ChatGroup(RealmList<User> participants, RealmList<Message> messages) {
        this(participants);
        this.messages = messages;
    }

    public Boolean addUsr(User newUser) {
        if (participants.contains(newUser)) return false;

        participants.add(newUser);
        return true;
    }


                public int getId() {
        return id;
        }

        public void  setId(int id) {
        this.id = id;
        }

        public RealmList<Message> getMessages() {
        return messages;
        }

        public void setMessages(RealmList< Message> messages) {
        this.messages = messages;
        }

        public RealmList<User> getParticipants() {
        return participants;
        }

        public void setParticiats(RealmList<User> participants) {
        this.participants = participants;
        }

}

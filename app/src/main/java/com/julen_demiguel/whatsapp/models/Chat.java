package com.julen_demiguel.whatsapp.models;

import com.julen_demiguel.whatsapp.Application.MyApplication;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Chat extends RealmObject {

    @PrimaryKey
    private int id;
    private RealmList<Message> messages;
    private RealmList<User> participants;
    private boolean group;
    private String nameGroup;
    private int img;

    public Chat() {
    }

    public Chat(RealmList<User> participants, boolean group) {
        this.id = MyApplication.chatID.incrementAndGet();
        this.participants = participants;
        this.group = group;
        this.nameGroup = "";
    }

    public Chat(RealmList<Message> messages, RealmList<User> participants, boolean group) {
        this(participants, group);
        this.messages = messages;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public boolean isGroup() {
        return group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getNameGroup() {
        return this.nameGroup;
    }

    public User getOtherUser() {
        if (!group) {
            for (User user : participants) {
                if (user.getId() != MyApplication.currentUser.getId()) {
                    return user;
                }
            }
        }

        return null;
    }

    public Boolean addUser(User newUser) {
        if (participants.contains(newUser) || !group) return false;

        participants.add(newUser);
        return true;
    }

    public String getLastMessage() {
        if (messages.size() > 0) {
            return messages.get(messages.size() - 1).getText();
        } else {
            return "";
        }

    }

    public boolean setNameGroup(String name) {
        if (group) {
            this.nameGroup = name;
            return true;
        }

        return false;
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

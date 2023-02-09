package com.julen_demiguel.whatsapp.Models;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChatGroup extends RealmObject {

    @PrimaryKey
    private String id;
    private ArrayList<Message> messages;
    private ArrayList<User> participants;

    public ChatGroup() {}


}

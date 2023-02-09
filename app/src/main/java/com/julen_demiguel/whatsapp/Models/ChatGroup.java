package com.julen_demiguel.whatsapp.Models;
import com.julen_demiguel.whatsapp.Application.MyApplication;
import java.util.ArrayList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChatGroup extends RealmObject {

    @PrimaryKey

    private int id;

    private ArrayList<Message> messages;
    private ArrayList<User> participants;

    public ChatGroup() {}


    public ChatGroup(ArrayList<User> participants) {
        this.id = MyApplication.chatID.incrementAndGet();
        this.participants = participants;
    }

    public ChatGroup(ArrayList<Message> messages, ArrayList<User> participants) {
        this(participants);
        this.messages = messages;
    }

    public Boolean addUsr(User newUser){
        if(participants.equals(newUser)){
            return false;
        } else{
            return true;
        }


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    }

}

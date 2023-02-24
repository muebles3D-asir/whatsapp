package com.julen_demiguel.whatsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.julen_demiguel.whatsapp.Models.Chat;
import com.julen_demiguel.whatsapp.Models.Message;
import com.julen_demiguel.whatsapp.R;
import com.julen_demiguel.whatsapp.adapters.MessageRecyclerDataAdapter;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ChatActivity extends AppCompatActivity {

    MessageRecyclerDataAdapter messageDataAdapter;
    RecyclerView rvChat;
    RealmList<Message> messages;
    EditText etMessage;
    Button btnSend;
    Realm realm;
    Chat chat;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        realm = Realm.getDefaultInstance();

        try{
            Bundle bundle = getIntent().getExtras();
            id = bundle.getInt("id");
            chat = (Chat) realm.where(Chat.class).equalTo("id", id).findFirst();
            getSupportActionBar().setTitle(chat.getOtherUser().getName());
        } catch (NullPointerException e ){}

        rvChat = findViewById(R.id.rvChat);
        messages = chat.getMessages();

        messageDataAdapter = new MessageRecyclerDataAdapter(messages);
        rvChat.setAdapter(messageDataAdapter);
        rvChat.setLayoutManager(new GridLayoutManager(this, 1));

    }
}

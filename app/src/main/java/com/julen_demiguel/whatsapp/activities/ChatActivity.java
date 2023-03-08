package com.julen_demiguel.whatsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.julen_demiguel.whatsapp.Application.MyApplication;
import com.julen_demiguel.whatsapp.models.Chat;
import com.julen_demiguel.whatsapp.models.Message;
import com.julen_demiguel.whatsapp.R;
import com.julen_demiguel.whatsapp.adapters.MessageRecyclerDataAdapter;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;

public class ChatActivity extends AppCompatActivity {

    MessageRecyclerDataAdapter messageDataAdapter;
    RecyclerView rvChat;

    androidx.appcompat.widget.Toolbar toolbar;
    RealmList<Message> messages;
    Realm realm;
    Chat chat;

    int id;
    Date date = new Date();
    EditText etMensaje;
    ImageView imgUserSender;
    Button botonSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        realm = Realm.getDefaultInstance();

        try {
            Bundle bundle = getIntent().getExtras();
            id = bundle.getInt("id");
            chat = realm.where(Chat.class).equalTo("id", id).findFirst();

            getSupportActionBar().setTitle(chat.getName());

        } catch (NullPointerException e) {
            Log.d("Debug:::test",id+"");
        }

        etMensaje = findViewById(R.id.etMessage);
        botonSend = findViewById(R.id.btnSend);
        toolbar = findViewById(R.id.chatToolbar);
        rvChat = findViewById(R.id.rvChat);
        imgUserSender = findViewById(R.id.imgFotoDePerfilChat);

        try {
            toolbar.setTitle(chat.getName());
            if (chat.isGroup()) {
                imgUserSender.setImageResource(chat.getImg());
            }
        } catch (Exception e){
            Log.d("Debug:::imagen",chat.getImg()+"");
        }

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            Intent toMain = new Intent(this, MainActivity.class);
            startActivity(toMain);
        });

        messages = chat.getMessages();
        messageDataAdapter = new MessageRecyclerDataAdapter(messages, chat.isGroup());
        rvChat.setAdapter(messageDataAdapter);
        rvChat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        botonSend.setOnClickListener(v -> {
            if (etMensaje.getText().length() > 0) {
                Message newMessage = new Message(etMensaje.getText().toString(), MyApplication.currentUser, date);
                realm.beginTransaction();
                chat.getMessages().add(newMessage);
                realm.commitTransaction();
                etMensaje.setText("");
            }
        });
        messages.addChangeListener(boards -> messageDataAdapter.notifyDataSetChanged());
    }

    @Override
    public void onBackPressed() {
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
    }


}


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

import com.julen_demiguel.whatsapp.Application.MyApplication;
import com.julen_demiguel.whatsapp.Models.Chat;
import com.julen_demiguel.whatsapp.Models.Message;
import com.julen_demiguel.whatsapp.R;
import com.julen_demiguel.whatsapp.adapters.MessageRecyclerDataAdapter;
import com.julen_demiguel.whatsapp.fragments.ChatsFragment;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ChatActivity extends AppCompatActivity {

    MessageRecyclerDataAdapter messageDataAdapter;
    RecyclerView rvChat;
    androidx.appcompat.widget.Toolbar toolbar;
    RealmList<Message> messages;
    EditText etMessage;
    Button btnSend;
    Realm realm;
    Chat chat;
    int id;
    EditText mensaje;
    Date date = new Date();

    Button botonSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        realm = Realm.getDefaultInstance();
        RealmConfiguration config = new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .build();

        try{
            Bundle bundle = getIntent().getExtras();
            id = bundle.getInt("id");
            chat = (Chat) realm.where(Chat.class).equalTo("id", id).findFirst();
            getSupportActionBar().setTitle(chat.getOtherUser().getName());
        } catch (NullPointerException e ){}

        mensaje = findViewById(R.id.etMessage);
        botonSend = findViewById(R.id.btnSend);
        toolbar = findViewById(R.id.chatToolbar);
        toolbar.setTitle(chat.getOtherUser().getName());
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMain = new Intent(ChatActivity.this,MainActivity.class);
                startActivity(toMain);
            }
        });

        botonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mensaje.getText().length() > 0){
                    Message newMessage = new Message(mensaje.getText().toString(), MyApplication.currentUser, date);
                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Message newMessage = new Message(mensaje.getText().toString(), MyApplication.currentUser, date);
                            chat.getMessages().add(newMessage);
                        }
                    });
                }
            }
        });

        rvChat = findViewById(R.id.rvChat);
        messages = chat.getMessages();


        messageDataAdapter = new MessageRecyclerDataAdapter(messages, chat.getOtherUser());
        rvChat.setAdapter(messageDataAdapter);
        rvChat.setLayoutManager(new GridLayoutManager(this, 1));
    }
    @Override
    public void onBackPressed() {
        Intent toMain = new Intent(ChatActivity.this,MainActivity.class);
        startActivity(toMain);
    }

}


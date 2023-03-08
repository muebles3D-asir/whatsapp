package com.julen_demiguel.whatsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.julen_demiguel.whatsapp.Application.MyApplication;
import com.julen_demiguel.whatsapp.models.Chat;
import com.julen_demiguel.whatsapp.models.User;
import com.julen_demiguel.whatsapp.R;
import com.julen_demiguel.whatsapp.adapters.ContactRecyclerDataAdapter;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ContactsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContactRecyclerDataAdapter contactRecyclerAdapter;

    Realm realm;
    RealmResults<User> results;

    Button botonCrearGrupo;
    Random random = new Random();
    androidx.appcompat.widget.Toolbar toolbar;
    int[] imgs = {R.drawable.bust_mask_1, R.drawable.bust_mask_2, R.drawable.bust_mask_3, R.drawable.bust_mask_4, R.drawable.bust_mask_5, R.drawable.bust_mask_6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        realm = Realm.getDefaultInstance();

        botonCrearGrupo = findViewById(R.id.btnCreateGroup);
        recyclerView = findViewById(R.id.RecyclerUsers);
        toolbar = findViewById(R.id.ContactsToolbar);

        toolbar.setTitle("Contactos");
        setSupportActionBar(toolbar);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.community_menu);

        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        results = realm.where(User.class).notEqualTo("telef", MyApplication.currentUser.getTelef()).findAll();

        RealmList<User> usersGroup = new RealmList<>();

        contactRecyclerAdapter = new ContactRecyclerDataAdapter(results, position -> {
            RealmList<User> usersChat = new RealmList<>();
            usersChat.add(results.get(position));
            usersChat.add(MyApplication.currentUser);
            RealmResults<Chat> chats = realm.where(Chat.class).equalTo("group", false).findAll();
            int id = -1;
            for (Chat chat : chats) {
                if (chat.getParticipants().contains(usersChat.get(0)) && chat.getParticipants().contains(usersChat.get(1))) {
                    id = chat.getId();
                    break;
                }
            }
            if (id == -1) {
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(new Chat(usersChat, false));
                realm.commitTransaction();
                id = results.get(position).getId();
            }

            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);

        }, position -> {
            botonCrearGrupo.setVisibility(View.VISIBLE);
            usersGroup.add(results.get(position));

            if (!usersGroup.contains(MyApplication.currentUser)) usersGroup.add(MyApplication.currentUser);
            return true;
        });


        recyclerView.setAdapter(contactRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        botonCrearGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                Chat group = new Chat(usersGroup, true);
                group.setImg(imgs[random.nextInt(imgs.length)]);
                realm.copyToRealmOrUpdate(group);
                realm.commitTransaction();
                Intent intent = new Intent(ContactsActivity.this, GroupActivity.class);
                intent.putExtra("id", group.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
    }

}
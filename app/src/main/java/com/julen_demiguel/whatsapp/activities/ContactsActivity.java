package com.julen_demiguel.whatsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.julen_demiguel.whatsapp.Application.MyApplication;
import com.julen_demiguel.whatsapp.Models.Chat;
import com.julen_demiguel.whatsapp.Models.User;
import com.julen_demiguel.whatsapp.R;
import com.julen_demiguel.whatsapp.adapters.ChatRecyclerDataAdapter;
import com.julen_demiguel.whatsapp.adapters.ContactRecyclerDataAdapter;
import com.julen_demiguel.whatsapp.fragments.ChatsFragment;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ContactsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContactRecyclerDataAdapter contactRecyclerAdapter;
    Realm realm;
    androidx.appcompat.widget.Toolbar toolbar;
    List<User> users = new RealmList<>();
    RealmResults<User> results;
    private ChatListener callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        recyclerView = findViewById(R.id.RecyclerUsers);
        realm = Realm.getDefaultInstance();

        toolbar = findViewById(R.id.ContactsToolbar);
        toolbar.setTitle("Contactos");
        setSupportActionBar(toolbar);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.community_menu);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        results = realm.where(User.class).findAll();
        if (results.size() > 0) {
            users.clear();
            users.addAll(realm.copyFromRealm(results));
        }
        contactRecyclerAdapter = new ContactRecyclerDataAdapter(results, position -> {
            RealmList<User> usersChat = new RealmList<>();
            usersChat.add(results.get(position));
            usersChat.add(MyApplication.currentUser);
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(new Chat(usersChat));
            realm.commitTransaction();
            Intent intent = new Intent(ContactsActivity.this, ChatActivity.class);
            intent.putExtra("id", results.get(position).getId());
            startActivity(intent);
            Toast.makeText(this, results.get(position).getId() + "", Toast.LENGTH_SHORT).show();

        });
        recyclerView.setAdapter(contactRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }

    public interface ChatListener {
        void openChat(int id);
    }
}
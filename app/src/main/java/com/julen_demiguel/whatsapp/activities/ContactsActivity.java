package com.julen_demiguel.whatsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.julen_demiguel.whatsapp.Models.User;
import com.julen_demiguel.whatsapp.R;
import com.julen_demiguel.whatsapp.adapters.ContactRecyclerAdapter;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ContactsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContactRecyclerAdapter contactRecyclerAdapter;
    Realm realm;
    androidx.appcompat.widget.Toolbar toolbar;
    List<User> users = new RealmList<>();
    RealmResults<User> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        recyclerView = findViewById(R.id.RecyclerUsers);
        realm = Realm.getDefaultInstance();

        toolbar = findViewById(R.id.ContactsToolbar);
        toolbar.setTitle("Whatsapp");
        setSupportActionBar(toolbar);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.community_menu);

        results = realm.where(User.class).findAll();
        if(results.size() > 0) {
            users.clear();
            users.addAll(realm.copyFromRealm(results));
        }
        contactRecyclerAdapter = new ContactRecyclerAdapter(results, new ContactRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        recyclerView.setAdapter(contactRecyclerAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
    }

}
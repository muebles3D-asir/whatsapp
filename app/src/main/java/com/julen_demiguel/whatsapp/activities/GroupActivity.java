package com.julen_demiguel.whatsapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.julen_demiguel.whatsapp.Models.Chat;
import com.julen_demiguel.whatsapp.R;

import io.realm.Realm;
import io.realm.RealmList;

public class GroupActivity extends AppCompatActivity {

    Realm realm;
    Chat chatGroup = new Chat();

    EditText etName;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Intent intent = getIntent();
        realm = Realm.getDefaultInstance();

        int idGroup = intent.getExtras().getInt("id");
        chatGroup = realm.where(Chat.class).equalTo("id", idGroup).findFirst();

        etName = findViewById(R.id.etName);
        btnCreate = findViewById(R.id.btnCreateGroup);


        btnCreate.setOnClickListener(view -> {
            if (etName.getText().length() == 0) {
                Toast.makeText(GroupActivity.this, "Debes introducir un nombre de grupo antes!!", Toast.LENGTH_SHORT).show();
            } else {
                realm.beginTransaction();
                chatGroup.setNameGroup(etName.getText().toString());
                realm.copyToRealmOrUpdate(chatGroup);
                realm.commitTransaction();

                Intent intent1 = new Intent(GroupActivity.this, ChatActivity.class);
                intent1.putExtra("id", chatGroup.getId());
                startActivity(intent1);
            }
        });
    }
}
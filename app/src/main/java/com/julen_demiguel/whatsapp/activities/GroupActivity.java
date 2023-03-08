package com.julen_demiguel.whatsapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.julen_demiguel.whatsapp.models.Chat;
import com.julen_demiguel.whatsapp.R;

import io.realm.Realm;

public class GroupActivity extends AppCompatActivity {

    Realm realm;
    Chat chatGroup = new Chat();

    EditText etName;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        realm = Realm.getDefaultInstance();

        Bundle bundle = getIntent().getExtras();

        int idGroup = bundle.getInt("id");
        chatGroup = realm.where(Chat.class).equalTo("id", idGroup).findFirst();

        etName = findViewById(R.id.etNameGroup);
        btnCreate = findViewById(R.id.btnConfigCreateGroup);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().length() == 0)
                    Toast.makeText(GroupActivity.this, "Debes introducir un nombre de grupo antes!!", Toast.LENGTH_SHORT).show();
                else {
                    realm.beginTransaction();
                    chatGroup.setName(etName.getText().toString());

                    realm.copyToRealmOrUpdate(chatGroup);
                    realm.commitTransaction();

                    Intent intent1 = new Intent(GroupActivity.this, ChatActivity.class);
                    intent1.putExtra("id", chatGroup.getId());
                    startActivity(intent1);
                }
            }
        });
    }
}
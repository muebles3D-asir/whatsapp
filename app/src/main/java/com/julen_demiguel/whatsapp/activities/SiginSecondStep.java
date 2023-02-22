package com.julen_demiguel.whatsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.julen_demiguel.whatsapp.Models.User;
import com.julen_demiguel.whatsapp.R;

import java.util.ArrayList;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class SiginSecondStep extends AppCompatActivity {
    User usuarioRegistro;
    Button botonRegistrarse;
    EditText password;
    EditText passwordRepeat;
    Random random = new Random();
    Realm realm;
    RealmResults<User> results;

    ArrayList<User> users = new ArrayList<User>();
    int[] imgs ={ R.drawable.perfil1, R.drawable.perfil2, R.drawable.perfil3, R.drawable.perfil4, R.drawable.perfil5, R.drawable.perfil6 };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sigin_fragment_step2);
        Intent intent = getIntent();

        usuarioRegistro = (User) intent.getSerializableExtra("usuarioRegistro");
        botonRegistrarse = (Button) findViewById(R.id.btnSigIn);
        password = (EditText) findViewById(R.id.etPassword);
        passwordRepeat = (EditText) findViewById(R.id.etRepeatPassword);
        botonRegistrarse.setOnClickListener(v -> {
            if (password.getText().length() == 0 || passwordRepeat.getText().length() == 0) {
                Toast.makeText(SiginSecondStep.this, "Por favor, rellena todos los campos :))", Toast.LENGTH_SHORT).show();
            } else {
                if (password.getText().toString().equals(passwordRepeat.getText().toString())) {
                    usuarioRegistro.setPassword(password.getText().toString());
                    usuarioRegistro.setImg(imgs[random.nextInt(imgs.length)]);
                    realm = Realm.getDefaultInstance();
                    results = realm.where(User.class).findAll();
                    users.addAll(realm.copyFromRealm(results));
                    boolean usuarioExistente = false;
                    for (User u : users) {
                        if (u.getTelef().equals(usuarioRegistro.getTelef())) {
                           usuarioExistente = true;
                            break;
                        } else if (u.getPassword().equals(usuarioRegistro.getPassword())) {
                          usuarioExistente = true;
                            break;
                        }
                    }
                    if(usuarioExistente) {
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(usuarioRegistro);
                        realm.commitTransaction();
                    } else {
                        Toast.makeText(this, "El usuario asociado a ese número de telefono ya esta registrado!!>:V", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent1 = new Intent(SiginSecondStep.this, LogInActivity.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(SiginSecondStep.this, "Las contraseñas no coinciden!!!! >.V", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

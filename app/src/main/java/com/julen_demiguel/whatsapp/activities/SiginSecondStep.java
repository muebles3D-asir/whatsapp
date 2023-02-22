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

public class SiginSecondStep extends AppCompatActivity {
    User usuarioRegistro;
    Button botonRegistrarse;
    EditText password;
    EditText passwordRepeat;
    Random random = new Random();
    Realm realm;
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
                Toast.makeText(SiginSecondStep.this, "Debes rellenar los campos porfavor", Toast.LENGTH_SHORT).show();
            } else {
                if (password.getText().toString().equals(passwordRepeat.getText().toString())) {
                    usuarioRegistro.setPassword(password.getText().toString());
                    usuarioRegistro.setImg(imgs[random.nextInt(imgs.length)]);

                    realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(usuarioRegistro);
                    realm.commitTransaction();

                    Intent intent1 = new Intent(SiginSecondStep.this, LogInActivity.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(SiginSecondStep.this, "Las contraseñas no coinciden!!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

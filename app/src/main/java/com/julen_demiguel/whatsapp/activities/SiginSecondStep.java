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

public class SiginSecondStep extends AppCompatActivity {
    User usuarioRegistro;
    Button botonRegistrarse;
    EditText password;
    EditText passwordRepeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sigin_fragment_step2);
        Intent intent = getIntent();
        usuarioRegistro = (User) intent.getSerializableExtra("usuarioRegistro");
        botonRegistrarse = (Button) findViewById(R.id.btnSigIn);
        password = (EditText) findViewById(R.id.etPassword);
        passwordRepeat = (EditText) findViewById(R.id.etRepeatPassword);
        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().length() == 0 | passwordRepeat.getText().length() == 0){
                    Toast.makeText(SiginSecondStep.this, "Debes rellenar los campos porfavor", Toast.LENGTH_SHORT).show();
                } else{
                    if(password.getText().toString().equals(passwordRepeat.getText().toString())){
                        usuarioRegistro.setPassword(password.toString());
                        Intent intent = new Intent(SiginSecondStep.this, LogInActivity.class);
                        startActivity(intent);
                    } else{
                        Toast.makeText(SiginSecondStep.this, "Las contraseñas no coinciden!!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}

package com.julen_demiguel.whatsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.julen_demiguel.whatsapp.models.User;
import com.julen_demiguel.whatsapp.R;

public class SignInActivity extends AppCompatActivity {
    Button botonNextStep;
    User usuarioRegistro;
    EditText nombre;
    EditText telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_fragment_step1);

        botonNextStep = findViewById(R.id.btnNext);
        nombre = findViewById(R.id.IdinputNombre);
        telefono = findViewById(R.id.etTelefono);

        botonNextStep.setOnClickListener(v -> {
            if (nombre.getText().equals("") || telefono.getText().equals("")) {
                Toast.makeText(this, "Debes rellenar todos los datos!!", Toast.LENGTH_SHORT).show();
            } else {
                usuarioRegistro = new User(nombre.getText().toString(), telefono.getText().toString());
                Intent intent = new Intent(this, SiginSecondStep.class);
                intent.putExtra("usuarioRegistro", usuarioRegistro);
                startActivity(intent);
            }
        });

    }

}

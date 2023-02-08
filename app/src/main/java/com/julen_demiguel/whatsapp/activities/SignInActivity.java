package com.julen_demiguel.whatsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.julen_demiguel.whatsapp.Models.User;
import com.julen_demiguel.whatsapp.R;

public class SignInActivity extends AppCompatActivity  {


    Button botonNextStep;
    User usuarioRegistro;
    EditText nombre;
    EditText telefono;
    EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sigin_fragment_step1);
        botonNextStep = (Button) findViewById(R.id.btnNext);
        nombre = (EditText) findViewById(R.id.IdinputNombre);
        telefono = (EditText) findViewById(R.id.etTelefono);
        email = (EditText) findViewById(R.id.idInputCorreo);


        botonNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().equals("") | telefono.getText().equals("") | email.getText().equals("")){
                    Toast.makeText(SignInActivity.this, "Debes rellenar todos los datos!!", Toast.LENGTH_SHORT).show();
                } else {
                   usuarioRegistro = new User(nombre.toString(), email.toString(), telefono.toString());
                    Intent intent = new Intent(SignInActivity.this, SiginSecondStep.class);
                    intent.putExtra("usuarioRegistro",  usuarioRegistro);
                    startActivity(intent);

                }


            }
        });




    }

}

package com.julen_demiguel.whatsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.julen_demiguel.whatsapp.Application.MyApplication;
import com.julen_demiguel.whatsapp.Models.User;
import com.julen_demiguel.whatsapp.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class LogInActivity extends AppCompatActivity {
    Realm realm;
    private boolean esVisible;
    EditText inputNombre;
    EditText inputPassword;
    EditText inputTelef;
    Button login;
    RealmResults<User> results;
    CheckBox visibleCkeck;
    ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.btn_login); 
        inputNombre = (EditText) findViewById(R.id.et_name);
        inputTelef = (EditText) findViewById(R.id.et_phone);
        inputPassword = (EditText) findViewById(R.id.et_password);


        realm = Realm.getDefaultInstance();
        visibleCkeck = (CheckBox) findViewById(R.id.idMostContraseña);
        results = realm.where(User.class).findAll();

        if(results.size() > 0) {
            users.clear();
            users.addAll(realm.copyFromRealm(results));
        }

        visibleCkeck.setOnClickListener(v -> {
            if(!esVisible) {
                inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                esVisible = true;
                ///aqui puedes cambiar el texto del boton, o textview, o cambiar la imagen de un imageView.
            }
            else {
                inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                esVisible = false;
                ///aqui puedes cambiar el texto del boton, o textview, o cambiar la imagen de un imageView.
            }
        });
        
        login.setOnClickListener(v -> {
            if(inputNombre.getText().length() == 0 || inputTelef.getText().length() == 0 || inputPassword.getText().length() == 0){
                Toast.makeText(LogInActivity.this, "Rellena todos los datos", Toast.LENGTH_SHORT).show();
            } else{
                User userLogIn = new User(inputNombre.getText().toString(),inputTelef.getText().toString(), inputPassword.getText().toString());
                if (users.contains(userLogIn)){
                    MyApplication.currentUser = userLogIn;
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(LogInActivity.this, "Ese un usario con existe", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}

package com.julen_demiguel.whatsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.julen_demiguel.whatsapp.Application.MyApplication;
import com.julen_demiguel.whatsapp.models.User;
import com.julen_demiguel.whatsapp.R;

import io.realm.Realm;

public class LogInActivity extends AppCompatActivity {
    Realm realm;

    EditText inputPassword;
    EditText inputTelef;
    Button login;
    CheckBox visibleCkeck;

    private boolean esVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        realm = Realm.getDefaultInstance();

        login = findViewById(R.id.btn_login);
        inputTelef = findViewById(R.id.et_phone);
        inputPassword = findViewById(R.id.et_password);
        visibleCkeck = findViewById(R.id.idMostContraseña);

        visibleCkeck.setOnClickListener(v -> {
            inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            esVisible = !esVisible;
        });

        login.setOnClickListener(v -> {
            if (inputTelef.getText().length() == 0 || inputPassword.getText().length() == 0) Toast.makeText(this, "Rellena todos los datos", Toast.LENGTH_SHORT).show();
            else {
                User userLogIn = realm.where(User.class).equalTo("telef", inputTelef.getText().toString()).findFirst();
                if (userLogIn != null && userLogIn.getPassword().equals(inputPassword.getText().toString())) {
                    MyApplication.currentUser = userLogIn;
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Ese usuario no existe", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}

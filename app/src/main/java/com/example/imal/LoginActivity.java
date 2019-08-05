package com.example.imal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private TextView emailTxt, pwTxt, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);
        register = findViewById(R.id.createAcc);

    }

    public void Login(View v){
        Intent loginIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(loginIntent);
    }

    public void registerRedirect(View v){
        Intent regIntent = new Intent(getBaseContext(), registerUser.class);
        startActivity(regIntent);
    }
}

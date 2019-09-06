package com.example.imal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private TextView emailTxt, pwTxt, register;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTxt = findViewById(R.id.usernameBox);
        pwTxt = findViewById(R.id.passwordBox);

        loginBtn = findViewById(R.id.loginBtn);
        register = findViewById(R.id.createAcc);

        progressDialog = new ProgressDialog(this);

    }

    public void Login(View v){

        userLogin();

    }

    public void registerRedirect(View v){

        Intent regIntent = new Intent(getBaseContext(), registerUser.class);
        finish();
        startActivity(regIntent);
    }

    public void userLogin(){
        String email = emailTxt.getText().toString().trim();
        String password = pwTxt.getText().toString().trim();


    }
}

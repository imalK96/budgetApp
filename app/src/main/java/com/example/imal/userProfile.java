package com.example.imal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class userProfile extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView userEmailTxt, userPwTxt;

    private Button updateBtn, DisableBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        userEmailTxt = findViewById(R.id.userPwOld);
        userPwTxt = findViewById(R.id.userPasswordNew);
        updateBtn = findViewById(R.id.updateCredBtn);
        DisableBtn = findViewById(R.id.DeleteBtn);

        userEmailTxt.setText(user.getEmail());



    }

    public void updateNav(View v){
        Intent profileIntent = new Intent(getBaseContext(), updateCredentials.class);
        startActivity(profileIntent);
    }




}

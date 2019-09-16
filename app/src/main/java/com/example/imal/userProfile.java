package com.example.imal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

    private TextView userEmailTxt, userPwTxt, displayNameTxt;

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
        displayNameTxt = findViewById(R.id.di);

        updateBtn = findViewById(R.id.updateCredBtn);
        DisableBtn = findViewById(R.id.DeleteBtn);

        userEmailTxt.setText(user.getEmail());
        displayNameTxt.setText(user.getDisplayName());


    }

    public void updateNav(View v){
        Intent profileIntent = new Intent(getBaseContext(), updateCredentials.class);
        startActivity(profileIntent);
    }

    public void deleteAccount(View view){
        //Add a dialog box --------------

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        currentUser.delete();
        Intent loginIntent = new Intent(getBaseContext(), LoginActivity.class);

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));

        //startActivity(loginIntent);



        
    }




}

package com.example.imal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class updateCredentials extends AppCompatActivity {

    EditText oldEmailTxt ,oldPwTxt, newEmailTxt, newPwTxt;
    Button update;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_credentials);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        oldEmailTxt = findViewById(R.id.userEmailOldUpdate);
        oldPwTxt = findViewById(R.id.userPwOldUpdate);
        newEmailTxt = findViewById(R.id.userEmailNewUpdate);
        newPwTxt = findViewById(R.id.userPasswordNewUpdate);

        update = findViewById(R.id.updateBtn);


    }


    public void onClick(android.view.View view) {


            updateCredentials();


    }


        public void updateCredentials(){

            oldEmailTxt = findViewById(R.id.userEmailOldUpdate);
            oldPwTxt = findViewById(R.id.userPwOldUpdate);
            newEmailTxt = findViewById(R.id.userEmailNewUpdate);
            newPwTxt = findViewById(R.id.userPasswordNewUpdate);

            String oldEmail = oldEmailTxt.getText().toString().trim();
            String oldPw = oldPwTxt.getText().toString().trim();

            String newEmail = newEmailTxt.getText().toString().trim();
            String newPw = newPwTxt.getText().toString().trim();

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            // Get auth credentials from the user for re-authentication
            AuthCredential credential = EmailAuthProvider
                    .getCredential(oldEmail, oldPw); // Current Login Credentials \\
            // Prompt the user to re-provide their sign-in credentials
            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {


                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            String newEmail = newEmailTxt.getText().toString().trim();

                            //Now change your email address \\
                            //----------------Code for Changing Email Address----------\\
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.updateEmail(newEmail)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                            }
                                        }
                                    });
                            //----------------------------------------------------------\\
                            String newPw = newPwTxt.getText().toString().trim();
                            user.updatePassword(newPw).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Password updated", Toast.LENGTH_SHORT).show();
                                    } else {

                                    }
                                }
                            });
                        }
                    });

        }

}

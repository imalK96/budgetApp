package com.example.imal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private Button loginBtn;
    private TextView emailTxt, pwTxt, register;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //go to dashboard
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

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

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password) && TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter a password and email", Toast.LENGTH_SHORT).show();
        }

        else{
            progressDialog.setMessage("Signing in...");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if(task.isSuccessful()){
                                //go to dashboard
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        }
                    });

        }


    }
}

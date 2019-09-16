package com.example.imal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerUser extends AppCompatActivity implements android.view.View.OnClickListener {

    private EditText emailTxt, passwordTxt, nameTxt;
    private Button registerBtn;

    private ProgressDialog regProgress;

    private FirebaseAuth firebaseAuth; /*1*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        firebaseAuth = FirebaseAuth.getInstance(); /*2*/

        if(firebaseAuth.getCurrentUser() != null){
            //go to dashboard
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        emailTxt = findViewById(R.id.regEmail);
        passwordTxt = findViewById(R.id.regPw);

        registerBtn = findViewById(R.id.regBtn);

        //nameTxt = findViewById(R.id.regName);

        registerBtn.setOnClickListener(this);

        regProgress = new ProgressDialog(this);


    }

    public void onClick(View v){

    }

    @Override
    public void onClick(android.view.View view) {

        if(view == registerBtn){

            registerUser();
        }

    }

    public void registerUser(){

        String email = emailTxt.getText().toString().trim();
        String password = passwordTxt.getText().toString().trim();
        final String name = nameTxt.getText().toString();

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

            regProgress.setMessage("Registering User...");
            regProgress.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            regProgress.dismiss();
                            if (task.isSuccessful()) {
                                Toast.makeText(registerUser.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                //go to dashboard
                                finish();
                                Intent regToMain = new Intent(getApplicationContext(), MainActivity.class);
                                regToMain.putExtra("name", name);
                                startActivity(regToMain);

                            } else{
                                Toast.makeText(registerUser.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

            );
        }
    }
}

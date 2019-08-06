package com.example.imal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EnterIncome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_income);
    }
    public void saved(View v){
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();

        Intent tomenu = new Intent(getBaseContext(),MainActivity.class);
        startActivity(tomenu);

    }
}

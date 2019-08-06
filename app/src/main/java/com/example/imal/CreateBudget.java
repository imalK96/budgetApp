package com.example.imal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateBudget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget);
    }

    public void endDateBudget(View v){
        Intent edBudget = new Intent(getBaseContext(),CreateBudget1.class);
        startActivity(edBudget);
    }
}

package com.example.imal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateBudget1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget1);
    }

    public void enterIncome(View v){
        Intent enterBudget = new Intent(getBaseContext(),EnterIncome.class);
        startActivity(enterBudget);
    }
}

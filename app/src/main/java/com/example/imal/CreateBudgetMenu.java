package com.example.imal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateBudgetMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget_menu);
    }

    public void enterBudget(View v){
        Intent enterBudget = new Intent(getBaseContext(),EnterIncome.class);
        startActivity(enterBudget);
    }

    public void editBudget(View v){
        Intent enterBudget = new Intent(getBaseContext(),EditBudget.class);
        startActivity(enterBudget);
    }
}

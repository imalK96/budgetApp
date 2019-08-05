package com.example.imal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    //private CardView profileCard;
    private LinearLayout l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1 = findViewById(R.id.profile);

    }

    public void profileNav(View v){
        Intent profileIntent = new Intent(getBaseContext(), userProfile.class);
        startActivity(profileIntent);
    }

    public void addCat(View v){

        Intent addCategory = new Intent(getBaseContext(), addCategory.class);
        startActivity(addCategory);
    }
}

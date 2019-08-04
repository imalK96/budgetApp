package com.example.imal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class addCategory extends AppCompatActivity implements android.view.View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        }

//    public void click(View v){
//
//
//        Intent i1 = new Intent(getBaseContext(),View.class);
//        startActivity(i1);
//
//    }

    @Override
    public void onClick(android.view.View view) {

        Intent i1 = new Intent(getBaseContext(),View.class);
        startActivity(i1);

    }
}

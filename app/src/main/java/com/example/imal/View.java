package com.example.imal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class View extends AppCompatActivity {

    ListView ListView1;
    Button update,delete;

    ArrayList<String> a1 = new ArrayList<String>();
    ArrayAdapter myAdapter1;

    Integer index1;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        String s1 = intent.getStringExtra("name");
        String s2 = intent.getStringExtra("amount");



        ListView1 = findViewById(R.id.listview);
        update = findViewById(R.id.button2);
        delete = findViewById(R.id.button);

        if(!s1.isEmpty() && !s2.isEmpty()) {
            a1.add("Item = " + s1 + "\t\t\t" + " Amount = Rs : " + s2);
            //a1.add("Rs:"+ s2);
        }
        myAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,a1);
        ListView1.setAdapter(myAdapter1);
    }
}

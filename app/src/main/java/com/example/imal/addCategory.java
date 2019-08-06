package com.example.imal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Edits;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class addCategory extends AppCompatActivity implements android.view.View.OnClickListener {

    EditText t1,t2;
    String s1,s2;
    ImageButton b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        t1 = findViewById(R.id.c1);
        t2 = findViewById(R.id.c2);
        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);

        b1.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                      s1 = t1.getText().toString();
                      s2 = t2.getText().toString();

        if(s1.isEmpty() || s2.isEmpty()){

            Context context = getApplicationContext();
            CharSequence text = "Empty Space!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text,duration); toast.show();
        }else{

            Context context = getApplicationContext();
            CharSequence text = "Successfully Added!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text,duration); toast.show();
        }


            }
        });


        }

////    public void click(View v){
////
////
////
////
////    }
////
////
////
////    public void add(){
////
///
////
////    }


    @Override
    public void onClick(android.view.View view) {
        Intent i1 = new Intent(getBaseContext(),View.class);

        s1 = t1.getText().toString();
        s2 = t2.getText().toString();

        i1.putExtra("name",s1);
        i1.putExtra("amount",s2);

        startActivity(i1);
    }
}

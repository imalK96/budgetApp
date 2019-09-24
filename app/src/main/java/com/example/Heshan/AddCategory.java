package com.example.Heshan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imal.MainActivity;
import com.example.imal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCategory extends AppCompatActivity {

    EditText t1,t2;
    Button b1,b2;
    DatabaseReference databaseCategory;
    private FirebaseAuth firebaseAuth;
    FirebaseUser user = null;
    private long backPressedTime;
    @Override
    public void onBackPressed() {
        if(backPressedTime +  2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }
        else {

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        backPressedTime = System.currentTimeMillis();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        t1 = findViewById(R.id.catupName);
        t2 = findViewById(R.id.catupAmount);
        b1 = findViewById(R.id.catAddbtn);
        b2 = findViewById(R.id.catViewbtn);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        databaseCategory = FirebaseDatabase.getInstance().getReference("Category").child(user.getUid());

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getBaseContext(),ViewCategory.class);
                startActivity(i1);
            }
        });
    }

    //Data insert part
    public void addCategory(View v){

      try {
          if (t1.getText().toString().isEmpty() && t2.getText().toString().isEmpty()) {
              Toast.makeText(getApplicationContext(), "fields are Empty", Toast.LENGTH_SHORT).show();
          }
          else if (t1.getText().toString().isEmpty()) {
              Toast.makeText(getApplicationContext(), "Name field is Empty", Toast.LENGTH_SHORT).show();
          } else if (t2.getText().toString().isEmpty()) {
              Toast.makeText(getApplicationContext(), "Amount field is Empty", Toast.LENGTH_SHORT).show();
          } else {


              String catName = t1.getText().toString().trim();
              String catAmount = t2.getText().toString().trim();

              Category cat = new Category(catName, catAmount);

              databaseCategory.push().setValue(cat);

              Toast.makeText(getApplicationContext(), "Item added successfully", Toast.LENGTH_SHORT).show();
              clearReference();

          }

      }catch (Exception e){

          Toast.makeText(getApplicationContext(), "Enter a correct amount", Toast.LENGTH_SHORT).show();
      }

    }
//Clear the fields
    public void clearReference(){

        t1.setText("");
        t2.setText("");

    }
}

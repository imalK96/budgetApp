package com.example.Heshan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class catUpdate extends AppCompatActivity {

    EditText txt1,txt2;
    Button BtnUpdate;
    FirebaseDatabase database;
    DatabaseReference ref;
    Category cat;
    private long backPressedTime;
    private FirebaseAuth firebaseAuth;


    //programming back button
    @Override
    public void onBackPressed() {
        if(backPressedTime +  2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }
        else {

            Intent i = new Intent(getApplicationContext(),ViewCategory.class);
            startActivity(i);
        }
        backPressedTime = System.currentTimeMillis();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_update);

        txt1 = findViewById(R.id.catupName);
        txt2 = findViewById(R.id.catupAmount);
        BtnUpdate = findViewById(R.id.catup);
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Category").child(user.getUid());



        Intent i1 = getIntent();
        final String i = i1.getStringExtra("idValue");
        final String i3= i1.getStringExtra("idamount");



        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try{
                    txt1.setText(i);
                    txt2.setText(i3);
                    }catch (Exception e){

                        System.out.println(e);

                    }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //update method
        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds  : dataSnapshot.getChildren()){

                                if(i.equals(ds.child("catName").getValue().toString())){
                                    String catName = txt1.getText().toString();
                                    String catAmount = txt2.getText().toString();

                                    if(catName.equals("") && catAmount.equals("")){
                                        Toast.makeText(getApplicationContext(),"Fields are empty!",Toast.LENGTH_SHORT).show();
                                    }else if(catName.equals("")){
                                        Toast.makeText(getApplicationContext(),"Name Field is empty!",Toast.LENGTH_SHORT).show();
                                    }else if(catAmount.equals("")){
                                        Toast.makeText(getApplicationContext(),"Amount Field is empty",Toast.LENGTH_SHORT).show();
                                    }else {

                                        Category cat = new Category(catName, catAmount);

                                        ds.getRef().child("catName").setValue(catName);
                                        ds.getRef().child("amount").setValue(catAmount);



                                        Toast.makeText(getApplicationContext(), "Item updated successfully", Toast.LENGTH_SHORT).show();
                                        Intent i2 = new Intent(getBaseContext(), ViewCategory.class);
                                        startActivity(i2);
                                    }
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Enter a correct Amount!", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }
}

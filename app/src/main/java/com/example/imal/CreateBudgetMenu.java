package com.example.imal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanuja.fragments.ThanujaMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateBudgetMenu extends AppCompatActivity {

    private FirebaseAuth firebaseAuth; /*1*/
    DatabaseReference readRef;

    FirebaseUser user =null;/*3*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget_menu);
        firebaseAuth = FirebaseAuth.getInstance();/*2*/
        user =firebaseAuth.getCurrentUser();/*3*/
    }

    public void enterBudget(View v){

        readRef = FirebaseDatabase.getInstance().getReference().child("Budget").child(user.getUid());
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChildren()){
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Toast.makeText(getApplicationContext(), "Budget Already Exsist", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Intent enterBudget = new Intent(getBaseContext(),EnterIncome.class);
                    startActivity(enterBudget);


                    //Thanuja added
                    readRef = FirebaseDatabase.getInstance().getReference().child("Yearly Expense").child(user.getUid());
                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds : dataSnapshot.getChildren()){
                                    ds.getRef().removeValue();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    readRef = FirebaseDatabase.getInstance().getReference().child("Monthly Expense").child(user.getUid());
                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds : dataSnapshot.getChildren()){
                                ds.getRef().removeValue();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    readRef = FirebaseDatabase.getInstance().getReference().child("Daily Expense").child(user.getUid());
                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds : dataSnapshot.getChildren()){
                                ds.getRef().removeValue();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //up to here

    public void editBudget(View v){
        readRef = FirebaseDatabase.getInstance().getReference().child("Budget").child(user.getUid());
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()){
                    Intent enterBudget = new Intent(getBaseContext(),EditBudget.class);
                    startActivity(enterBudget);
                }else{

                    Toast.makeText(getApplicationContext(), "Budget Not Available", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

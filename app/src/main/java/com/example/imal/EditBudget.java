package com.example.imal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditBudget extends AppCompatActivity {
    private FirebaseAuth firebaseAuth; /*1*/
    DatabaseReference readRef,upRef;
    EnterBudgetDB enterbudget;
    FirebaseUser user =null;/*3*/
    TextView etsal, etother, etbank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);
        firebaseAuth = FirebaseAuth.getInstance();/*2*/
        user =firebaseAuth.getCurrentUser();/*3*/
        etsal = findViewById(R.id.etsalary);
        etother = findViewById(R.id.etother);
        etbank = findViewById(R.id.etbank);
        show();

    }

    public  void show(){
        readRef = FirebaseDatabase.getInstance().getReference().child("Budget").child(user.getUid());
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()){
                   for (DataSnapshot ds : dataSnapshot.getChildren()) {


                       etsal.setText(ds.child("salary").getValue().toString());
                       etother.setText(ds.child("income").getValue().toString());
                       etbank.setText(ds.child("interest").getValue().toString());

                   }

                }else{
                    Toast.makeText(getApplicationContext(),"No Data to Display",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updatebudget(View v){
        upRef = FirebaseDatabase.getInstance().getReference().child("Budget").child(user.getUid());
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(user.getUid())){
                    try{
                        enterbudget.setSalary(Double.parseDouble(etsal.getText().toString().trim()));
                        enterbudget.setIncome(Double.parseDouble(etother.getText().toString().trim()));
                        enterbudget.setInterest(Double.parseDouble(etbank.getText().toString().trim()));

                        upRef = FirebaseDatabase.getInstance().getReference().child("Budget").child(user.getUid());
                        upRef.setValue(enterbudget);
                        Toast.makeText(getApplicationContext(),"Data Updated", Toast.LENGTH_SHORT).show();

                    }catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(),"Invalid Data", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




}

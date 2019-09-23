package com.example.imal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    TextView etsal, etother, etbank, edis, sddis, totb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);
        firebaseAuth = FirebaseAuth.getInstance();/*2*/
        user =firebaseAuth.getCurrentUser();/*3*/
        etsal = findViewById(R.id.etsalaryedit);
        etother = findViewById(R.id.etotheredit);
        etbank = findViewById(R.id.etbankedit);
        edis = findViewById(R.id.eddisplay);
        sddis = findViewById(R.id.sddisplay);
        totb = findViewById(R.id.totbdis);
        show();

    }

    public  void show(){
        readRef = FirebaseDatabase.getInstance().getReference().child("Budget").child(user.getUid());
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        edis.setText(ds.child("enddate").getValue().toString());
                        sddis.setText(ds.child("startdate").getValue().toString());
                        etsal.setText(ds.child("salary").getValue().toString());
                        etother.setText(ds.child("income").getValue().toString());
                        etbank.setText(ds.child("interest").getValue().toString());
                        double totbud = Double.parseDouble(etsal.getText().toString())+ Double.parseDouble(etother.getText().toString())+ Double.parseDouble(etbank.getText().toString());
                        totb.setText("Total Budget - " + totbud);

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
                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        try{

                            EnterBudgetDB enterbudget = new EnterBudgetDB();
                            enterbudget.setStartdate(sddis.getText().toString().trim());
                            enterbudget.setEnddate(edis.getText().toString().trim());
                            enterbudget.setSalary(Double.parseDouble(etsal.getText().toString().trim()));
                            enterbudget.setIncome(Double.parseDouble(etother.getText().toString().trim()));
                            enterbudget.setInterest(Double.parseDouble(etbank.getText().toString().trim()));

                            /*upRef = FirebaseDatabase.getInstance().getReference().child("Budget").child(user.getUid());
                            upRef.setValue(enterbudget);*/
                            ds.getRef().setValue(enterbudget);
                            Toast.makeText(getApplicationContext(),"Data Updated", Toast.LENGTH_SHORT).show();

                            Intent tomenu = new Intent(getBaseContext(),CreateBudgetMenu.class);
                            startActivity(tomenu);

                        }catch (NumberFormatException e){
                            Toast.makeText(getApplicationContext(),"Invalid Data", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void delete(View v){
        upRef = FirebaseDatabase.getInstance().getReference().child("Budget").child(user.getUid());
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        try{


                            /*upRef = FirebaseDatabase.getInstance().getReference().child("Budget").child(user.getUid());
                            upRef.setValue(enterbudget);*/
                            ds.getRef().removeValue();
                            Toast.makeText(getApplicationContext(),"Budget Deleted ", Toast.LENGTH_SHORT).show();

                            Intent tomenu = new Intent(getBaseContext(),CreateBudgetMenu.class);
                            startActivity(tomenu);

                        }catch (NumberFormatException e){
                            Toast.makeText(getApplicationContext(),"Delete Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

package com.example.imal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class ViewReport extends AppCompatActivity {
    private FirebaseAuth firebaseAuth; /*1*/
    DatabaseReference readRef,upRef;
    FirebaseUser user =null;/*3*/
    TextView etsal, etother, etbank, reportbuddis, reportbaldis, reportexpdis;
    EnterBudgetDB enterbudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        firebaseAuth = FirebaseAuth.getInstance();/*2*/
        user =firebaseAuth.getCurrentUser();/*3*/
        reportbuddis= findViewById(R.id.reportbuddis);
        reportbaldis= findViewById(R.id.reportbaldis);
        reportexpdis = findViewById(R.id.reportexpdis);
        show();
    }

    public  void show(){
        readRef = FirebaseDatabase.getInstance().getReference().child("Budget").child(user.getUid());
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        double totbud = Double.parseDouble((ds.child("salary").getValue().toString()))+Double.parseDouble((ds.child("income").getValue().toString()))+ Double.parseDouble((ds.child("interest").getValue().toString()));
                        reportbuddis.setText(Double.toString(totbud));
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"No Data to Display",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final double[] totSal = new double[1];

        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Daily Expense").child(user.getUid());
        delRef.addListenerForSingleValueEvent(new ValueEventListener(){
            public void onDataChange(DataSnapshot dataSnapshot){
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    totSal[0] = totSal[0] + Double.parseDouble(ds.child("amount").getValue().toString());
                }

            }

            public void onCancelled(DatabaseError databaseError){

            }
        });

        DatabaseReference delRef1 = FirebaseDatabase.getInstance().getReference().child("Monthly Expense").child(user.getUid());
        delRef.addListenerForSingleValueEvent(new ValueEventListener(){
            public void onDataChange(DataSnapshot dataSnapshot){
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    totSal[0] = totSal[0] + Double.parseDouble(ds.child("amount").getValue().toString());
                }

            }

            public void onCancelled(DatabaseError databaseError){

            }
        });

        DatabaseReference delRef2 = FirebaseDatabase.getInstance().getReference().child("Yearly Expense").child(user.getUid());
        delRef.addListenerForSingleValueEvent(new ValueEventListener(){
            public void onDataChange(DataSnapshot dataSnapshot){
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    totSal[0] = totSal[0] + Double.parseDouble(ds.child("amount").getValue().toString());
                }

                reportexpdis.setText(Double.toString(totSal[0]));
            }

            public void onCancelled(DatabaseError databaseError){

            }
        });
    }
}

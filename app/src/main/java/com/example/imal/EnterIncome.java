package com.example.imal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EnterIncome extends AppCompatActivity {
    private FirebaseAuth firebaseAuth; /*1*/
    DatabaseReference dbRef,upRef,readRef;
    CalendarView cvsd, cved;
    TextView etsal, etother, etbank;
    private String cvsds, cveds;
    EnterBudgetDB enterbudget;
    FirebaseUser user =null;/*3*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_income);
        cvsd = (CalendarView) findViewById(R.id.calendarViewsd);
        cved = (CalendarView) findViewById(R.id.calendarViewed);
        etsal =(EditText) findViewById(R.id.etsalary);
        etother =(EditText) findViewById(R.id.etother);
        etbank =(EditText) findViewById(R.id.etbank);
        enterbudget = new EnterBudgetDB();
        firebaseAuth = FirebaseAuth.getInstance();/*2*/
        user =firebaseAuth.getCurrentUser();/*3*/

        cvsd.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                cvsds = (i1+1) + "/" + i2 + "/" + i;

                /*mm dd yyyy*/
            }
        });


        cved.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                cveds = (i1+1) + "/" + i2 + "/" + i;

                /*mm dd yyyy*/
            }
        });
    }
    public void saved(View v){

        dbRef = FirebaseDatabase.getInstance().getReference().child("Budget");

        try{

            if (TextUtils.isEmpty(etsal.getText().toString()))
                Toast.makeText(this, "Fill All Fields,  If Null Use 0", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(cvsds))
                Toast.makeText(this, "Select a Start Date", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(cveds))
                Toast.makeText(this, "Select a End Date", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(etother.getText().toString()))
                Toast.makeText(this, "Fill All Fields,  If Null Use 0", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(etbank.getText().toString()))
                Toast.makeText(this, "Fill All Fields,  If Null Use 0", Toast.LENGTH_SHORT).show();
            else {

                enterbudget.setStartdate(cvsds);
                enterbudget.setEnddate(cveds);
                enterbudget.setSalary(Double.parseDouble(etsal.getText().toString().trim()));
                enterbudget.setIncome(Double.parseDouble(etother.getText().toString().trim()));
                enterbudget.setInterest(Double.parseDouble(etbank.getText().toString().trim()));

                dbRef.child(user.getUid()).push().setValue(enterbudget);
                //dbRef.push().setValue(enterbudget);
                Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();

                Intent tomenu = new Intent(getBaseContext(),MainActivity.class);
                startActivity(tomenu);
            }
        }
        catch (NumberFormatException e){
            Toast.makeText(this, "Invalid Data Type", Toast.LENGTH_SHORT).show();

        }



    }



}

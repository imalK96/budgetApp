package com.example.imal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.Heshan.AddCategory;
import com.example.Heshan.ViewCategory;
import com.example.thanuja.fragments.FragmentDaily;
import com.example.thanuja.fragments.ThanujaMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    //private CardView profileCard;
    private LinearLayout l1;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1 = findViewById(R.id.profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();


    }

    public void profileNav(View v){
        Intent profileIntent = new Intent(getBaseContext(), userProfile.class);
        startActivity(profileIntent);
    }

    public void addCat(View v){

        Intent addCategory = new Intent(getBaseContext(), AddCategory.class);
        startActivity(addCategory);
    }

    public void viewreports(View v){

        Intent addCategory = new Intent(getBaseContext(), ViewReport.class);
        startActivity(addCategory);
    }

    //thanuja added
    public void addExpensesNav(View v){
        Intent addExpIntent = new Intent(this, ThanujaMain.class);
        startActivity(addExpIntent);
    }

    public void startDateBudget(View v){
        Intent sdBudget = new Intent(getBaseContext(),CreateBudgetMenu.class);
        startActivity(sdBudget);
    }


    public void Logout(View v){
        Intent logoutIntent = new Intent(getBaseContext(),LoginActivity.class);
        startActivity(logoutIntent);

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void addCategory(View v){
        Intent c1 = new Intent(getBaseContext(), ViewCategory.class);
        startActivity(c1);
    }

}

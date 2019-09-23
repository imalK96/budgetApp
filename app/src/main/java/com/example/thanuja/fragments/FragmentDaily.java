package com.example.thanuja.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imal.R;
import com.example.thanuja.dialogbox.ExampleDialog;
import com.example.thanuja.recyclerview.Expense;
import com.example.thanuja.recyclerview.RecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentDaily extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private static List<Expense> lstContact;
    static FirebaseUser user = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstContact = new ArrayList<>();
        lstContact.add(new Expense("Gave to Imal", "Rs. 2000", R.drawable.images));

        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        readData();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }


    public void readData(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Daily Expense").child(user.getUid());
        readRef.addListenerForSingleValueEvent(new ValueEventListener(){
            public void onDataChange(DataSnapshot dataSnapshot){
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    lstContact.add(new Expense(ds.child("discription").getValue().toString(), ds.child("amount").getValue().toString(), R.drawable.images));
                }
            }

            public void onCancelled(DatabaseError databaseError){

            }
        });
    }


    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.showNow(getFragmentManager(), "example dialog");
    }

    @Nullable
    @Override
        public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_thanuja_daily_fragment, container, false);

        myrecyclerview = (RecyclerView)v.findViewById(R.id.contact_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), lstContact);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);

        return v;
    }
}

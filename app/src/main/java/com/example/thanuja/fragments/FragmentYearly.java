package com.example.thanuja.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imal.R;
import com.example.thanuja.dialogbox.ExampleDialogMonthly;
import com.example.thanuja.dialogbox.ExampleDialogYearly;
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

public class FragmentYearly extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<Expense> lstContact;
    static FirebaseUser user = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstContact = new ArrayList<>();
//        lstContact.add(new Expense("Boarding fee", "Rs. 5000", R.drawable.ic_cash));
//        lstContact.add(new Expense("Semester fee", "Rs. 145000", R.drawable.ic_cash));

        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        readData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = getView().findViewById(R.id.fabYearly);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        ExampleDialogYearly exampleDialogYearly = new ExampleDialogYearly();
        exampleDialogYearly.showNow(getFragmentManager(), "example dialog yearly");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_thanuja_yearly_fragment, container, false);

        myrecyclerview = (RecyclerView)v.findViewById(R.id.contact_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), lstContact);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);

        return v;
    }

    public void readData(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Yearly Expense").child(user.getUid());
        readRef.addListenerForSingleValueEvent(new ValueEventListener(){
            public void onDataChange(DataSnapshot dataSnapshot){
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    lstContact.add(new Expense(ds.child("discription").getValue().toString(), ds.child("amount").getValue().toString(), R.drawable.ic_cash));
                }
            }

            public void onCancelled(DatabaseError databaseError){

            }
        });
    }
}

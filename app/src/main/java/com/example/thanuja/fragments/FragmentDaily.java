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
import com.example.thanuja.recyclerview.Expense;
import com.example.thanuja.recyclerview.RecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentDaily extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<Expense> lstContact;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstContact = new ArrayList<>();
        lstContact.add(new Expense("Gave to Imal", "Rs. 2000", R.drawable.images));
        lstContact.add(new Expense("Gave to Nuwanga", "Rs. 5000", R.drawable.images));
        lstContact.add(new Expense("Got a pizza", "Rs. 6000", R.drawable.images));
        lstContact.add(new Expense("Got a iPhone", "Rs. 100000", R.drawable.images));
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

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

import java.util.ArrayList;
import java.util.List;

public class FragmentMonthly extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<Expense> lstContact;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstContact = new ArrayList<>();
        lstContact.add(new Expense("Current bill", "Rs. 2000", R.drawable.dollar));
        lstContact.add(new Expense("Electricity bill", "Rs. 5000", R.drawable.dollar));
        lstContact.add(new Expense("Water bill", "Rs. 6000", R.drawable.dollar));
        lstContact.add(new Expense("Internet bill", "Rs. 8000", R.drawable.dollar));
        lstContact.add(new Expense("PeoTV bill", "Rs. 4000", R.drawable.dollar));
        lstContact.add(new Expense("Current bill", "Rs. 2000", R.drawable.dollar));
        lstContact.add(new Expense("Electricity bill", "Rs. 5000", R.drawable.dollar));
        lstContact.add(new Expense("Water bill", "Rs. 6000", R.drawable.dollar));
        lstContact.add(new Expense("Internet bill", "Rs. 8000", R.drawable.dollar));
        lstContact.add(new Expense("PeoTV bill", "Rs. 4000", R.drawable.dollar));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_thanuja_monthly_fragment, container, false);

        myrecyclerview = (RecyclerView)v.findViewById(R.id.contact_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), lstContact);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);

        return v;
    }
}

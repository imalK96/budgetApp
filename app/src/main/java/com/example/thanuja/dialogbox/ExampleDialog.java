package com.example.thanuja.dialogbox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.imal.R;
import com.example.thanuja.fragments.FragmentDaily;
import com.example.thanuja.fragments.ThanujaMain;
import com.example.thanuja.model.DailyExpense;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ExampleDialog extends AppCompatDialogFragment {
    private EditText discription;
    private EditText amount;
    private  ExampleDialogListener listener;

    DatabaseReference dbRef;
    DailyExpense de;
    private FirebaseAuth firebaseAuth;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_thanuja_dialogbox, null);

        discription = view.findViewById(R.id.discription);
        amount = view.findViewById(R.id.amount);

        de = new DailyExpense();
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        builder.setView(view)
                .setTitle(R.string.thanuja_addexpense)
                .setNegativeButton(R.string.thanuja_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(R.string.thanuja_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = discription.getText().toString();
                        String password = amount.getText().toString();

                        listener.applyTexts(username, password);

                        //database part added
                        dbRef = FirebaseDatabase.getInstance().getReference().child("Daily Expense");

                        de.setDiscription(discription.getText().toString().trim());
                        de.setAmount(Float.parseFloat(amount.getText().toString().trim()));

                        //dbRef.child(user.getUid()).child("expense1").setValue(de);
                        dbRef.child(user.getUid()).push().setValue(de);

                        Toast.makeText(getContext(),"Data saved successfully", Toast.LENGTH_SHORT).show();

                        Intent i1 = new Intent(getContext(), ThanujaMain.class);
                        startActivity(i1);

                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement ExampleDialogListener");
        }
    }
    public interface ExampleDialogListener{
        void applyTexts(String username, String password);
    }
}



package com.example.thanuja.recyclerview;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imal.R;
import com.example.thanuja.model.DailyExpense;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    List<Expense> mData;
    Dialog myDialog;

    DatabaseReference dbRef;
    DailyExpense de;
    private FirebaseAuth firebaseAuth;

    public RecyclerViewAdapter(Context mContext, List<Expense> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        de = new DailyExpense();
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();


        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.activity_thanuja_item_expense, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.activity_thanuja_updatae_delete_dialogbox);



        vHolder.item_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText edit_discription = (EditText)myDialog.findViewById(R.id.UDdiscription);
                final EditText edit_amount = (EditText)myDialog.findViewById(R.id.UDamount);
                Button update = (Button)myDialog.findViewById(R.id.UDbtnUpdate);
                Button delete = (Button)myDialog.findViewById(R.id.UDbtnDelete);

                edit_discription.setText(mData.get(vHolder.getAdapterPosition()).getExpenseName());
                edit_amount.setText(mData.get(vHolder.getAdapterPosition()).getAmount());

                Toast.makeText(mContext, "Text click " +String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                myDialog.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String d = mData.get(vHolder.getAdapterPosition()).getExpenseName();
                        final String a = mData.get(vHolder.getAdapterPosition()).getAmount();

                        final DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Daily Expense").child(user.getUid());
                        updRef.addListenerForSingleValueEvent(new ValueEventListener(){
                            public void onDataChange(DataSnapshot dataSnapshot){
                                for(DataSnapshot ds : dataSnapshot.getChildren()){
                                    String databaseDis = ds.child("discription").getValue().toString();
                                    String databaseAmt = ds.child("amount").getValue().toString();

                                    if(d.equalsIgnoreCase(databaseDis) && a.equalsIgnoreCase(databaseAmt)){
                                        de.setDiscription(edit_discription.getText().toString().trim());
                                        de.setAmount(Float.parseFloat(edit_amount.getText().toString().trim()));

                                        ds.getRef().setValue(de);
                                        Toast.makeText(mContext, "Data updated successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }

//                                if(dataSnapshot.hasChild("expense1")){
//                                    try{
//                                        de.setDiscription(edit_discription.getText().toString().trim());
//                                        de.setAmount(Float.parseFloat(edit_amount.getText().toString().trim()));
//
//                                        updRef.child("expense1").setValue(de);
//
//                                        Toast.makeText(mContext, "Data updated successfully", Toast.LENGTH_SHORT).show();
//
//                                    }catch(NumberFormatException e){
//                                        Toast.makeText(mContext, "Invalid contact number", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                                else{
//                                    Toast.makeText(mContext, "No source to update", Toast.LENGTH_SHORT).show();
//                                }
                            }

                            public void onCancelled(DatabaseError databaseError){

                            }
                        });

                    }
                });


                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getExpenseName());
        holder.tv_phone.setText(mData.get(position).getAmount());
        holder.img.setImageResource(mData.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_expense;

        private TextView tv_name;
        private TextView tv_phone;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_expense = (LinearLayout)itemView.findViewById(R.id.item_expense_id);

            tv_name = (TextView) itemView.findViewById(R.id.name_contact);
            tv_phone = (TextView) itemView.findViewById(R.id.phone_contact);
            img = (ImageView) itemView.findViewById(R.id.img_contact);
        }
    }
}

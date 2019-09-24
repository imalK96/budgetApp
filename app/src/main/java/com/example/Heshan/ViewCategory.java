package com.example.Heshan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imal.MainActivity;
import com.example.imal.R;
import com.example.thanuja.model.DailyExpense;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewCategory extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Category cat;
    Button b1,b2;
    categoryAdapter catAdapter;
    int flag ;
    Integer imgId = R.drawable.bug;
    private long backPressedTime;
    private FirebaseAuth firebaseAuth;


    @Override
    public void onBackPressed() {
        if(backPressedTime +  2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }
        else {

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);

        b1 = findViewById(R.id.catUpdate);
        b2 = findViewById(R.id.catDelete);
        cat = new Category();
        listView = (ListView) findViewById(R.id.listView1);
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Category").child(user.getUid());


        list = new ArrayList<>();
        final customListView customListView = new customListView(this,list,imgId);


            listView.setAdapter(customListView);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                try{
                String value = dataSnapshot.getValue(Category.class).toString();
                list.add(value);
                customListView.notifyDataSetChanged();
                }catch (Exception e){}

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Item clicked method
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String a[] = list.get(i).toString().split(" ",2);

                cat.setCatName(a[0]);
                cat.setAmount(a[1]);

                Toast.makeText(getApplicationContext(),"Item selected",Toast.LENGTH_SHORT).show();


            }
        });

        //Data delete
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final String str = cat.getCatName();
                final String amount = cat.getAmount();
                if(cat.getCatName() == ""){

                    Toast.makeText(getApplicationContext(),"Please Select Item before delete!",Toast.LENGTH_SHORT).show();

                }else{

                   final  DatabaseReference delref = FirebaseDatabase.getInstance().getReference().child("Category").child(user.getUid());
                   delref.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           for(DataSnapshot ds : dataSnapshot.getChildren()){
                               String datname = ds.child("catName").getValue().toString();
                               String datamount = ds.child("amount").getValue().toString();

                               if(str.equalsIgnoreCase(datname)){
                                   ds.getRef().removeValue();
                                   Toast.makeText(getApplicationContext(),"Data deleted successfully!",Toast.LENGTH_SHORT).show();
                                   Intent i1 = new Intent(getBaseContext(),ViewCategory.class);
                                    startActivity(i1);
                                    customListView.notifyDataSetChanged();

                               }
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });


                }

            }
        });

        //update
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!cat.getCatName().isEmpty()){
                Intent i2 = new Intent(getBaseContext(),catUpdate.class);
                i2.putExtra("idValue",cat.getCatName());
                i2.putExtra("idamount",cat.getAmount());

                startActivity(i2);

               }
                else{

                    Toast.makeText(getApplicationContext(),"Please Select Item before update!",Toast.LENGTH_SHORT).show();

                }

            }
        });



  }

  public class categoryAdapter extends BaseAdapter{

        Context context;

      public categoryAdapter(Context context) {
          this.context = context;
      }

      @Override
      public int getCount() {
          return list.size();
      }

      @Override
      public Object getItem(int i) {
          return i;
      }

      @Override
      public long getItemId(int i) {
          return 0;
      }

      @Override
      public View getView(int i, View view, ViewGroup viewGroup) {
          View rowView = View.inflate(context,R.layout.custom_cell,null);
          TextView nameText = rowView.findViewById(R.id.nameText);

          if(flag == i){
              nameText.setTextColor(getResources().getColor(R.color.colorPrimary));
              nameText.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));
          }

          nameText.setText(list.toString());



          return rowView;
      }
  }

  public void click(View view){


      Intent i1 = new Intent(getBaseContext(),AddCategory.class);
      startActivity(i1);

  }


    public void addExpense(View view){

        try {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            final DatabaseReference expref = FirebaseDatabase.getInstance().getReference().child("Monthly Expense").child(user.getUid());
            final DailyExpense d1 = new DailyExpense(cat.getCatName(), Float.parseFloat(cat.getAmount()));


            expref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    expref.push().setValue(d1);
                    Toast.makeText(getApplicationContext(), "Item added successfully!", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please Select Item to add the expense!", Toast.LENGTH_SHORT).show();
        }
    }


}

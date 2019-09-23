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

import com.example.imal.R;
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
    Module module;
    categoryAdapter catAdapter;
    int flag ;
    Integer imgId = R.drawable.bug;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);

        b1 = findViewById(R.id.catUpdate);
        b2 = findViewById(R.id.catDelete);
        cat = new Category();
        listView = (ListView) findViewById(R.id.listView1);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Category");
        list = new ArrayList<>();
        final customListView customListView = new customListView(this,list,imgId);

        //catAdapter = new categoryAdapter(this);
        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        //listView.setAdapter(new ArrayAdapter<String>(this,R.layout.custom_cell,list));
            listView.setAdapter(customListView);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(Category.class).toString();
                list.add(value);
                customListView.notifyDataSetChanged();

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                flag = i;
               //module.setGvalue_Id(list.get(i));
                cat.setCatID(list.get(i).substring(0,7));

                Toast.makeText(getApplicationContext(),"Item Selected",Toast.LENGTH_SHORT).show();
               //Toast.makeText(getApplicationContext(),module.getGvalue_Id(),Toast.LENGTH_SHORT).show();
               //module.setGvalue_Name(list.get(i));
                //cat.setCatID(String.valueOf(list.get(i)));
                //cat.setCatID(list.get(i).substring(0,6));

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String str = cat.getCatID();
//                Toast.makeText(getApplicationContext(),cat.getCatID().substring(0,19),Toast.LENGTH_SHORT).show();
               // final String str = cat.getCatID();

                //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                if(cat.catID == ""){

                    Toast.makeText(getApplicationContext(),"Please Select Item before delete!",Toast.LENGTH_SHORT).show();

                }else{

                    ref.child("Category").child(str).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ref.child(str).removeValue();
                            ref.child(str).child("catName").getKey();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Toast.makeText(getApplicationContext(),"Category is deleted",Toast.LENGTH_SHORT).show();

                    Intent i1 = new Intent(getBaseContext(),ViewCategory.class);
                    startActivity(i1);

                }

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cat.catID != ""){
                Intent i2 = new Intent(getBaseContext(),catUpdate.class);
                i2.putExtra("idValue",cat.getCatID());
                startActivity(i2);

                startActivity(i2);}
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





}

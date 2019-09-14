package com.example.thanuja.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.imal.R;
import com.example.thanuja.dialogbox.ExampleDialog;
import com.example.thanuja.fragments.FragmentDaily;
import com.example.thanuja.fragments.FragmentMonthly;
import com.example.thanuja.fragments.FragmentYearly;
import com.example.thanuja.fragments.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class ThanujaMain extends AppCompatActivity implements ExampleDialog.ExampleDialogListener{
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanuja_main);
        //setContentView(R.layout.activity_thanuja_daily_fragment);

        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Add fragment here
        adapter.AddFragment(new FragmentDaily(), "Daily");
        adapter.AddFragment(new FragmentMonthly(), "Monthly");
        adapter.AddFragment(new FragmentYearly(), "Yearly");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

        tablayout.getTabAt(0).setIcon(R.drawable.add_catergory);
        tablayout.getTabAt(1).setIcon(R.drawable.bitcoin);
        tablayout.getTabAt(2).setIcon(R.drawable.cat);

        //Remove shadow from action bar
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setElevation(0);


        FloatingActionButton fab = findViewById(R.id.fab);



//        textViewUsername = findViewById(R.id.textview_username);
//        textViewPassword = findViewById(R.id.textview_password);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        //FragmentDaily fragmentDemo = (FragmentDaily) getSupportFragmentManager().findFragmentById(R.id.viewpager_id);
//        FragmentDaily fragmentDemo = new FragmentDaily();
//        //above part is to determine which fragment is in your frame_container
//        setFragment(fragmentDemo);

    }

//    protected void setFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(android.R.id.content, fragment);
//        fragmentTransaction.commit();
//    }



    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String username, String password) {
//        textViewUsername.setText(username);
//        textViewPassword.setText(password);
    }
}

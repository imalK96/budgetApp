package com.example.Heshan;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Module extends Application {

    public ArrayList <String> garList = new ArrayList<>();
    public ArrayAdapter<String> garAdp;
    public String gvalue_Id = "";
    public String gvalue_Name;

    public String getGvalue_Id() {
        return gvalue_Id;
    }

    public void setGvalue_Id(String gvalue_Id) {
        this.gvalue_Id = gvalue_Id;
    }

    public String getGvalue_Name() {
        return gvalue_Name;
    }

    public void setGvalue_Name(String gvalue_Name) {
        this.gvalue_Name = gvalue_Name;
    }
}

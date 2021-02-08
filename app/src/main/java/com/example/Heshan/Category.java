package com.example.Heshan;

import java.util.List;

public class Category {


//properties, constructor, getters and  setters
    private String catName = "";
    private String amount;

    public Category() {

    }

    public Category(String catName, String amount) {


        this.catName = catName;
        this.amount = amount;
    }



    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String toString(){

        return  this.catName + "  " + this.amount;
    }
}

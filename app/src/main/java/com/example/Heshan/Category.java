package com.example.Heshan;

import java.util.List;

public class Category {

    public String catID = "" ;
    private static int count = 0;
    private String catName;
    private double amount;

    public Category() {

    }

    public Category(String catName, double amount) {
        //this.catID = catID;
        catID = "CatID=" + count;
        count++;
        this.catName = catName;
        this.amount = amount;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toString(){

        return  this.catID + "\n"+ "Category Name = " + this.catName + "\n" + "Amount = " + this.amount + "\n";
    }
}

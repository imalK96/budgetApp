package com.example.thanuja.recyclerview;

public class Expense {
    private String expenseName;
    private String amount;
    private int image;

    public Expense(String expenseName, String amount, int image) {
        this.expenseName = expenseName;
        this.amount = amount;
        this.image = image;
    }

    //Getters
    public String getExpenseName() {
        return expenseName;
    }

    public String getAmount() {
        return amount;
    }

    public int getImage() {
        return image;
    }

    //Setters
    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

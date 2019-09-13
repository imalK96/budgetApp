package com.example.thanuja.model;

public class DailyExpense {
    private String discription;
    private float amount;

    public DailyExpense(){

    }

    public DailyExpense(String discription, float amount) {
        this.discription = discription;
        this.amount = amount;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}

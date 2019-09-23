package com.example.imal;

public class EnterBudgetDB {

    private  String startdate;
    private  String enddate;
    private double income;
    private double interest;
    private double salary;

    public EnterBudgetDB() {
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public double getIncome() {
        return income;
    }

    public double getInterest() {
        return interest;
    }

    public double getSalary() {
        return salary;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

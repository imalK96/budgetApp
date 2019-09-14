package com.example.imal;

public class EnterBudgetDB {

    private  String startdate;
    private  String enddate;
    private String income;
    private String interest;
    private String salary;

    public EnterBudgetDB() {
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public String getIncome() {
        return income;
    }

    public String getInterest() {
        return interest;
    }

    public String getSalary() {
        return salary;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}

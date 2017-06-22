package com.ibericoders.ibericoders.controlgastos.model;



public class Expense {

    private String name;
    private String description;
    private double amount;
    private String date;
    private String category;

    public Expense(String name, String description, double amount, String date, String category) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return name +"|"+ description +"|"+ amount +"|"+ date;
    }

}

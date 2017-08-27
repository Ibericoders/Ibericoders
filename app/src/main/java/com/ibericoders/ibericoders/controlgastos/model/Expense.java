package com.ibericoders.ibericoders.controlgastos.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Expense implements Parcelable{

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


    /*
     * Implementación de Parcelable
     */
    protected Expense(Parcel in) {
        name = in.readString();
        description = in.readString();
        amount = in.readDouble();
        date = in.readString();
        category = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeDouble(amount);
        dest.writeString(date);
        dest.writeString(category);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Expense> CREATOR = new Parcelable.Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

}

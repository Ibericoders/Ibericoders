package com.ibericoders.ibericoders.controlgastos.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class ExpensesData {

    private SQLiteDatabase db;
    public ExpensesData(Context ctx){
        //Creación objeto ayudante y obtención de la base de datos
        Helper helper = new Helper(ctx, "data", 1);
        db= helper.getWritableDatabase();
    }

    public void SaveNewExpense(Expense expense){
        if(!CheckExpense(expense.getName())){
            String sql="insert into expensesTable (name,description,amount,date,category) ";
            sql+="values ('"+expense.getName()+"','"+expense.getDescription()+"',"+expense.getAmount()+",'"+expense.getDate()+"','"+expense.getCategory()+"')";
            db.execSQL(sql);
        }
    }

    public boolean CheckExpense(String expenseName){
        boolean res=false;
        String sql="select * from expensesTable where name='"+expenseName+"'";
        Cursor c= db.rawQuery(sql,null);
        while(c.moveToNext()){
            res=true;
        }
        c.close();
        return res;
    }

    public ArrayList<Expense> getAllExpenses(){
        String sql="select * from expensesTable";
        Cursor c= db.rawQuery(sql,null);
        ArrayList<Expense> expenses =new ArrayList<>();
        while(c.moveToNext()){
            Expense g=new Expense(c.getString(1),c.getString(2),Double.parseDouble(c.getString(3)),c.getString(4),c.getString(5));
            expenses.add(g);
        }
        c.close();
        return expenses;
    }

    public ArrayList<Expense> CheckCategoryExpenses(String category){
        String sql="select * from expensesTable where category='"+category+"'";
        Cursor c= db.rawQuery(sql,null);
        ArrayList<Expense> expenses =new ArrayList<>();
        while(c.moveToNext()){
            Expense g=new Expense(c.getString(1),c.getString(2),Double.parseDouble(c.getString(3)),c.getString(4),c.getString(5));
            expenses.add(g);
        }
        c.close();
        return expenses;
    }

    public Expense getExpense(String name){
        String sql="select * from expensesTable where name='"+name+"'";
        Cursor c= db.rawQuery(sql,null);
        Expense g=null;
        while(c.moveToNext()){
            g=new Expense(c.getString(1),c.getString(2),Double.parseDouble(c.getString(3)),c.getString(4),c.getString(5));
        }
        c.close();
        return g;
    }

    public void deleteExpense(String name){
        String sql="delete * from expensesTable where name='"+name+"'";
        db.execSQL(sql);
    }
}
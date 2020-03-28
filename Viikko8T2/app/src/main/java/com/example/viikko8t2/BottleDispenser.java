package com.example.viikko8t2;

import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BottleDispenser {

    ArrayList<Bottle> bottleList = new ArrayList<Bottle>();
    TextView textViewNotif;
    private double money;

    //Singleton-periaate
    private static BottleDispenser bd = new BottleDispenser();


    //Singletonissa constructor on yksityinen
    private BottleDispenser() {
        money = 0;

        bottleList.add(new Bottle());
        bottleList.add(new Bottle("Pepsi Max",1.5, 2.2));
        bottleList.add(new Bottle("Coca-Cola Zero",0.5, 2.0));
        bottleList.add(new Bottle("Coca-Cola Zero",1.5, 2.5));
        bottleList.add(new Bottle("Fanta Zero",0.5, 1.95));

    }

    //Singleton
    public static BottleDispenser getInstance() {
        return bd;
    }




    public void addMoney(TextView tv) {
        textViewNotif = tv;
        money += 1;
        tv.setText("Added money!");
    }



    public void buyBottle(TextView tv, int pullo) {
        textViewNotif = tv;
        int index = pullo-1;
        if (bottleList.size()== 0) {
            tv.setText("Machine is empty");
            return;
        }

        if (money < bottleList.get(index).getPrice()) {
            tv.setText("Add money first!");
            return;
        }

        tv.setText("KACHUNK! " + bottleList.get(index).getName() + " came out of the dispenser!");

        money-= bottleList.get(index).getPrice();
        removeBottle(index);
    }



    public void removeBottle(int pullo) {
        bottleList.remove(pullo);
    }




    public void returnMoney(TextView tv) {
        textViewNotif = tv;
        DecimalFormat muotoilija = new DecimalFormat("#0.00");

        String paska = muotoilija.format(money);
        paska = paska.replace(",", ".");

        tv.setText("Klink klink. Money came out! You got " + paska +"€ back");

        money = 0;

    }



    public void listBottles() {
        for(int i=0;i<bottleList.size();i++) {
            System.out.println(i+1 + ". Name: "+bottleList.get(i).getName()+"\n" +
                    "	Size: "+bottleList.get(i).getSize()+"	Price: "+bottleList.get(i).getPrice());
        }
    }



}


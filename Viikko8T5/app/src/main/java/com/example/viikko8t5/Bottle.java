package com.example.viikko8t5;

public class Bottle {

    public String name = "Pepsi Max";

    public String manufacturer = "Pepsi";

    public double total_energy = 0.3;
    public double size = 0.5;
    public double price = 1.80;


    public Bottle(){}

    public Bottle(String namei, double sizei, double pricei){
        name = namei;
        size = sizei;
        price = pricei;
    }

    public String getName(){
        return name;
    }
    //public String getManufacturer(){}

    //public double getEnergy(){}
    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }
}
package com.example.viikko8t3;

public class Bottle {

    private String name = "Pepsi Max";

    private String manufacturer = "Pepsi";

    private double total_energy = 0.3;
    private double size = 0.5;
    private double price = 1.80;


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


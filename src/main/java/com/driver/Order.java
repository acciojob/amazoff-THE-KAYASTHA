package com.driver;

public class Order {

    private String id;
    private int deliveryTime;
     Order(){

    }

    public Order(String id, String deliveryTime) {
        this.id=id;
        String[] timeComponents = deliveryTime.split(":");
        int HH = Integer.parseInt(timeComponents[0]);
        int MM = Integer.parseInt(timeComponents[1]);
        this.deliveryTime=HH*60+MM;


        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}

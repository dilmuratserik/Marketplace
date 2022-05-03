package com.example.marketplace.models;

public class MyCartModel {
    String currentTime;
    String currentDate;
    String productName;
    String productPrice;
    String totalQuantity;
    int totalPrice;

    public MyCartModel(){

    }
    public MyCartModel(String currentTime, String currentDate, String productName, String productPrice, String totalQuantity, int totalPrice) {
        this.currentTime = currentTime;
        this.currentDate = currentDate;
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }
    public String getCurrentTime(){
        return currentTime;
    }
}

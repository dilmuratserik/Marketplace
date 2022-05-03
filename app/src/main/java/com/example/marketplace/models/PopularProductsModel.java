package com.example.marketplace.models;

import java.io.Serializable;

public class PopularProductsModel implements Serializable {
    String description;
    String name;
    int rating;
    int price ;
    String img_url;


    public PopularProductsModel(String description,String name, int rating, int price, String img_url) {
        this.description = description;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.img_url = img_url;
    }
    public PopularProductsModel(){

    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getPrice() {
        return price;
    }

    public String getImg_url() {
        return img_url;
    }

}

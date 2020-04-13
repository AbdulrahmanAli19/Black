package com.example.black.Classes;

import java.util.ArrayList;

public class Order {
    public ArrayList<BagItems> productItems = new ArrayList<>();
    public User user ;

    public Order(ArrayList<BagItems> productItems, User user) {
        this.productItems = productItems;
        this.user = user;
    }

    public Order(){

    }
}

package com.example.black.Classes;


import android.widget.ImageView;

public class BagItems {
   private int mImage ;
   private String mProductName;
   private String mSize ;
   private int mProductPrice;
   private int mProductSalePrice ;

    public BagItems(int mImage, String mProductName, String Size, int mProductPrice, int mProductSalePrice) {
        this.mImage = mImage;
        this.mProductName = mProductName;
        this.mProductPrice = mProductPrice;
        this.mProductSalePrice = mProductSalePrice;
        this.mSize = Size ;
    }

    public BagItems(int mImage, String mProductName, int mProductPrice, int mProductSalePrice) {
        this.mImage = mImage;
        this.mProductName = mProductName;
        this.mProductPrice = mProductPrice;
        this.mProductSalePrice = mProductSalePrice;
    }

    public int getmImage() {
        return mImage;
    }

    public String getmProductName() {
        return mProductName;
    }

    public String getmSize() {
        return mSize;
    }

    public int getmProductPrice() {
        return mProductPrice;
    }

    public int getmProductSalePrice() {
        return mProductSalePrice;
    }
}

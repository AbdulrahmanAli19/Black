package com.example.black.Classes;

public class ProductItems {
    private int mProductImage, mProductPrice, mProductSalePrice;
    private String mProductName;

    public ProductItems(int mImage, int mPrice, int mSalePrice, String mName) {
        this.mProductImage = mImage;
        this.mProductPrice = mPrice;
        this.mProductSalePrice = mSalePrice;
        this.mProductName = mName;
    }

    public ProductItems(int mImage, int mPrice, String mName) {
        this.mProductImage = mImage;
        this.mProductPrice = mPrice;
        this.mProductName = mName;
    }

    public int getmProductImage() {
        return mProductImage;
    }

    public int getmProductPrice() {
        return mProductPrice;
    }

    public int getmProductSalePrice() {
        return mProductSalePrice;
    }

    public String getmProductName() {
        return mProductName;
    }
}

package com.example.black.Classes;

public class HelpItmes {
    private String mText;
    private int mImage, mNextImage ;

    public HelpItmes(String mText, int mImage, int mNextImage) {
        this.mText = mText;
        this.mImage = mImage;
        this.mNextImage = mNextImage;
    }

    public String getmText() {
        return mText;
    }

    public int getmImage() {
        return mImage;
    }

    public int getmNextImage() {
        return mNextImage;
    }
}

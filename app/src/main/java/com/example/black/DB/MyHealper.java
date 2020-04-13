package com.example.black.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyHealper extends SQLiteOpenHelper {

    private final static String TABLE_1 = "Users" ;
    private final static String COL_1 = "Name" ;
    private final static String COL_2 = "Surname_Name" ;
    private final static String COL_3 = "Email" ;
    private final static String COL_4 = "Password" ;
    private final static String COL_5 = "Address" ;
    private final static String COL_6 = "More_Info" ;
    private final static String COL_7 = "Phone_key" ;
    private final static String COL_8 = "Phone_Number" ;
    private final static String COL_9 = "Government" ;

    public MyHealper(@Nullable Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+TABLE_1+"" +
                " ( ID integer primary key,"
                +COL_1+" varchar(50), "
                +COL_2+" varchar(50), "
                +COL_3+" string, "
                +COL_4+" varchar(30), "
                +COL_5+" string, "
                +COL_6+" string, "
                +COL_7+" string, "
                +COL_8+" string, "
                +COL_9+" varchar(30))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

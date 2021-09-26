package com.example.filmlercalisma.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VeriTabaniYardimcisi extends SQLiteOpenHelper {




    public VeriTabaniYardimcisi(@Nullable Context context) {
        super(context,"filmler.sqlite",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS 'kategoriler'  ( 'kategori_id' INTEGER " +
               "PRIMARY KEY AUTOINCREMENT, 'kategori_ad' TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS yonetmenler  (\n" +
                "yonetmen_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "yonetmen_ad TEXT\n" +
                ")");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"filmler\" (\n" +
                "\t`film_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`film_ad`\tTEXT,\n" +
                "\t`film_yil`\tINTEGER,\n" +
                "\t`film_resim`\tTEXT,\n" +
                "\t`kategori_id`\tINTEGER,\n" +
                "\t`yonetmen_id`\tINTEGER,\n" +
                "\tFOREIGN KEY(`kategori_id`) REFERENCES `kategoriler`(`kategoril_id`),\n" +
                "\tFOREIGN KEY(`yonetmen_id`) REFERENCES `yonetmenler`(`yonetmen_id`)\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF  EXISTS kategoriler");
        sqLiteDatabase.execSQL("DROP TABLE IF  EXISTS yonetmenler");
        sqLiteDatabase.execSQL("DROP TABLE IF  EXISTS filmler");
        onCreate(sqLiteDatabase);

    }
}

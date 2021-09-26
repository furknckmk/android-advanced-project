package com.example.filmlercalisma.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.filmlercalisma.objects.Kategoriler;

import java.util.ArrayList;

public class KategorilerDao {

    public ArrayList<Kategoriler> tumKategoriler(VeriTabaniYardimcisi vt){
        ArrayList<Kategoriler> kategorilerArrayList=new ArrayList<>();

        SQLiteDatabase db=vt.getWritableDatabase();
        Cursor c =db.rawQuery("SELECT * FROM kategoriler",null );

        while (c.moveToNext()){
            Kategoriler k = new Kategoriler(c.getInt(c.getColumnIndex("kategori_id")),c.getString(c.getColumnIndex("kategori_ad")));
            kategorilerArrayList.add(k);
        }
        return kategorilerArrayList;
    }
}

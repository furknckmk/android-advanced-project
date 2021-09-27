package com.example.kisilerapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class KisilerDao {
    public ArrayList<Kisiler> tumKisiler(VeriTabani vt){
        ArrayList<Kisiler> kisilerArrayList=new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c =db.rawQuery("SELECT * FROM kisiler",null);
        while (c.moveToNext()){
            Kisiler k = new Kisiler(c.getInt(c.getColumnIndex("kisi_id"))
                    ,c.getString(c.getColumnIndex("kisi_ad"))
                    ,c.getString(c.getColumnIndex("kisi_tel")));
            kisilerArrayList.add(k);
        }
        db.close();
        return kisilerArrayList;
    }
    public ArrayList<Kisiler> kisiAra(VeriTabani vt,String aramaKelime){
        ArrayList<Kisiler> kisilerArrayList=new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c =db.rawQuery("SELECT * FROM kisiler WHERE kisi_ad like '%"+aramaKelime+"%'",null);
        while (c.moveToNext()){
            Kisiler k = new Kisiler(c.getInt(c.getColumnIndex("kisi_id"))
                    ,c.getString(c.getColumnIndex("kisi_ad"))
                    ,c.getString(c.getColumnIndex("kisi_tel")));
            kisilerArrayList.add(k);
        }
        db.close();
        return kisilerArrayList;
    }
    public  void kisiSil(VeriTabani vt,int kisi_id){
        SQLiteDatabase db=vt.getWritableDatabase();
        db.delete("kisiler","kisi_id=?",new String[]{String.valueOf(kisi_id)});
        db.close();
    }
    public  void kisiEkle(VeriTabani vt,String kisi_ad,String kisi_tel){
        SQLiteDatabase db=vt.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("kisi_ad",kisi_ad);
        values.put("kisi_tel",kisi_tel);

        db.insertOrThrow("kisiler",null,values);
        db.close();

    }
    public  void kisiGuncelle(VeriTabani vt,int kisi_id,String kisi_ad,String kisi_tel){
        SQLiteDatabase db=vt.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("kisi_ad",kisi_ad);
        values.put("kisi_tel",kisi_tel);

        db.update("kisiler",values,"kisi_id=?",new String[]{String.valueOf(kisi_id)});
        db.close();

    }
}

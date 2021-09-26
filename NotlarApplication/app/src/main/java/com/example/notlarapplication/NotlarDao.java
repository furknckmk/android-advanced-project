package com.example.notlarapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotlarDao {
    public ArrayList<Notlar> tumNotlar(VeriTabani vt){  // Butun notlari veri tabanindan cekecek method
        ArrayList<Notlar> notlarArrayList= new ArrayList<>();// Her seferinde kendini yenileyerek icerisinde yeni notlari tutucak
        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM notlar",null);

        while (c.moveToNext()){

            Notlar n = new Notlar(c.getInt(c.getColumnIndex("not_id"))
                    ,c.getString(c.getColumnIndex("ders_adi"))
                    ,c.getInt(c.getColumnIndex("not1"))
                    ,c.getInt(c.getColumnIndex("not2")));

            notlarArrayList.add(n);
        }
        db.close();
        return notlarArrayList;
    }

    public void notSil(VeriTabani vt,int not_id){
        SQLiteDatabase db= vt.getWritableDatabase();
        db.delete("notlar","not_id=?",new String[]{String.valueOf(not_id)});
        db.close();

    }

    public void  notEkle (VeriTabani vt,String ders_adi,int not1,int not2){
        SQLiteDatabase db=vt.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("ders_adi",ders_adi);
        values.put("not1",not1);
        values.put("not2",not2);

        db.insertOrThrow("notlar",null,values);
        db.close();
    }
    public void  notDuzenle (VeriTabani vt,int not_id,String ders_adi,int not1,int not2){
        SQLiteDatabase db=vt.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("ders_adi",ders_adi);
        values.put("not1",not1);
        values.put("not2",not2);

        db.update("notlar",values,"not_id=?",new String[]{String.valueOf(not_id)});
        db.close();
    }

}

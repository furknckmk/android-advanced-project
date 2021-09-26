package com.example.notlarapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VeriTabani extends SQLiteOpenHelper {

    public VeriTabani(@Nullable Context context) {
        super(context, "notlar.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE notlar  (\n" +
                "not_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "ders_adi TEXT,\n" +
                "not1 INTEGER,\n" +
                "not2 INTEGER\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS notlar");
        onCreate(sqLiteDatabase);
    }
}

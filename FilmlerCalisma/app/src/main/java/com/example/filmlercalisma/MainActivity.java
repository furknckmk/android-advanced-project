package com.example.filmlercalisma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.filmlercalisma.dao.DatabaseCopyHelper;
import com.example.filmlercalisma.dao.FilmlerDao;
import com.example.filmlercalisma.dao.KategorilerDao;
import com.example.filmlercalisma.dao.VeriTabaniYardimcisi;
import com.example.filmlercalisma.objects.Filmler;
import com.example.filmlercalisma.objects.Kategoriler;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private VeriTabaniYardimcisi vt = new VeriTabaniYardimcisi(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kopyala();
        ArrayList<Filmler> liste = new FilmlerDao().tumFilmler(vt);

        for (Filmler f : liste){

            Log.e("*************","************");
            Log.e("Film id : ",String.valueOf(f.getFilm_id()));
            Log.e("Film ad : ",f.getFilm_ad());
            Log.e("Film yil : ",String.valueOf(f.getFilm_yil()));
            Log.e("Film resim : ",f.getFilm_resim());
            Log.e("Film kategori : ",f.getKategori().getKategori_ad());
            Log.e("Film yonetmen : ",f.getYonetmen().getYonetmen_ad());


            Log.e("*************","************");
        }


    }

    public void kopyala(){

        DatabaseCopyHelper helper =new DatabaseCopyHelper(this);
        try {
            helper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        helper.openDataBase();
    }
}
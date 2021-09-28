package com.example.filmler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Locale;

public class FilmlerActivity extends AppCompatActivity {
    private Toolbar filmlerToolbar;
    private RecyclerView filmlerRv;
    private ArrayList<Filmler> filmlerArrayList;
    private FilmlerAdapter adapter;
    private Kategoriler kategori;
    private Yonetmenler yonetmen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmler);

        filmlerToolbar = findViewById(R.id.filmlerToolbar);
        filmlerRv = findViewById(R.id.filmlerRv);

        filmlerToolbar.setTitle("Filmler");
        setSupportActionBar(filmlerToolbar);

        filmlerRv.setHasFixedSize(true);
        filmlerRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        filmlerArrayList = new ArrayList<>();

        Filmler f1 = new Filmler(1,"django",2019,"django",null,null);
        Filmler f2 = new Filmler(2,"inception",2019,"inception",null,null);
        Filmler f3 = new Filmler(3,"The Pianist",2015,"thepianist",null,null);
        filmlerArrayList.add(f1);
        filmlerArrayList.add(f2);
        filmlerArrayList.add(f3);

        adapter= new FilmlerAdapter(this,filmlerArrayList);

        filmlerRv.setAdapter(adapter);


    }
}
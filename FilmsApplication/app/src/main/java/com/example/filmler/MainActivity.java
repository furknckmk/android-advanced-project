package com.example.filmler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView kategoriRv;
    private ArrayList<Kategoriler> kategorilerArrayList;
    private KategoriAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        kategoriRv = findViewById(R.id.KategoriRv);

        toolbar.setTitle("Kategoriler");
        setSupportActionBar(toolbar);

        kategoriRv.setHasFixedSize(true);
        kategoriRv.setLayoutManager(new LinearLayoutManager(this));

        kategorilerArrayList = new ArrayList<>();

        Kategoriler k1= new Kategoriler(1,"komedi");
        Kategoriler k2= new Kategoriler(2,"bilim kurgu");

        kategorilerArrayList.add(k1);
        kategorilerArrayList.add(k2);

        adapter = new KategoriAdapter(this,kategorilerArrayList);
        kategoriRv.setAdapter(adapter);

    }
}
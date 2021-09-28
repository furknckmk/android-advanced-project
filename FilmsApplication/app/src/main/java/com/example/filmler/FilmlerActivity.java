package com.example.filmler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class FilmlerActivity extends AppCompatActivity {
    private Toolbar filmlerToolbar;
    private RecyclerView filmlerRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmler);

        filmlerToolbar = findViewById(R.id.filmlerToolbar);
        filmlerRv = findViewById(R.id.filmlerRv);

        filmlerToolbar.setTitle("Filmler");
        setSupportActionBar(filmlerToolbar);

        filmlerRv.setHasFixedSize(true);

    }
}
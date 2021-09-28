package com.example.filmler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetayActivity extends AppCompatActivity {
    private ImageView filmResim;
    private TextView textViewFilmAdDetay,textViewYil,textViewYonetmen;
    private Filmler film;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        filmResim = findViewById(R.id.imageViewResim);
        textViewFilmAdDetay = findViewById(R.id.textViewFilmAdDetay);
        textViewYil=findViewById(R.id.textViewYil);
        textViewYonetmen=findViewById(R.id.textViewYonetmen);

        film = (Filmler) getIntent().getSerializableExtra("nesne");

        textViewFilmAdDetay.setText(film.getFilm_ad());
        textViewYil.setText(String.valueOf(film.getFilm_yil()));
        //textViewYonetmen.setText(film.getYonetmen().getYonetmen_ad());
        filmResim.setImageResource(getResources().getIdentifier(film.getFilm_resim()
                ,"drawable",getPackageName()));

    }
}
package com.example.filmler;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmlerAdapter extends RecyclerView.Adapter<FilmlerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Filmler> filmlerListe;


    public FilmlerAdapter(Context mContext, List<Filmler> filmlerListe) {
        this.mContext = mContext;
        this.filmlerListe = filmlerListe;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_card_tasarim,parent,false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
       final Filmler film= filmlerListe.get(position);
        holder.textViewFilmAd.setText(film.getFilm_ad());
        holder.imageViewFilmResim.setImageResource(mContext.getResources().getIdentifier(film.getFilm_resim()
                ,"drawable",mContext.getPackageName()));

        holder.film_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,DetayActivity.class);
                intent.putExtra("nesne",film);

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return filmlerListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private CardView film_card;
        private TextView textViewFilmAd;
        private ImageView imageViewFilmResim;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            imageViewFilmResim=itemView.findViewById(R.id.imageFilmResim);
            film_card= itemView.findViewById(R.id.film_card);
            textViewFilmAd = itemView.findViewById(R.id.textViewFilmAd);
        }
    }
}

package com.example.kisilerapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class KisilerAdapter extends RecyclerView.Adapter<KisilerAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<Kisiler> kisilerListe;
    private VeriTabani vt;

    public KisilerAdapter(Context mContext, List<Kisiler> kisilerListe, VeriTabani vt) {
        this.mContext = mContext;
        this.kisilerListe = kisilerListe;
        this.vt = vt;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kisi_card_tasarim, parent, false);


        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        final Kisiler kisi = kisilerListe.get(position);
        holder.textViewBilgi.setText(kisi.getKisi_ad() + " - " + kisi.getKisi_tel());

        holder.imageViewNokta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.imageViewNokta);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_sil:
                                Snackbar.make(holder.imageViewNokta, "Kisi Silinsin mi ?", Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        new KisilerDao().kisiSil(vt, kisi.getKisi_id());
                                        kisilerListe = new KisilerDao().tumKisiler(vt);
                                        notifyDataSetChanged();
                                    }
                                }).show();
                                return true;
                            case R.id.action_guncelle:
                                alertGoster(kisi);
                                return true;
                            default:
                                return false;

                        }
                    }
                });

                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return kisilerListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private TextView textViewBilgi;
        private ImageView imageViewNokta;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            textViewBilgi = itemView.findViewById(R.id.textViewBilgi);
            imageViewNokta = itemView.findViewById(R.id.imageViewNokta);

        }
    }

    public void alertGoster(Kisiler kisi) {
        LayoutInflater layout = LayoutInflater.from(mContext);
        View tasarim = layout.inflate(R.layout.alert_design, null);

        final EditText editTextAd = tasarim.findViewById(R.id.editTextTextAd);
        final EditText editTextTel = tasarim.findViewById(R.id.editTextTextTel);

        editTextAd.setText(kisi.getKisi_ad());
        editTextTel.setText(kisi.getKisi_tel());

        AlertDialog.Builder ad = new AlertDialog.Builder(mContext);

        ad.setTitle("Kisi Guncelle");
        ad.setView(tasarim);
        ad.setPositiveButton("Guncelle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String kisi_ad = editTextAd.getText().toString().trim();
                String kisi_tel = editTextTel.getText().toString().trim();

                new KisilerDao().kisiGuncelle(vt, kisi.getKisi_id(), kisi_ad, kisi_tel);
                kisilerListe = new KisilerDao().tumKisiler(vt);
                notifyDataSetChanged();
            }
        });
        ad.setNegativeButton("Iptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        ad.create().show();
    }
}

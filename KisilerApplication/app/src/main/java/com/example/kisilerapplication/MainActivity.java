package com.example.kisilerapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private ArrayList<Kisiler> kisilerArrayList;
    private KisilerAdapter adapter;
    private VeriTabani vt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        rv=findViewById(R.id.rv);
        fab=findViewById(R.id.fab);

        toolbar.setTitle("Kisiler Uygulamasi");
        setSupportActionBar(toolbar);

        vt=new VeriTabani(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        kisilerArrayList = new KisilerDao().tumKisiler(vt);

        adapter = new KisilerAdapter(this,kisilerArrayList,vt);
        rv.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertGoster();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);

        MenuItem menuItem=menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("Girdi",newText);
        kisilerArrayList = new KisilerDao().kisiAra(vt,newText);

        adapter = new KisilerAdapter(this,kisilerArrayList,vt);
        rv.setAdapter(adapter);

        return false;
    }
    public void alertGoster(){
        LayoutInflater layout=LayoutInflater.from(this);
        View tasarim =layout.inflate(R.layout.alert_design,null);

     final EditText editTextAd= tasarim.findViewById(R.id.editTextTextAd);
     final EditText editTextTel =tasarim.findViewById(R.id.editTextTextTel);

        AlertDialog.Builder ad=new AlertDialog.Builder(this);

        ad.setTitle("Kisi Ekle");
        ad.setView(tasarim);
        ad.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
        String kisi_ad= editTextAd.getText().toString().trim();
        String kisi_tel= editTextTel.getText().toString().trim();

        new KisilerDao().kisiEkle(vt,kisi_ad,kisi_tel);

        kisilerArrayList = new KisilerDao().tumKisiler(vt);
                adapter = new KisilerAdapter(MainActivity.this,kisilerArrayList,vt);
                rv.setAdapter(adapter);



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
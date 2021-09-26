package com.example.notlarapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class NotKayitActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText editTextDers,editTextNot1,editTextNot2;
    private Button buttonKaydet;
    private VeriTabani vt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_kayit);
        vt = new VeriTabani(this);
            toolbar = findViewById(R.id.toolbar2);
        editTextDers = findViewById(R.id.editTextDers);
        editTextNot1 = findViewById(R.id.editTextNot1);
        editTextNot2 = findViewById(R.id.editTextNot2);
        buttonKaydet = findViewById(R.id.buttonKaydet);

        toolbar.setTitle("Not Kayit");
        setSupportActionBar(toolbar);

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kenar bosluklari silme
                String ders_adi = editTextDers.getText().toString().trim();
                String not1 = editTextNot1.getText().toString().trim();
                String not2 = editTextNot2.getText().toString().trim();

                if (TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(view,"Ders Adi giriniz",Snackbar.LENGTH_SHORT).show();
                return;
                }
                if (TextUtils.isEmpty(not1)){
                    Snackbar.make(view,"Not 1 giriniz",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(not2)){
                    Snackbar.make(view,"Not 2 giriniz",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                new NotlarDao().notEkle(vt,ders_adi,Integer.parseInt(not1),Integer.parseInt(not2));

                startActivity(new Intent(NotKayitActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}
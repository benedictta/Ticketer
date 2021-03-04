package com.udinus.ticketer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class edit extends AppCompatActivity {

    EditText noktp1;
    Button search;
    DatabaseHelper bantuDB;

    String nama;
    String noktp;
    String tanggal;
    String asal;
    String tujuan;
    String kereta;
    String kelas;
    String berangkat;
    String datang;
    String harga;

    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        noktp1 = (EditText)findViewById(R.id.noktp2);
        search = (Button)findViewById(R.id.btnSearch);

        bantuDB = new DatabaseHelper(this);

        back = (ImageButton)findViewById(R.id.kembali5);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cekNoKTP())
                    Toast.makeText(edit.this, "No KTP Tidak Terdaftar", Toast.LENGTH_SHORT).show();
                else
                {
                    Cursor ambil =bantuDB.getAllData();
                    while (ambil.moveToNext()) {

                        if (ambil.getString(0).matches(noktp1.getText().toString())) {

                            noktp = ambil.getString(0);
                            nama = ambil.getString(1);
                            kereta = ambil.getString(2);
                            kelas = ambil.getString(3);
                            asal = ambil.getString(4);
                            tujuan = ambil.getString(5);
                            tanggal = ambil.getString(6);
                            berangkat = ambil.getString(7);
                            datang = ambil.getString(8);
                            harga = ambil.getString(9);
                        }
                    }

                    Intent a = new Intent(edit.this, edit2.class);
                    a.putExtra("no ktp",noktp);
                    a.putExtra("nama", nama);
                    a.putExtra("tanggal",tanggal);
                    a.putExtra("asal",asal);
                    a.putExtra("tujuan",tujuan);
                    a.putExtra("kereta",kereta);
                    a.putExtra("kelas",kelas);
                    a.putExtra("berangkat",berangkat);
                    a.putExtra("datang",datang);
                    a.putExtra("harga",harga);
                    startActivity(a);
                }
            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
    public boolean cekNoKTP() {
        boolean Sama = false;
        Cursor cek =bantuDB.getAllData();
        while (cek.moveToNext()) {

            if (cek.getString(0).matches(noktp1.getText().toString())) {

                Sama = true;
            }
        }
        return Sama;
    }
}

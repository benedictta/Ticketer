package com.udinus.ticketer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class tambah3 extends AppCompatActivity {

    String nama;
    String noktp;
    String tanggal;
    String asal;
    String tujuan;
    String kereta;
    String kelas;
    String berangkat;
    String datang;

    TextView noktp1;
    TextView nama1;
    TextView tanggal1;
    TextView asal1;
    TextView tujuan1;
    TextView kereta1;
    TextView kelas1;
    TextView berangkat1;
    TextView datang1;
    TextView harga1;

    ImageButton back;
    Button confirm;

    Integer harga;

    DatabaseHelper bantuDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah3);

        Intent ambil = getIntent();
        noktp = ambil.getStringExtra("no ktp");
        nama = ambil.getStringExtra("nama");
        tanggal = ambil.getStringExtra("tanggal");
        asal = ambil.getStringExtra("asal");
        tujuan = ambil.getStringExtra("tujuan");
        kereta = ambil.getStringExtra("kereta");
        kelas = ambil.getStringExtra("kelas");
        berangkat = ambil.getStringExtra("berangkat");
        datang = ambil.getStringExtra("datang");

        noktp1 = (TextView) findViewById(R.id.m_ktp);
        nama1 = (TextView) findViewById(R.id.m_nama);
        tanggal1 = (TextView)findViewById(R.id.m_tanggal);
        asal1 = (TextView) findViewById(R.id.m_asal);
        tujuan1 = (TextView)findViewById(R.id.m_tujuan);
        kereta1 = (TextView)findViewById(R.id.m_kereta);
        kelas1 = (TextView)findViewById(R.id.m_kelas);
        berangkat1 = (TextView)findViewById(R.id.m_berangkat);
        datang1 = (TextView)findViewById(R.id.m_datang);
        harga1 = (TextView)findViewById(R.id.m_harga);

        back = (ImageButton)findViewById(R.id.kembali3);
        confirm = (Button)findViewById(R.id.btnConfirm);

        bantuDB = new DatabaseHelper(this);

        if(asal.matches("Semarang") && tujuan.matches("Tegal")
            || asal.matches("Tegal") && tujuan.matches("Semarang") )
        {
            if(kelas.matches("Ekonomi"))
                harga = 50000;
            else if (kelas.matches("Eksekutif"))
                harga = 75000;
        }
        else if(asal.matches("Semarang") && tujuan.matches("Jakarta")
                || asal.matches("Jakarta") && tujuan.matches("Semarang") )
        {
            if(kelas.matches("Ekonomi"))
                harga = 155000;
            else if (kelas.matches("Eksekutif"))
                harga = 225000;
        }
        else if(asal.matches("Tegal") && tujuan.matches("Jakarta")
                || asal.matches("Jakarta") && tujuan.matches("Tegal") )
        {
            if(kelas.matches("Ekonomi"))
                harga = 125000;
            else if (kelas.matches("Eksekutif"))
                harga = 175000;
        }
        noktp1.setText(noktp);
        nama1.setText(nama);
        tanggal1.setText(tanggal);
        asal1.setText(asal);
        tujuan1.setText(tujuan);
        kereta1.setText(kereta);
        kelas1.setText(kelas);
        berangkat1.setText(berangkat);
        datang1.setText(datang);
        harga1.setText("Rp. " + String.valueOf(harga));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(tambah3.this);
                builder.setCancelable(true);
                builder.setTitle("Konfirmasi Pesanan");
                builder.setMessage("Data yang dimasukan sudah benar ?");
                builder.setPositiveButton("Konfirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean isInserted = bantuDB.insertData(noktp,nama,kereta,kelas,asal,tujuan,tanggal,berangkat,datang,String.valueOf(harga));

                                if(isInserted == true)
                                {
                                    Toast.makeText(tambah3.this, "Data Telah Tersimpan", Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    Toast.makeText(tambah3.this, "Data Gagal Tersimpan", Toast.LENGTH_SHORT).show();
                                }
                                Intent kembali = new Intent(getApplicationContext(),MainActivity.class);
                                kembali.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(kembali);
                            }
                        });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }
}

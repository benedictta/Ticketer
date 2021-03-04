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

public class edit2 extends AppCompatActivity {

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

    Button edit1;
    Button delete;

    DatabaseHelper bantuDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit2);

        back = (ImageButton)findViewById(R.id.kembali6);
        delete = (Button)findViewById(R.id.btnDelete);
        edit1 = (Button)findViewById(R.id.btnEdit2);

        bantuDB = new DatabaseHelper(edit2.this);

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
        harga = ambil.getStringExtra("harga");

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

        noktp1.setText(noktp);
        nama1.setText(nama);
        tanggal1.setText(tanggal);
        asal1.setText(asal);
        tujuan1.setText(tujuan);
        kereta1.setText(kereta);
        kelas1.setText(kelas);
        berangkat1.setText(berangkat);
        datang1.setText(datang);
        harga1.setText(harga);

        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(edit2.this, edit3.class);
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
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(edit2.this);
                builder.setCancelable(true);
                builder.setTitle("Konfirmasi Hapus Data");
                builder.setMessage("Hapus Data ?");
                builder.setPositiveButton("Konfirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Integer deleteRows = bantuDB.deleteData(noktp);
                                if(deleteRows>0)
                                    Toast.makeText(edit2.this,"Data Terdelete",Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(edit2.this,"Data Gagal Terdelete",Toast.LENGTH_LONG).show();

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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

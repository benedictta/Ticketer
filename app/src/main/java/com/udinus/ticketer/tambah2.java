package com.udinus.ticketer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class tambah2 extends AppCompatActivity {

    ImageButton back;
    Button lanjut;
    AutoCompleteTextView asal;
    AutoCompleteTextView tujuan;
    AutoCompleteTextView kereta;
    AutoCompleteTextView kelas1;
    AutoCompleteTextView berangkat;
    EditText datang;
    Integer i;
    String noktp;
    String nama;
    String tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah2);

        Intent ambil = getIntent();
        noktp = ambil.getStringExtra("no ktp");
        nama = ambil.getStringExtra("nama");
        tanggal = ambil.getStringExtra("tanggal");

        String[] kota = {"Semarang", "Jakarta", "Tegal"};
        String[] kereta1 = {"Kaligung" , "Joglosemarkerto" , "Kamandaka"};
        String[] kereta2 = {"Argo Muria" , "Argo Cheribon" , "Tegal Bahari"};
        String[] kereta3 = {"Tawang Jaya" , "Argo Sindoro", "Argo Anggrek"};
        final String[] kelas = {"Eksekutif" , "Ekonomi"};

        final String[] jam_brkt = {"07:00","10.00","13:00","16:00"};
        final String[] jam_dtg1 = {"09:00","12:00","15:00","18:00"};
        final String[] jam_dtg2 = {"12:00","14:00","17:00","20:00"};
        final String[] jam_dtg3 = {"14:00","16:00","19:00","22:00"};

        back = (ImageButton)findViewById(R.id.kembali2);
        asal = (AutoCompleteTextView)findViewById(R.id.Asal);
        tujuan = (AutoCompleteTextView)findViewById(R.id.Tujuan);
        lanjut = (Button)findViewById(R.id.btnLanjut2);
        kereta = (AutoCompleteTextView) findViewById(R.id.Kereta);
        kelas1 = (AutoCompleteTextView)findViewById(R.id.Kelas);
        berangkat = (AutoCompleteTextView)findViewById(R.id.Berangkat);
        datang = (EditText)findViewById(R.id.Kedatangan);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, kota);
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, kereta1);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, kereta2);
        final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, kereta3);
        ArrayAdapter<String> adapterKelas = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, kelas);
        ArrayAdapter<String> adapterWaktu = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, jam_brkt);

        asal.setAdapter(adapter);
        kelas1.setAdapter(adapterKelas);
        berangkat.setAdapter(adapterWaktu);

        asal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kereta.setText("");
                kelas1.setText("");
                berangkat.setText("");
                datang.setText("");
                asal.showDropDown();
            }
        });

        tujuan.setAdapter(adapter);
        tujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kereta.setText("");
                kelas1.setText("");
                berangkat.setText("");
                datang.setText("");
                tujuan.showDropDown();
            }
        });
        kereta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(asal.getText().toString().matches("Semarang") && tujuan.getText().toString().matches("Tegal")
                || asal.getText().toString().matches("Tegal") && tujuan.getText().toString().matches("Semarang"))
                {
                    kereta.setAdapter(adapter1);
                    kereta.showDropDown();
                }
                else if(asal.getText().toString().matches("Jakarta") && tujuan.getText().toString().matches("Tegal")
                        || asal.getText().toString().matches("Tegal") && tujuan.getText().toString().matches("Jakarta"))
                {
                    kereta.setAdapter(adapter2);
                    kereta.showDropDown();
                }
                else if(asal.getText().toString().matches("Jakarta") && tujuan.getText().toString().matches("Semarang")
                        || asal.getText().toString().matches("Semarang") && tujuan.getText().toString().matches("Jakarta"))
                {
                    kereta.setAdapter(adapter3);
                    kereta.showDropDown();
                }
                else
                {

                }
                berangkat.setText("");
                datang.setText("");
                kelas1.setText("");
            }
        });

        kelas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!kereta.getText().toString().matches(""))
                {
                    kelas1.showDropDown();
                }
            }
        });

        berangkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!kereta.getText().toString().matches(""))
                {
                    berangkat.showDropDown();
                }
            }
        });

        berangkat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (i=0;i<jam_brkt.length;i++)
                {
                    if(jam_brkt[i].matches(berangkat.getText().toString()))
                    {
                        if(asal.getText().toString().matches("Semarang") && tujuan.getText().toString().matches("Tegal")
                                || asal.getText().toString().matches("Tegal") && tujuan.getText().toString().matches("Semarang"))
                        {
                            datang.setText(jam_dtg1[i]);
                        }
                        else if(asal.getText().toString().matches("Jakarta") && tujuan.getText().toString().matches("Tegal")
                                || asal.getText().toString().matches("Tegal") && tujuan.getText().toString().matches("Jakarta"))
                        {
                            datang.setText(jam_dtg2[i]);
                        }
                        else if(asal.getText().toString().matches("Jakarta") && tujuan.getText().toString().matches("Semarang")
                                || asal.getText().toString().matches("Semarang") && tujuan.getText().toString().matches("Jakarta"))
                        {
                            datang.setText(jam_dtg3[i]);
                        }
                    }
                }
            }
        });

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (asal.getText().toString().matches("") && tujuan.getText().toString().matches(""))
                {
                    Toast.makeText(tambah2.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                else if(asal.getText().toString().matches(tujuan.getText().toString()))
                {
                    Toast.makeText(tambah2.this, "Asal & Tujuan tidak boleh sama", Toast.LENGTH_SHORT).show();
                }
                else if (asal.getText().toString().matches("") || tujuan.getText().toString().matches("")
                        || kereta.getText().toString().matches("") || kelas1.getText().toString().matches("")
                        || berangkat.getText().toString().matches("") || datang.getText().toString().matches(""))
                {
                    Toast.makeText(tambah2.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent a = new Intent(tambah2.this, tambah3.class);
                    a.putExtra("no ktp",noktp);
                    a.putExtra("nama", nama);
                    a.putExtra("tanggal",tanggal);
                    a.putExtra("asal",asal.getText().toString());
                    a.putExtra("tujuan",tujuan.getText().toString());
                    a.putExtra("kereta",kereta.getText().toString());
                    a.putExtra("kelas",kelas1.getText().toString());
                    a.putExtra("berangkat",berangkat.getText().toString());
                    a.putExtra("datang",datang.getText().toString());
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
}

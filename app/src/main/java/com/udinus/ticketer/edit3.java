package com.udinus.ticketer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class edit3 extends AppCompatActivity {

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

    TextView Nama;
    TextView Kereta;
    TextView Kelas;
    TextView Asal;
    TextView Tujuan;

    EditText Tanggal;

    AutoCompleteTextView Berangkat;
    EditText Datang;

    ImageButton back;
    Button ubah;

    DatabaseHelper bantuDB;

    final Calendar kalender = Calendar.getInstance();

    Integer i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit3);

        final String[] eJam_brkt = {"07:00","10.00","13:00","16:00"};
        final String[] eJam_dtg1 = {"09:00","12:00","15:00","18:00"};
        final String[] eJam_dtg2 = {"12:00","14:00","17:00","20:00"};
        final String[] eJam_dtg3 = {"14:00","16:00","19:00","22:00"};

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

        Nama = (TextView)findViewById(R.id.m_nama2);
        Kereta = (TextView)findViewById(R.id.m_kereta2);
        Kelas = (TextView)findViewById(R.id.m_kelas2);
        Asal = (TextView)findViewById(R.id.m_asal2);
        Tujuan = (TextView)findViewById(R.id.m_tujuan2);
        Tanggal = (EditText)findViewById(R.id.TanggalBrkt2);
        Berangkat = (AutoCompleteTextView)findViewById(R.id.Berangkat2);
        Datang = (EditText) findViewById(R.id.Kedatangan2);

        back = (ImageButton)findViewById(R.id.kembali7);
        ubah = (Button)findViewById(R.id.btnSimpan);

        bantuDB = new DatabaseHelper(this);

        Nama.setText(nama);
        Kereta.setText(kereta);
        Kelas.setText(kelas);
        Asal.setText(asal);
        Tujuan.setText(tujuan);

        Tanggal.setText(tanggal);
        Berangkat.setText(berangkat);
        Datang.setText(datang);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                kalender.set(Calendar.YEAR, year);
                kalender.set(Calendar.MONTH, month);
                kalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }
        };

        Tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog kalender1 = new DatePickerDialog(edit3.this, date, kalender
                        .get(Calendar.YEAR), kalender.get(Calendar.MONTH),
                        kalender.get(Calendar.DAY_OF_MONTH));
                kalender1.getDatePicker().setMinDate(System.currentTimeMillis());
                kalender1.show();
            }
        });

        ArrayAdapter<String> adapterWaktu2 = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, eJam_brkt);

        Berangkat.setAdapter(adapterWaktu2);

        Berangkat.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Berangkat.showDropDown();
            }
        });

        Berangkat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (i=0;i<eJam_brkt.length;i++)
                {
                    if(eJam_brkt[i].matches(Berangkat.getText().toString()))
                    {
                        if(asal.matches("Semarang") && tujuan.matches("Tegal")
                                || asal.matches("Tegal") && tujuan.matches("Semarang"))
                        {
                            Datang.setText(eJam_dtg1[i]);
                        }
                        else if(asal.matches("Jakarta") && tujuan.matches("Tegal")
                                || asal.matches("Tegal") && tujuan.matches("Jakarta"))
                        {
                            Datang.setText(eJam_dtg2[i]);
                        }
                        else if(asal.matches("Jakarta") && tujuan.matches("Semarang")
                                || asal.matches("Semarang") && tujuan.matches("Jakarta"))
                        {
                            Datang.setText(eJam_dtg3[i]);
                        }
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(edit3.this);
                builder.setCancelable(true);
                builder.setTitle("Konfirmasi Perubahan");
                builder.setMessage("Data yang dimasukan sudah benar ?");
                builder.setPositiveButton("Konfirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean isUpdate = bantuDB.updateData(noktp,nama,kereta,kelas,asal,tujuan,Tanggal.getText().toString(),Berangkat.getText().toString(),Datang.getText().toString(),harga);

                                if(isUpdate == true)
                                {
                                    Toast.makeText(edit3.this, "Data Telah Diperbaharui", Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    Toast.makeText(edit3.this, "Data Gagal Diperbaharui", Toast.LENGTH_SHORT).show();
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
    private void updateLabel() {
        String myFormat = "dd - MM - yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        Tanggal.setText(sdf.format(kalender.getTime()));
    }
}

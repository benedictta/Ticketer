package com.udinus.ticketer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class tambah extends AppCompatActivity {

    ImageButton back;
    Button lanjut;
    final Calendar kalender = Calendar.getInstance();
    EditText tanggal;
    EditText nama;
    EditText noktp;

    DatabaseHelper bantuDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        tanggal = (EditText) findViewById(R.id.TanggalBrkt);
        lanjut =  (Button)findViewById(R.id.btnLanjut);
        back = (ImageButton) findViewById(R.id.kembali1);
        nama = (EditText) findViewById(R.id.Nama);
        noktp = (EditText) findViewById(R.id.noKTP);
        bantuDB = new DatabaseHelper(this);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                kalender.set(Calendar.YEAR, year);
                kalender.set(Calendar.MONTH, month);
                kalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }
        };
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog kalender1 = new DatePickerDialog(tambah.this, date, kalender
                        .get(Calendar.YEAR), kalender.get(Calendar.MONTH),
                        kalender.get(Calendar.DAY_OF_MONTH));
                kalender1.getDatePicker().setMinDate(System.currentTimeMillis());
                kalender1.show();
            }
        });
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noktp.getText().toString().matches("") || nama.getText().toString().matches("")
                    || tanggal.getText().toString().matches(""))
                {
                    Toast.makeText(tambah.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                else if(cekNoKTP())
                {
                    Toast.makeText(tambah.this, "No KTP Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent a = new Intent(tambah.this,tambah2.class);
                    a.putExtra("no ktp", noktp.getText().toString());
                    a.putExtra("nama", nama.getText().toString());
                    a.putExtra("tanggal", tanggal.getText().toString());
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
    private void updateLabel() {
        String myFormat = "dd - MM - yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tanggal.setText(sdf.format(kalender.getTime()));
    }

    public boolean cekNoKTP() {
        boolean Sama = false;
        Cursor cek =bantuDB.getAllData();
        while (cek.moveToNext()) {

            if (cek.getString(0).matches(noktp.getText().toString())) {

                Sama = true;
            }
        }
        return Sama;
    }
}

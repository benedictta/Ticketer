package com.udinus.ticketer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton tblView;
    ImageButton tblPesan;
    ImageButton tblEdit;
    ImageButton tblHelp;
    ImageButton tblDeleteAll;

    DatabaseHelper bantuDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tblPesan = (ImageButton)findViewById(R.id.tblPesan);
        tblHelp = (ImageButton)findViewById(R.id.tblHelp);
        tblView = (ImageButton)findViewById(R.id.tblView);
        tblEdit = (ImageButton)findViewById(R.id.tblEdit);
        tblDeleteAll = (ImageButton)findViewById(R.id.hapus_semua);

        bantuDB = new DatabaseHelper(this);

        tblPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,tambah.class);
                startActivity(a);
            }
        });
        tblHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("HELP.", "1. Pesan untuk memesan tiket\n\n2. View untuk view Data\n\n3. Edit untuk edit dan hapus data\n\n\n----------------------------------------------------\ncopyright(c). Benedictta Dinda P");
            }
        });

        tblView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, com.udinus.ticketer.View.class);
                startActivity(a);
            }
        });

        tblEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,edit.class);
                startActivity(a);
            }
        });

        tblDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Konfirmasi Hapus Data");
                builder.setMessage("Hapus Semua Data ?");
                builder.setPositiveButton("Konfirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Integer HapusSemua = bantuDB.deleteALL();
                                if(HapusSemua>0)
                                    Toast.makeText(MainActivity.this,"Semua Data Dihapus",Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(MainActivity.this,"Hapus Gagal",Toast.LENGTH_LONG).show();

                            }
                        });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                androidx.appcompat.app.AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

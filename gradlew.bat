package com.udinus.datamahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import static com.udinus.datamahasiswa.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    EditText xnim;
    EditText xnama;
    EditText xtgl;
    Button tblAdd;
    Button tblView;
    Button tblUpdate;
    Button tblDelete;
    RadioGroup gender;
    String jenis_kelamin;
    DatabaseHelper BantuDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BantuDb=new DatabaseHelper(this);
        xnim=(EditText)findViewById(R.id.xnim);
        xnama=(EditText)findViewById(R.id.xnama);
        xtgl = (EditText)findViewById(R.id.xtgl);
        tblAdd=(Button)findViewById(R.id.tblAdd);
        tblView=(Button)findViewById(R.id.tblView);
        tblUpdate=(Button)findViewById(R.id.tblUpdate);
        tblDelete=(Button)findViewById(R.id.tblDelete);
        viewAll();
        DeleteData();
        UpdateData();
        tblAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected = gender.getCheckedRadioButtonId();
                RadioButton SelectedButton = (RadioButton)findViewById(selected);
                jenis_kelamin = SelectedButton.getText().toString();
                boolean isInserted =  BantuDb.insertData(xnim.getText().toString(),xnama.getText().toString(),xtgl.getText().toString(),jenis_kelamin);
                if(isInserted == true)
                    Toast.makeText(MainActivity.this,"Data Tersimpan",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Gagal Tersimpan",Toast.LENGTH_LONG).show();
            }
        });
   
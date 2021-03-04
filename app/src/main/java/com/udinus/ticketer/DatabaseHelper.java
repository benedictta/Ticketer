package com.udinus.ticketer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="benedictta.db";
    public static final String TABLE_NAME="data_tiket";
    public static final String COL_1="no_ktp";
    public static final String COL_2="nama";
    public static final String COL_3="kereta";
    public static final String COL_4="kelas";
    public static final String COL_5="asal";
    public static final String COL_6="tujuan";
    public static final String COL_7="tanggal";
    public static final String COL_8="berangkat";
    public static final String COL_9="datang";
    public static final String COL_10="harga";
    public static final int DATABASE_VERTION=1;

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERTION);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table data_tiket(no_ktp text null,nama text null,kereta text null,kelas text null, asal text null, tujuan text null, tanggal text null, berangkat text null, datang text null, harga text null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String no_ktp,String nama, String kereta, String kelas, String asal, String tujuan, String tanggal, String berangkat, String datang, String harga)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,no_ktp);
        contentValues.put(COL_2,nama);
        contentValues.put(COL_3,kereta);
        contentValues.put(COL_4,kelas);
        contentValues.put(COL_5,asal);
        contentValues.put(COL_6,tujuan);
        contentValues.put(COL_7,tanggal);
        contentValues.put(COL_8,berangkat);
        contentValues.put(COL_9,datang);
        contentValues.put(COL_10,harga);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1) {
            return false;
        }else
        {
            return true;
        }

    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from data_tiket",null);
        return  res;
    }
    public Cursor getData(String no_ktp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql="select * from data_tiket WHERE no_ktp = '"+no_ktp+"'";
        Cursor res = db.rawQuery(sql,null);
        return  res;
    }
    public boolean updateData(String no_ktp,String nama, String kereta, String kelas, String asal, String tujuan, String tanggal, String berangkat, String datang, String harga)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,no_ktp);
        contentValues.put(COL_2,nama);
        contentValues.put(COL_3,kereta);
        contentValues.put(COL_4,kelas);
        contentValues.put(COL_5,asal);
        contentValues.put(COL_6,tujuan);
        contentValues.put(COL_7,tanggal);
        contentValues.put(COL_8,berangkat);
        contentValues.put(COL_9,datang);
        contentValues.put(COL_10,harga);

        db.update(TABLE_NAME,contentValues,"no_ktp=?",new String[]{no_ktp});

        return  true;
    }

    public int deleteData(String no_ktp)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return  db.delete(TABLE_NAME,"no_ktp=?",new String[]{no_ktp});
    }

    public int deleteALL()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        return 1;
    }



}

package com.amamipro.moneymanager.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.BulanColumns.BULAN;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.HutangColumns.JUMLAHU;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.HutangColumns.NAMA;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.BulanColumns.TAHUN;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.BulanColumns.SALDO;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.TABLE_BULAN;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.TABLE_HUTANG;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
public static String CREATE_TABLE_BULAN = "create table " + TABLE_BULAN +
        " (" + _ID + " integer primary key autoincrement, " +
        BULAN + " text not null, " +
        TAHUN + " text not null, " +
        SALDO + " number not null);";

    public static String CREATE_TABLE_HUTANG = "create table " + TABLE_HUTANG +
            " (" + _ID + " integer primary key autoincrement, " +
            NAMA + " text not null, " +
            JUMLAHU + " text not null);";

    private static String DATABASE_NAME = "dbbulan";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BULAN);
        db.execSQL(CREATE_TABLE_HUTANG);

    }

    /*
    Method onUpgrade akan di panggil ketika terjadi perbedaan versi
    Gunakan method onUpgrade untuk melakukan proses migrasi data
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        Drop table tidak dianjurkan ketika proses migrasi terjadi dikarenakan data user akan hilang,
         */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BULAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HUTANG);
        onCreate(db);
    }


}

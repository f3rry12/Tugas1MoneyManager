package com.amamipro.moneymanager.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.amamipro.moneymanager.BulanModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.BulanColumns.BULAN;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.BulanColumns.TAHUN;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.BulanColumns.SALDO;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.TABLE_BULAN;

public class BulanHelper {
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public BulanHelper(Context context) {
        this.context = context;
    }

    public BulanHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }


    public ArrayList<BulanModel> getAllData() {
        Cursor cursor = database.query(TABLE_BULAN, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        ArrayList<BulanModel> arrayList = new ArrayList<>();
        BulanModel bulanModel;
        if (cursor.getCount() > 0) {
            do {
                bulanModel = new BulanModel();
                bulanModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                bulanModel.setBulan(cursor.getString(cursor.getColumnIndexOrThrow(BULAN)));
                bulanModel.setTahun(cursor.getString(cursor.getColumnIndexOrThrow(TAHUN)));
                bulanModel.setSaldo(cursor.getString(cursor.getColumnIndexOrThrow(SALDO)));
                arrayList.add(bulanModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(BulanModel bulanModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(BULAN, bulanModel.getBulan());
        initialValues.put(TAHUN, bulanModel.getTahun());
        initialValues.put(SALDO, bulanModel.getSaldo());
        return database.insert(TABLE_BULAN, null, initialValues);
    }


    public int update(BulanModel bulanModel) {
        ContentValues args = new ContentValues();
        args.put(BULAN, bulanModel.getBulan());
        args.put(TAHUN, bulanModel.getTahun());
        args.put(SALDO, bulanModel.getSaldo());
        return database.update(TABLE_BULAN, args, _ID + "= '" + bulanModel.getId() + "'", null);
    }


    public int delete(int id) {
        return database.delete(TABLE_BULAN, _ID + " = '" + id + "'", null);
    }
}

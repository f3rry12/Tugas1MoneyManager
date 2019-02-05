package com.amamipro.moneymanager.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.amamipro.moneymanager.HutangModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.HutangColumns.JUMLAHU;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.HutangColumns.NAMA;
import static com.amamipro.moneymanager.dbHelper.DatabaseContract.TABLE_HUTANG;

public class HutangHelper {
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public HutangHelper(Context context) {
        this.context = context;
    }

    public HutangHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }


    public ArrayList<HutangModel> getAllData() {
        Cursor cursor = database.query(TABLE_HUTANG, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        ArrayList<HutangModel> arrayList = new ArrayList<>();
        HutangModel hutangModel;
        if (cursor.getCount() > 0) {
            do {
                hutangModel = new HutangModel();
                hutangModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                hutangModel.setNama(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                hutangModel.setJumlahu(cursor.getString(cursor.getColumnIndexOrThrow(JUMLAHU)));
                arrayList.add(hutangModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(HutangModel hutangModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NAMA, hutangModel.getNama());
        initialValues.put(JUMLAHU, hutangModel.getJumlahu());
        return database.insert(TABLE_HUTANG, null, initialValues);
    }


    public int update(HutangModel hutangModel) {
        ContentValues args = new ContentValues();
        args.put(NAMA, hutangModel.getNama());
        args.put(JUMLAHU, hutangModel.getJumlahu());
        return database.update(TABLE_HUTANG, args, _ID + "= '" + hutangModel.getId() + "'", null);
    }


    public int delete(int id) {
        return database.delete(TABLE_HUTANG, _ID + " = '" + id + "'", null);
    }
}

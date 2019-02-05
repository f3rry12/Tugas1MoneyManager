package com.amamipro.moneymanager.dbHelper;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_BULAN = "table_bulan";
    static String TABLE_HUTANG = "table_hutang";

    static final class BulanColumns implements BaseColumns {

        // BulanModel nama
        static String BULAN = "bulan";
        static String TAHUN = "tahun";
        static String SALDO = "saldo";


    }

    static final class HutangColumns implements BaseColumns {


        // HutangModel
        static String NAMA = "nama";
        static String JUMLAHU = "jumlahu";
    }
}
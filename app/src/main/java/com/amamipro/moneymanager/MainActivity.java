package com.amamipro.moneymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.amamipro.moneymanager.dbHelper.BulanHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToKeuangan (View view){
        Intent intent = new Intent (MainActivity.this, BulananActivity.class);
        startActivity(intent);
    }

    public void goToHutang (View view){
        Intent intent = new Intent (MainActivity.this, HutangActivity.class);
        startActivity(intent);
    }

}

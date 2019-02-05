package com.amamipro.moneymanager;

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

public class BulananActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private EditText saldo, tahun, bulan;
    private Button tambah;
    private BulanAdapter bulanAdapter;
    private BulanHelper bulanHelper;
    private ArrayList<BulanModel> bulanModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_keuangan);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        bulan = (EditText) findViewById(R.id.edit_bulan);
        tahun = (EditText) findViewById(R.id.edit_tahun);
        saldo = (EditText) findViewById(R.id.edit_saldo);
        tambah = (Button) findViewById(R.id.btn_tambah);


        bulanHelper = new BulanHelper(this);
        bulanAdapter = new BulanAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        getAllData();

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tambah.getText().equals("update")) {


                } else {
                    insertData();
                    getAllData();
                }
            }
        });


    }





    private void insertData() {
        bulanHelper.open();
        BulanModel mahasiswa = new BulanModel(bulan.getText().toString(), tahun.getText().toString(), saldo.getText().toString());
        bulanHelper.insert(mahasiswa);
        bulanHelper.close();
    }

    private void getAllData() {
        bulanHelper.open();
        // Ambil semua data mahasiswa di database
        bulanModels = bulanHelper.getAllData();
        bulanHelper.close();
        bulanAdapter.addItem(bulanModels);
        recyclerView.setAdapter(bulanAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.search_menu, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
////        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(this);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        newText = newText.toLowerCase();
//        ArrayList<BulanModel> newList = new ArrayList<>();
//        for (BulanModel bulanModel : bulanModels ){
//            String bulan = bulanModel.getBulan().toLowerCase();
//            String tahun = bulanModel.getTahun().toLowerCase();
//            String saldo = bulanModel.getSaldo().toLowerCase();
//            if (bulan.contains(newText)){
//                newList.add(bulanModel);
//            }else if(tahun.contains(newText)){
//                newList.add(bulanModel);
//            }else if(saldo.contains(newText)){
//                newList.add(bulanModel);
//            }
//        }
//
//        bulanAdapter.setFilter(newList);
//        return true;
//    }
}

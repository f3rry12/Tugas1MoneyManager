package com.amamipro.moneymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.amamipro.moneymanager.dbHelper.HutangHelper;

import java.util.ArrayList;

public class HutangActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView2;
    private EditText nama, jumlahu;
    private Button tambahu;
    private HutangAdapter hutangAdapter;
    private HutangHelper hutangHelper;
    private ArrayList<HutangModel> hutangModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_hutang);
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerview2);
        nama = (EditText) findViewById(R.id.edit_nama);
        jumlahu = (EditText) findViewById(R.id.edit_jumlahu);
        tambahu = (Button) findViewById(R.id.btn_tambahu);


        hutangHelper = new HutangHelper(this);
        hutangAdapter = new HutangAdapter(this);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


        getAllData();

        tambahu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tambahu.getText().equals("update")) {


                } else {
                    insertData();
                    getAllData();
                }
            }
        });


    }





    private void insertData() {
        hutangHelper.open();
        HutangModel utangutang = new HutangModel(nama.getText().toString(), jumlahu.getText().toString());
        hutangHelper.insert(utangutang);
        hutangHelper.close();
    }

    private void getAllData() {
        hutangHelper.open();
        hutangModels = hutangHelper.getAllData();
        hutangHelper.close();
        hutangAdapter.addItem(hutangModels);
        recyclerView2.setAdapter(hutangAdapter);
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
//        ArrayList<HutangModel> newList = new ArrayList<>();
//        for (HutangModel hutangModel : hutangModels ){
//            String nama = hutangModel.getNama().toLowerCase();
//            String jumlahu = hutangModel.getJumlahu().toLowerCase();
//            if (nama.contains(newText)){
//                newList.add(hutangModel);
//            }else if(jumlahu.contains(newText)){
//                newList.add(hutangModel);
//            }
//        }
//
//        hutangAdapter.setFilter(newList);
//        return true;
//    }
}

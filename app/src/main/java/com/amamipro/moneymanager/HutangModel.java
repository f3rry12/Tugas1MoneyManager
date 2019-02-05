package com.amamipro.moneymanager;

public class HutangModel {
    private int id;
    private String nama;
    private String jumlahu;

    public HutangModel() {

    }

    public HutangModel(String nama, String jumlahu) {
        this.nama = nama;
        this.jumlahu = jumlahu;
    }

    public HutangModel(int id, String nama, String jumlahu) {
        this.id = id;
        this.nama = nama;
        this.jumlahu = jumlahu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJumlahu() {
        return jumlahu;
    }

    public void setJumlahu(String jumlahu) {
        this.jumlahu = jumlahu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
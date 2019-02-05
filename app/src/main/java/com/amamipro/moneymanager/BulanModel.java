package com.amamipro.moneymanager;

public class BulanModel {
    private int id;
    private String bulan;
    private String tahun;
    private String saldo;

    public BulanModel() {

    }

    public BulanModel(String bulan, String tahun, String saldo) {
        this.bulan = bulan;
        this.tahun = tahun;
        this.saldo = saldo;
    }

    public BulanModel(int id, String bulan, String tahun, String saldo) {
        this.id = id;
        this.bulan = bulan;
        this.tahun = tahun;
        this.saldo = saldo;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
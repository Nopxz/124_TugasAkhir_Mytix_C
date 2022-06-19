package com.example.tessolopam.model;

public class PesanModel {

    private String id, namapemesan, notelepon, pembayaran, jumlahtiket, tambah, namafilm, Harga;

    public PesanModel(String namapemesan, String notelepon, String pembayaran, String jumlahtiket, String tambah, String namafilm, String harga) {
        this.namapemesan = namapemesan;
        this.notelepon = notelepon;
        this.pembayaran = pembayaran;
        this.jumlahtiket = jumlahtiket;
        this.tambah = tambah;
        this.namafilm = namafilm;
        Harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return namapemesan;
    }

    public void setNama(String nama) {
        this.namapemesan = nama;
    }

    public String getTelepon() {
        return notelepon;
    }

    public void setNotelepon(String notelepon) {
        this.notelepon = notelepon;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }

    public String getJumlahtiket() {
        return jumlahtiket;
    }

    public void setJumlahtiket(String jumlahtiket) {
        this.jumlahtiket = jumlahtiket;
    }

    public String getTambah() {
        return tambah;
    }

    public void setTambah(String tambah) {
        this.tambah = tambah;
    }

    public String getNamafilm() {
        return namafilm;
    }

    public void setNamafilm(String namafilm) {
        this.namafilm = namafilm;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }
}

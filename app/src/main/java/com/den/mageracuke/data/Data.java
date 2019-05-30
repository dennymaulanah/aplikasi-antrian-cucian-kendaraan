package com.den.mageracuke.data;

public class Data {
    private String id, nama, create_at, no;
    int status;

    public Data() {
    }

    public Data(String id, String nama, String tanggal, String status, String no) {
        this.id = id;
        this.no = no;
        this.nama = nama;
        this.create_at = tanggal;
        this.status = Integer.parseInt(status);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return create_at;
    }

    public void setTanggal(String tanggal) {
        this.create_at = tanggal;
    }
    public int getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = Integer.parseInt(status);

    }
}
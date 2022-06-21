package com.example.travel_with_me;

public class DataModel {
    int id;
    String nama;
    String password;

    public DataModel(int id, String nama, String password){
        this.id = id;
        this.nama = nama;
        this.password = password;
    }

    public int getId() {
        return  id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNama() {
        return  nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getPassword() {
        return  password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

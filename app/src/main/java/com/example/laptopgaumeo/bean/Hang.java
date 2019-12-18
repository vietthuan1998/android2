package com.example.laptopgaumeo.bean;

import java.util.ArrayList;

public class Hang {
    private String tenhang;
    private ArrayList<Object> listlap;

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public ArrayList<Object> getListlap() {
        return listlap;
    }

    public void setListlap(ArrayList<Object> listlap) {
        this.listlap = listlap;
    }

    public Hang(String tenhang, ArrayList<Object> listlap) {

        this.tenhang = tenhang;
        this.listlap = listlap;
    }
}

package com.example.laptopgaumeo.bean;

public class GioHang {
    private int idlap, soluong;
    private String name,image;
    private long gia;

    public int getIdlap() {
        return idlap;
    }

    public void setIdlap(int idlap) {
        this.idlap = idlap;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public GioHang(int idlap, int soluong, String name, String image, long gia) {
        this.idlap = idlap;
        this.soluong = soluong;
        this.name = name;
        this.image = image;
        this.gia = gia;
    }
}

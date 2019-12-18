package com.example.laptopgaumeo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Laptop implements Serializable,Comparable<Laptop> {
    private int ID;
    private String Hang;
    private String Image;
    private String Name;
    private String CPU;
    private String RAM;
    private String HDD;
    private String VGA;
    private String Monitor;
    private long Gia;
    private String Loai;

    public int getID() {
        return ID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getHang() {
        return Hang;
    }

    public void setHang(String hang) {
        Hang = hang;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getHDD() {
        return HDD;
    }

    public void setHDD(String HDD) {
        this.HDD = HDD;
    }

    public String getVGA() {
        return VGA;
    }

    public void setVGA(String VGA) {
        this.VGA = VGA;
    }

    public String getMonitor() {
        return Monitor;
    }

    public void setMonitor(String monitor) {
        Monitor = monitor;
    }

    public long getGia() {
        return Gia;
    }

    public void setGia(long gia) {
        Gia = gia;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public String getCauHinh(){
        return getName()+"\\ "+getCPU()+"\\ "+getRAM()+"\\ "+getHDD();
    }

    public Laptop(int ID, String Hang,String image, String name, String CPU, String RAM, String HDD, String VGA, String monitor, long gia, String loai) {
        this.ID = ID;
        this.Hang = Hang;
        Image = image;
        Name = name;
        this.CPU = CPU;
        this.RAM = RAM;
        this.HDD = HDD;
        this.VGA = VGA;
        Monitor = monitor;
        Gia = gia;
        this.Loai = loai;
    }

    public int compareTo(Laptop laptop){
        if(getGia() == laptop.getGia()) return 0;
        else if(getGia() > laptop.getGia()) return 1;
        else return -1;
    }

}

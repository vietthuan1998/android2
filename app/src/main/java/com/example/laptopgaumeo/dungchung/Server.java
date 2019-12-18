package com.example.laptopgaumeo.dungchung;

public class Server {
//    public static String localhost = "shopping-android.000webhostapp.com";
//    public static String DuongdanLaptop = "http://" + localhost + "/getLoaiSanPham.php";
//    public static String DuongdanUser = "http://" + localhost + "/getUser.php";
//    public static String DuongdanRegister = "http://" + localhost + "/resigter.php";
//    public static String DuongdanCart = "http://" + localhost + "/setDonHang.php";
//    public static String DuongdanDetailCart = "http://" + localhost + "/setDetailCart.php";

    public static String localhost = "192.168.1.9";
    public static String DuongdanLaptop = "http://" + localhost + ":8081/server/getLoaiSanPham.php";
    public static String DuongdanUser = "http://" + localhost + ":8081/server/getUser.php";
    public static String DuongdanRegister = "http://" + localhost + ":8081/server/resigter.php";
    public static String DuongdanCart = "http://" + localhost + ":8081/server/setDonHang.php";
    public static String DuongdanDetailCart = "http://" + localhost + ":8081/server/setDetailCart.php";
}
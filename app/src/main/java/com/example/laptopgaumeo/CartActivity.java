package com.example.laptopgaumeo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.laptopgaumeo.adapter.Item_Cart;
import com.example.laptopgaumeo.adapter.Item_Laptop;
import com.example.laptopgaumeo.bean.GioHang;
import com.example.laptopgaumeo.dungchung.CheckConnection;
import com.example.laptopgaumeo.dungchung.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    ListView GHview;
    static TextView tvthongbao;
    static TextView tvtongtien;
    static Item_Cart item_cart;
    Button btnthanhtoan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvthongbao = (TextView) findViewById(R.id.cart_thongbao);
        tvtongtien = (TextView) findViewById(R.id.cart_tongtien);
        btnthanhtoan = (Button) findViewById(R.id.cart_thanhtoan);
        GHview = (ListView) findViewById(R.id.cart_list);
        item_cart = new Item_Cart(CartActivity.this,MainActivity.listgiohang);
        GHview.setAdapter(item_cart);
        checkGioHang();
        btnthanhtoan.setOnClickListener(this);
    }

    public static void checkGioHang() {
        if (MainActivity.listgiohang.size() > 0) {
            int sum = 0;

            for (int i = 0; i <MainActivity.listgiohang.size(); i++) {
                if (MainActivity.listgiohang.get(i).getSoluong() == 0) {
                   MainActivity.listgiohang.remove(i);
                    item_cart.notifyDataSetChanged();
                } else sum +=MainActivity.listgiohang.get(i).getSoluong() *MainActivity.listgiohang.get(i).getGia();
            }
            if (MainActivity.listgiohang.size() > 0) {
                tvthongbao.setVisibility(View.INVISIBLE);
            }
            tvtongtien.setText(Item_Laptop.getGia(sum));
        } else {
            tvthongbao.setVisibility(View.VISIBLE);
            tvtongtien.setText(Item_Laptop.getGia(0));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_home:
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        if(MainActivity.checkLogin == false){
            AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn chưa đăng nhập! Vui lòng đăng nhập để tiếp tục trải nghiệm");
            builder.setCancelable(false);
            builder.setNegativeButton("Đăng nhập", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
            builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog alertDialog =builder.create();
            alertDialog.show();
        }
        else if(MainActivity.listgiohang.size() == 0) {
            Toast.makeText(this, "Mua hàng đi bạn ơi!!", Toast.LENGTH_SHORT).show();
        }
        else {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest =new StringRequest(Request.Method.POST, Server.DuongdanCart, new Response.Listener<String>() {
                @Override
                public void onResponse(String madonhang) {
                    Log.i("ma đơn hàng : ",madonhang);
                    if(Integer.parseInt(madonhang) > 0){
                        RequestQueue queue = Volley.newRequestQueue(CartActivity.this);
                        StringRequest request = new StringRequest(Request.Method.POST, Server.DuongdanDetailCart, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("1") ){
                                    MainActivity.listgiohang.clear();
                                    Intent intent = new Intent(CartActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    CheckConnection.ShowToast_Short(CartActivity.this,"Lỗi!!!!!!!!!");
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                JSONArray  jsonArray = new JSONArray();
                                for (GioHang gh : MainActivity.listgiohang){
                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("soluong",gh.getSoluong());
                                        jsonObject.put("product_id",gh.getSoluong());
                                        jsonObject.put("cart_id",madonhang);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    jsonArray.put(jsonObject);
                                }
                                HashMap<String,String> hashMap = new HashMap<String, String>();
                                hashMap.put("json", String.valueOf(jsonArray));
                                return hashMap;
                            }
                        };
                        queue.add(request);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String , String> hashMap = new HashMap<String,String>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();

                    hashMap.put("user_id", String.valueOf(MainActivity.user.getId()));
                    hashMap.put("ngaymua", format.format(date));
                    return hashMap;
                }
            };
            requestQueue.add(stringRequest);
            CheckConnection.ShowToast_Short(this,"Cảm ơn đã mua sản phẩm!");
        }
    }
}

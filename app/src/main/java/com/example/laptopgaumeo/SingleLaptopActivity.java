package com.example.laptopgaumeo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.laptopgaumeo.adapter.Item_Cart;
import com.example.laptopgaumeo.adapter.Item_Laptop;
import com.example.laptopgaumeo.bean.GioHang;
import com.example.laptopgaumeo.bean.Laptop;

public class SingleLaptopActivity extends AppCompatActivity {
    TextView tvCPU, tvRAM, tvVGA, tvHDD, tvMonitor, tvName, tvprice;
    ImageView imageViewlap;
    Button btnbuy;
    Item_Cart item_cart;
    private Laptop laptop;
    int id = 0;
    String hang = "", image = "", name = "", CPU = "", RAM = "", HDD = "", VGA = "", monitor = "", loai = "";
    long gia = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_single_lap);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvCPU = (TextView) findViewById(R.id.single_lap_cpu);
        tvRAM = (TextView) findViewById(R.id.single_lap_ram);
        tvVGA = (TextView) findViewById(R.id.single_lap_vga);
        tvHDD = (TextView) findViewById(R.id.single_lap_hdd);
        tvName = (TextView) findViewById(R.id.single_lap_name);
        tvMonitor = (TextView) findViewById(R.id.single_lap_monitor);
        tvprice = (TextView) findViewById(R.id.single_lap_price);
        imageViewlap = (ImageView) findViewById(R.id.single_lap_img);
        btnbuy = (Button) findViewById(R.id.single_lap_buy);
        item_cart = new Item_Cart(SingleLaptopActivity.this,MainActivity.listgiohang);

        Intent intent = getIntent();
        laptop = (Laptop) intent.getSerializableExtra("parcelable");
        getThongTin();

        if (laptop != null) {
            tvName.setText(name);
            tvMonitor.setText(monitor);
            tvCPU.setText(CPU);
            tvRAM.setText(RAM);
            tvVGA.setText(VGA);
            tvHDD.setText(HDD);
            tvprice.setText(Item_Laptop.getGia(gia));
            imageViewlap.setImageResource(getIdAvatar(image));
        }

        btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleLaptopActivity.this, CartActivity.class);

                if(MainActivity.listgiohang.size() > 0) {
                    boolean exist = false;
                    for (int i = 0; i <MainActivity.listgiohang.size(); i++) {
                        if(MainActivity.listgiohang.get(i).getIdlap() == id) {
                            exist = true;
                            int sl =MainActivity.listgiohang.get(i).getSoluong();
                            if (sl == 10) {
                                Toast.makeText(SingleLaptopActivity.this, "Bạn chỉ ddc phép mua 10 sản phẩm của mặt hàng này", Toast.LENGTH_SHORT).show();
                                return;
                            }
                           MainActivity.listgiohang.get(i).setSoluong(sl + 1);
                        }
                    }
                    if (exist == false) {
                       MainActivity.listgiohang.add(new GioHang(id, 1, name, image, gia));
                        item_cart.notifyDataSetChanged();
                    }
                } else {
                   MainActivity.listgiohang.add(new GioHang(id, 1, name, image, gia));
                    item_cart.notifyDataSetChanged();
                }


                startActivity(intent);
            }
        });
    }

    private void getThongTin() {
        id = laptop.getID();
        hang = laptop.getHang();
        image = laptop.getImage();
        RAM = laptop.getRAM();
        monitor = laptop.getMonitor();
        name = laptop.getName();
        CPU = laptop.getCPU();
        HDD = laptop.getHDD();
        VGA = laptop.getVGA();
        gia = laptop.getGia();
        loai = laptop.getLoai();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_cart:
                Intent intent = new Intent(SingleLaptopActivity.this, CartActivity.class);
                startActivity(intent);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public int getIdAvatar(String name) {
        String pkgName = SingleLaptopActivity.this.getPackageName();
        // Trả về 0 nếu không tìm thấy.
        int resID = SingleLaptopActivity.this.getResources().getIdentifier(name.toLowerCase(), "drawable", pkgName);
        Log.i("CustomListView", "Res Name: " + name + "==> Res ID = " + resID);
        return resID;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

}

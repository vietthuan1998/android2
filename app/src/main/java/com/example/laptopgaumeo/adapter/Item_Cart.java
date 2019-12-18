package com.example.laptopgaumeo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laptopgaumeo.CartActivity;
import com.example.laptopgaumeo.MainActivity;
import com.example.laptopgaumeo.R;
import com.example.laptopgaumeo.bean.GioHang;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Item_Cart extends BaseAdapter {
    private Context context;
    private ArrayList<GioHang> gioHangArrayList;
    private LayoutInflater inflater;
    Item_Laptop item_laptop;


    public Item_Cart(Context context, ArrayList<GioHang> gioHangArrayList) {
        this.context = context;
        this.gioHangArrayList = gioHangArrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return gioHangArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_cart, null);
            holder = new ViewHolder();
            holder.anh = convertView.findViewById(R.id.item_cart_img);
            holder.tvTenlap = convertView.findViewById(R.id.item_cart_tenlap);
            holder.tvgia = convertView.findViewById(R.id.item_cart_gia);
            holder.edtSoluong = convertView.findViewById(R.id.item_cart_sl);
            holder.btnplus = convertView.findViewById(R.id.item_cart_plus);
            holder.btnmunis = convertView.findViewById(R.id.item_cart_minus);
            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();

        GioHang gh = gioHangArrayList.get(position);
        holder.anh.setImageResource(getIdMipmap(gh.getImage()));
        holder.tvTenlap.setText(gh.getName());
        holder.tvgia.setText(getGia(gh.getGia()));
        holder.edtSoluong.setText(gh.getSoluong() + "");

        int sl = Integer.parseInt(holder.edtSoluong.getText().toString());
        if (sl >= 10) {
            holder.btnplus.setVisibility(View.INVISIBLE);
            holder.btnmunis.setVisibility(View.VISIBLE);
        }
        if (sl >= 1 && sl < 10) {
            holder.btnplus.setVisibility(View.VISIBLE);
            holder.btnmunis.setVisibility(View.VISIBLE);
        }
        if (sl < 1) {

        }

        holder.btnmunis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = MainActivity.listgiohang.get(position).getSoluong();
                sl--;
                MainActivity.listgiohang.get(position).setSoluong(sl);
                holder.edtSoluong.setText(sl + "");
                CartActivity.checkGioHang();
                if (sl < 10) {
                    holder.btnplus.setVisibility(View.VISIBLE);
                    holder.btnmunis.setVisibility(View.VISIBLE);
                }

            }
        });
        holder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = MainActivity.listgiohang.get(position).getSoluong();
                sl++;
                MainActivity.listgiohang.get(position).setSoluong(sl);
                holder.edtSoluong.setText(sl + "");
                CartActivity.checkGioHang();
                if (sl > 9) {
                    holder.btnplus.setVisibility(View.INVISIBLE);
                    holder.btnmunis.setVisibility(View.VISIBLE);
                } else {
                    holder.btnplus.setVisibility(View.VISIBLE);
                    holder.btnmunis.setVisibility(View.VISIBLE);
                }
            }
        });

        return convertView;
    }


    public int getIdMipmap(String name) {
        String pkgName = context.getPackageName();
        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(name.toLowerCase(), "drawable", pkgName);
        Log.i("CustomListView", "Res Name: " + name + "==> Res ID = " + resID);
        return resID;
    }

    public class ViewHolder {
        ImageView anh;
        TextView tvTenlap, tvgia;
        EditText edtSoluong;
        Button btnmunis, btnplus;
    }

    public static String getGia(long x) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String st = numberFormat.format(x);
        return st;
    }

}

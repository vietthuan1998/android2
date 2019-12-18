package com.example.laptopgaumeo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopgaumeo.MainActivity;
import com.example.laptopgaumeo.R;
import com.example.laptopgaumeo.bean.Laptop;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class Item_Laptop extends RecyclerView.Adapter<Item_Laptop.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    public ArrayList<Laptop> listlap;
    private OnItemClickListener listener;
    public static Laptop laptop;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }



    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public static String getGia(long x){
        Locale locale = new Locale("vi","VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String st=numberFormat.format(x);
        return st;
    }

    public Item_Laptop(Context context, ArrayList<Laptop> listlap) {
        this.context = context;
        this.listlap = listlap;
        this.layoutInflater = layoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_single_laptop,parent,false);
        ViewHolder viewHolder =new ViewHolder(view,listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Laptop laptop = listlap.get(position);
        int id =this.getIdAvatar(laptop.getImage());
        holder.imageView.setImageResource(id);
        holder.cauhinhView.setText(laptop.getCauHinh());
        holder.giaview.setText(this.getGia(laptop.getGia()));

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return listlap.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView imageView;
        TextView cauhinhView,giaview;

        public ViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView =(ImageView)itemView.findViewById(R.id.item_single_laptop_image);
            cauhinhView = (TextView) itemView.findViewById(R.id.item_single_laptop_cauhinh);
            giaview = (TextView)itemView.findViewById(R.id.item_single_laptop_gia);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }

    public int getIdAvatar(String name){
        String pkgName = context.getPackageName();
        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(name.toLowerCase() , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ name+"==> Res ID = "+ resID);
        return resID;
    }



}

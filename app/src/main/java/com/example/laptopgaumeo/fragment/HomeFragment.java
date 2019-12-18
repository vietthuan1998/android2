package com.example.laptopgaumeo.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.laptopgaumeo.R;
import com.example.laptopgaumeo.SearchActivity;
import com.example.laptopgaumeo.SingleLaptopActivity;
import com.example.laptopgaumeo.adapter.Item_Laptop;
import com.example.laptopgaumeo.bean.Laptop;
import com.example.laptopgaumeo.dungchung.CheckConnection;
import com.example.laptopgaumeo.dungchung.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ViewFlipper viewFlipper;
    int[] slide={R.drawable.slide1,R.drawable.slide2,R.drawable.slide3,R.drawable.slide4,R.drawable.slide5,R.drawable.slide6};
    Animation in,out;
    RecyclerView recyclerViewmsi,recyclerViewdell, recyclerViewhp, recyclerViewlenovo, recyclerViewasus;
    Button searchView;
    static ArrayList<Laptop> listLaptops,listDell, listHP, listLenovo,listMSI,listAsus;

    Item_Laptop item_dell,item_msi,item_hp,item_lenovo,item_asus;


    public void GetData(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanLaptop, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int id = jsonObject.getInt("ID");
                            String hang = jsonObject.getString("Hang");
                            String image = jsonObject.getString("Image");
                            String name = jsonObject.getString("Name");
                            String CPU = jsonObject.getString("CPU");
                            String RAM = jsonObject.getString("RAM");
                            String HDD = jsonObject.getString("HDD");
                            String VGA = jsonObject.getString("VGA");
                            String monitor = jsonObject.getString("Monitor");
                            long gia = jsonObject.getLong("Gia");
                            String loai = jsonObject.getString("Loai");
                            Log.i("test","====> ID" + id );
                            Laptop lp = new Laptop(id,hang,image,name, CPU, RAM, HDD, VGA, monitor,gia,loai);
                            listLaptops.add(lp);
                            if(hang.toUpperCase().equals("DELL")) listDell.add(lp);
                            else if(hang.toUpperCase().equals("MSI")) listMSI.add(lp);
                            else if(hang.toUpperCase().equals("HP")) listHP.add(lp);
                            else if(hang.toUpperCase().equals("LENOVO")) listLenovo.add(lp);
                            else listAsus.add(lp);

                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Lỗiiiiiiiiiiiii", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                    item_dell.notifyDataSetChanged();
                    item_asus.notifyDataSetChanged();
                    item_hp.notifyDataSetChanged();
                    item_msi.notifyDataSetChanged();
                    item_lenovo.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewFlipper = (ViewFlipper) view.findViewById(R.id.home_slide);
        searchView = (Button) view.findViewById(R.id.home_search);
        recyclerViewmsi = (RecyclerView)view.findViewById(R.id.item_hang_msi);
        recyclerViewasus = (RecyclerView)view.findViewById(R.id.item_hang_acer);
        recyclerViewdell = (RecyclerView)view.findViewById(R.id.item_hang_dell);
        recyclerViewhp = (RecyclerView)view.findViewById(R.id.item_hang_hp);
        recyclerViewlenovo = (RecyclerView)view.findViewById(R.id.item_hang_lenovo);

        listLaptops = new ArrayList<Laptop>();
        listAsus= new ArrayList<Laptop>();
        listDell= new ArrayList<Laptop>();
        listHP= new ArrayList<Laptop>();
        listLenovo= new ArrayList<Laptop>();
        listMSI= new ArrayList<Laptop>();

        item_dell =new Item_Laptop(getContext(), listDell);
        item_asus =new Item_Laptop(getContext(),listAsus);
        item_msi =new Item_Laptop(getContext(),listMSI);
        item_lenovo =new Item_Laptop(getContext(),listLenovo);
        item_hp =new Item_Laptop(getContext(),listHP);

        LoadRV(recyclerViewdell, item_dell);
        LoadRV(recyclerViewasus,item_asus);
        LoadRV(recyclerViewmsi,item_msi);
        LoadRV(recyclerViewlenovo,item_lenovo);
        LoadRV(recyclerViewhp,item_hp);

        ////////////////hàm
        setviewFlipper();
        GetData();


        item_dell.setOnItemClickListener(new Item_Laptop.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), SingleLaptopActivity.class);
                Laptop laptop = item_dell.listlap.get(position);
                intent.putExtra("parcelable",laptop);
                startActivity(intent);
            }
        });
        item_lenovo.setOnItemClickListener(new Item_Laptop.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), SingleLaptopActivity.class);
                Laptop laptop = item_lenovo.listlap.get(position);
                intent.putExtra("parcelable",laptop);
                startActivity(intent);
            }
        });
        item_hp.setOnItemClickListener(new Item_Laptop.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), SingleLaptopActivity.class);
                Laptop laptop = item_hp.listlap.get(position);
                intent.putExtra("parcelable",laptop);
                startActivity(intent);
            }
        });
        item_msi.setOnItemClickListener(new Item_Laptop.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), SingleLaptopActivity.class);
                Laptop laptop = item_msi.listlap.get(position);
                intent.putExtra("parcelable",laptop);
                startActivity(intent);
            }
        });
        item_asus.setOnItemClickListener(new Item_Laptop.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), SingleLaptopActivity.class);
                Laptop laptop = item_asus.listlap.get(position);
                intent.putExtra("parcelable",laptop);
                startActivity(intent);
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void setviewFlipper() {
        for(int i =0 ;i<slide.length;i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(slide[i]);
            viewFlipper.addView(imageView);
        }
        in = AnimationUtils.loadAnimation(getActivity(),R.anim.anim_in);
        out = AnimationUtils.loadAnimation(getActivity(),R.anim.anim_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
    }

    private void LoadRV(RecyclerView recyclerView, Item_Laptop item_laptop){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(item_laptop);
    }



}


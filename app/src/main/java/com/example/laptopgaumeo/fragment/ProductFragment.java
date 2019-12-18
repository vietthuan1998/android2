package com.example.laptopgaumeo.fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.laptopgaumeo.MainActivity;
import com.example.laptopgaumeo.R;
import com.example.laptopgaumeo.SingleLaptopActivity;
import com.example.laptopgaumeo.adapter.Item_Laptop;
import com.example.laptopgaumeo.bean.Laptop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductFragment extends Fragment{
    TextView product_name;
    RecyclerView recyclerView;
    HomeFragment homeFragment;
    String hangName;
    String hang, cpu, ram;
    List<String> HANG,CPU,RAM, sort;
    Spinner spinnerhang,spinnerCPU,spinnerRAM, spinnersort;
    ArrayList<Laptop> getListLap,getLap;
    Item_Laptop item_laptop;
    ActionBar actionBar;


    public ArrayList<Laptop> getlap(String name){
        ArrayList<Laptop> ds = new ArrayList<Laptop>();
        ArrayList<Laptop> listlap = homeFragment.listLaptops;
        name = name.toLowerCase();
        for(Laptop i : listlap){
            if (i.getCauHinh().toLowerCase().contains(name) ||
                    i.getHang().toLowerCase().contains(name) ||
                    i.getVGA().toLowerCase().contains(name) ||
                    i.getLoai().toLowerCase().contains(name))
                ds.add(i);
        }
        return ds;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product,container,false);
        Bundle bundle = getArguments();


        product_name = (TextView)view.findViewById(R.id.product_hang);
        recyclerView = (RecyclerView)view.findViewById(R.id.product_listduct);
        spinnerCPU = view.findViewById(R.id.spinner_cpu);
        spinnerhang = view.findViewById(R.id.spinner_hang);
        spinnerRAM = view.findViewById(R.id.spinner_ram);
        spinnersort = view.findViewById(R.id.spinner_sort);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(bundle!=null){
            hangName = bundle.getString(MainActivity.hang);
            product_name.setText(hangName);
        }


        getListLap = getlap(hangName);
        getLap = getListLap;

        loadRV(recyclerView, getListLap);

        HANG = new ArrayList<String>();CPU = new ArrayList<String>();RAM = new ArrayList<String>();
        sort = new ArrayList<String>();
        HANG.add("ACER");HANG.add("MSI");HANG.add("LENOVO");HANG.add("HP");HANG.add("DELL");HANG.add("Hãng");
        CPU.add("i3");CPU.add("i5");CPU.add("i7");CPU.add("CPU");
        RAM.add("2GB");RAM.add("4GB");RAM.add("8GB");RAM.add("16GB");RAM.add("32GB");RAM.add("RAM");
        sort.add("Giá tăng dần");sort.add("Giá giảm dần");sort.add("Sắp xếp");



        //================SINNER HÃNG LAPTOP==============================
        ArrayAdapter<String> adapterhang =new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, HANG){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)view.findViewById(android.R.id.text1)).setText("");
                    ((TextView)view.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }
                return view;
            }

            @Override
            public int getCount() {
                return super.getCount()-1;
            }
        };
        adapterhang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerhang.setAdapter(adapterhang);
        spinnerhang.setSelection(adapterhang.getCount());
        spinnerhang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == spinnerhang.getCount()) hang = "";
                else hang = spinnerhang.getSelectedItem().toString();
                getLap = loadlap(getListLap,hang,cpu,ram);
                if(getLap != null){
                    loadRV(recyclerView,getLap);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //================SINNER CPU==============================
        ArrayAdapter<String> adapterCPU =new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,CPU){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)view.findViewById(android.R.id.text1)).setText("");
                    ((TextView)view.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }
                return view;
            }

            @Override
            public int getCount() {
                return super.getCount()-1;
            }
        };
        adapterCPU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCPU.setAdapter(adapterCPU);
        spinnerCPU.setSelection(adapterCPU.getCount());
        spinnerCPU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == spinnerCPU.getCount()) cpu = "";
                else cpu = spinnerCPU.getSelectedItem().toString();
                getLap = loadlap(getListLap,hang,cpu,ram);
                if(getLap != null){
                    loadRV(recyclerView,getLap);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //================SINNER RAM==============================
        ArrayAdapter<String> adapterRAM =new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,RAM){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)view.findViewById(android.R.id.text1)).setText("");
                    ((TextView)view.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }
                return view;
            }

            @Override
            public int getCount() {
                return super.getCount()-1;
            }
        };
        adapterRAM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRAM.setAdapter(adapterRAM);
        spinnerRAM.setSelection(adapterRAM.getCount());
        spinnerRAM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == spinnerRAM.getCount()) ram = "";
                else ram = spinnerRAM.getSelectedItem().toString();
                getLap = loadlap(getListLap,hang,cpu,ram);
                if(getLap != null){
                    loadRV(recyclerView,getLap);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //================SINNER SORT==============================
        ArrayAdapter<String> adaptersort =new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, sort){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)view.findViewById(android.R.id.text1)).setText("");
                    ((TextView)view.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }
                return view;
            }

            @Override
            public int getCount() {
                return super.getCount()-1;
            }
        };
        adaptersort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersort.setAdapter(adaptersort);
        spinnersort.setSelection(adaptersort.getCount());
        spinnersort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0 ){
                    loadRV(recyclerView,SortUp(getLap));
                }
                else if(position == 1 ) {
                    SortDown(getLap);
                    loadRV(recyclerView,getLap);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }


    public void loadRV(RecyclerView recyclerView,ArrayList<Laptop> list){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        item_laptop=new Item_Laptop(getContext(),list);
        recyclerView.setAdapter(item_laptop);
        item_laptop.setOnItemClickListener(new Item_Laptop.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), SingleLaptopActivity.class);
                Laptop laptop =item_laptop.listlap.get(position);
                intent.putExtra("parcelable",laptop);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Laptop> loadlap(ArrayList<Laptop> ds, String hang, String cpu, String ram){
        ArrayList<Laptop> temp = new ArrayList<Laptop>();
        if(hang == null || cpu ==null || ram == null) return null;
        for(Laptop i : ds ){
            if( i.getHang().contains(hang) && i.getCPU().contains(cpu) && i.getRAM().contains(ram) )
                temp.add(i);
        }
        return temp;
    }


    public ArrayList<Laptop> SortUp(ArrayList<Laptop> ds){
        Collections.sort(ds);
        return ds;
    }

    public void SortDown(ArrayList<Laptop> ds){
        for(int i = 0 ;i<ds.size();i++)
            for(int j= i+1;j<ds.size();j++)
                if(ds.get(i).getGia() < ds.get(j).getGia()){
                    Laptop ao = ds.get(i);
                    ds.set(i,ds.get(j));
                    ds.set(j,ao);
                }
    }

}

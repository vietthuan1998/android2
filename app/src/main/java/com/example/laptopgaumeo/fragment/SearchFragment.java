package com.example.laptopgaumeo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.laptopgaumeo.MainActivity;
import com.example.laptopgaumeo.R;
import com.example.laptopgaumeo.adapter.Item_Laptop;
import com.example.laptopgaumeo.bean.Laptop;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    FloatingSearchView searchView;
    ImageButton btnSearch;
    String key;
    Item_Laptop adapter;
    static ArrayList<Laptop> list;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        searchView = (FloatingSearchView) view.findViewById(R.id.search_view);
        btnSearch = (ImageButton) view.findViewById(R.id.search);
        fragmentManager = getFragmentManager();

        list =new ArrayList<Laptop>();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key=searchView.getQuery();
                Bundle bundle = new Bundle();
                Fragment fragment = new ProductFragment();
                bundle.putString(MainActivity.hang,key);
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                    .replace(R.id.container,fragment,null)
                    .commit();
            }
        });

        return view;
    }
}

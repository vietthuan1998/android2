package com.example.laptopgaumeo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.laptopgaumeo.LoginActivity;
import com.example.laptopgaumeo.MainActivity;
import com.example.laptopgaumeo.R;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    
    TextView tvSetting, tvMyCart, tvSupport,tvInfor, tvName;
    Button btnLogOut,btnLogin;
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_nologin,container,false);

        tvSupport =(TextView)view.findViewById(R.id.frm_profile_support);
        tvInfor = (TextView)view.findViewById(R.id.frm_profile_infor);
        tvSetting =(TextView)view.findViewById(R.id.frm_profile_setting);
        tvMyCart = (TextView)view.findViewById(R.id.frm_profile_mycart);
        tvName = (TextView)view.findViewById(R.id.frm_profile_name);
        btnLogOut=(Button)view.findViewById(R.id.frm_profile_btnlogout);
        btnLogin = (Button)view.findViewById(R.id.frm_profile_btnlogin);
        fragmentManager = getActivity().getSupportFragmentManager();
        if(MainActivity.checkLogin == false){
            btnLogin.setVisibility(View.VISIBLE);
            btnLogOut.setVisibility(View.INVISIBLE);
            tvName.setText("Xin chào!");
        }
        else {
            btnLogin.setVisibility(View.INVISIBLE);
            btnLogOut.setVisibility(View.VISIBLE);
            tvName.setText("Xin chào "+MainActivity.user.getFullname());
        }

        
        btnLogin.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frm_profile_btnlogin:
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                return;
            case R.id.frm_profile_btnlogout:
                MainActivity.checkLogin = false;
                MainActivity.user =null;
                MainActivity.listgiohang.clear();
                fragmentManager.beginTransaction()
                        .replace(R.id.frm_container,new HomeFragment(),null)
                                .commit();
                return;
            default: return;
        }
    }
}

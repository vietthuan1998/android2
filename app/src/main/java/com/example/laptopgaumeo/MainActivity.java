package com.example.laptopgaumeo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.laptopgaumeo.bean.GioHang;
import com.example.laptopgaumeo.bean.User;
import com.example.laptopgaumeo.fragment.HomeFragment;
import com.example.laptopgaumeo.fragment.NotifyFragment;
import com.example.laptopgaumeo.fragment.ProductFragment;
import com.example.laptopgaumeo.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActionBar actionBar;
    ActionBarDrawerToggle toggle;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    public static final String hang = "hang";
    public static ArrayList<GioHang> listgiohang;
    public static boolean checkLogin =false;
    public static ArrayList<User> getDSUser;
    public static User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        navigationView = (NavigationView)findViewById(R.id.left_menu);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);



        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        actionBar =getSupportActionBar();

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        actionBar.setTitle("Shop");
        Fragment fragment = new HomeFragment();
        loadFragment(fragment);

        if(listgiohang != null){

        }else {
            listgiohang = new ArrayList<GioHang>();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()){
                case R.id.menu_home:
                    actionBar.setTitle("Home");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.menu_notify:
                    actionBar.setTitle("Giỏ hàng");
                    fragment = new NotifyFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.menu_profile:
                    actionBar.setTitle("Cá nhân");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) return true;
        switch (item.getItemId()) {
            case R.id.menu_cart:
                Intent intent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent = new Intent(this, ProductFragment.class);
        Fragment fragment;
        Bundle bundle = new Bundle();
        switch (menuItem.getItemId()){
            case R.id.nav_asus:
                fragment = new ProductFragment();
                bundle.putString(MainActivity.hang,"ASUS");
                fragment.setArguments(bundle);
                loadFragment(fragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_dell:
                fragment = new ProductFragment();
                bundle.putString(MainActivity.hang,"DELL");
                fragment.setArguments(bundle);
                loadFragment(fragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_msi:
                fragment = new ProductFragment();
                bundle.putString(MainActivity.hang,"MSI");
                fragment.setArguments(bundle);
                loadFragment(fragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_lenovo:
                fragment = new ProductFragment();
                bundle.putString(MainActivity.hang,"LENOVO");
                fragment.setArguments(bundle);
                loadFragment(fragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_hp:
                fragment = new ProductFragment();
                bundle.putString(MainActivity.hang,"HP");
                fragment.setArguments(bundle);
                loadFragment(fragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_wordstation:
                fragment = new ProductFragment();
                bundle.putString(MainActivity.hang,"wordstation");
                fragment.setArguments(bundle);
                loadFragment(fragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_caocap:
                fragment = new ProductFragment();
                bundle.putString(MainActivity.hang,"CC");
                fragment.setArguments(bundle);
                loadFragment(fragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_gaming:
                fragment = new ProductFragment();
                bundle.putString(MainActivity.hang,"Gaming");
                fragment.setArguments(bundle);
                loadFragment(fragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction  = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void click(){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}

package com.example.laptopgaumeo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.laptopgaumeo.bean.User;
import com.example.laptopgaumeo.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    private static FragmentManager fragmentManager;

    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new LoginFragment())
                    .commit();
        }
    }

    public void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, new LoginFragment())
                .commit();
    }

    public void finishActivity(){
        finish();
    }

}

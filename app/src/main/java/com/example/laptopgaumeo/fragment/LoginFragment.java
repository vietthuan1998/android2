package com.example.laptopgaumeo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.laptopgaumeo.MainActivity;
import com.example.laptopgaumeo.R;
import com.example.laptopgaumeo.bean.User;
import com.example.laptopgaumeo.dungchung.CheckConnection;
import com.example.laptopgaumeo.dungchung.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginFragment extends Fragment implements View.OnClickListener {
    EditText emailid, password;
    Button loginButton;
    TextView forgotPassword, signUp;
    LinearLayout loginLayout;
    FragmentManager fragmentManager;
    View view;

    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        initViews();
        getData();
        setListeners();
        return view;
    }


    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        emailid = (EditText) view.findViewById(R.id.login_emailid);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
        forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        signUp = (TextView) view.findViewById(R.id.createAccount);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);
        MainActivity.getDSUser = new ArrayList<User>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                checkValidation();
                break;
            case R.id.createAccount:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, new SignUp_Fragment(), null)
                        .commit();
                break;
        }
    }

    private void checkValidation() {
        String getEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();


        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            CheckConnection.ShowToast_Short(getContext(), "Enter both credentials");

        } else {
            for (User user : MainActivity.getDSUser) {
//                Toast.makeText(getContext(), "User: "+user.getUsername() + "pass: " +user.getPassword(), Toast.LENGTH_SHORT).show();
                if (getEmailId.equals(user.getUsername()) &&
                        getPassword.equals(user.getPassword())) {
                    Toast.makeText(getActivity(), "Do Login.", Toast.LENGTH_SHORT).show();
                    MainActivity.checkLogin = true;
                    MainActivity.user = user;

                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    return;
                }
            }
            CheckConnection.ShowToast_Short(getContext(), "sai user/password");
        }
    }

    private void finish() {
        finish();
    }


    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanUser, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("ID");
                        String username = jsonObject.getString("Username");
                        String password = jsonObject.getString("Password");
                        String email = jsonObject.getString("Email");
                        String phone = jsonObject.getString("Phone");
                        String fullname = jsonObject.getString("Fullname");
                        user = new User(id, username, password, email, phone, fullname);
//                        Toast.makeText(LoginMainActivity.this, "" + id, Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        Toast.makeText(getContext(), "loi!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    MainActivity.getDSUser.add(user);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public int checkUser(String key){
        for(User user : MainActivity.getDSUser){
            if(key == user.getUsername()) return 1;
        }
        return 0;
    }
}

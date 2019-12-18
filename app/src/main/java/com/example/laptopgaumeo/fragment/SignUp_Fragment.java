package com.example.laptopgaumeo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.laptopgaumeo.LoginActivity;
import com.example.laptopgaumeo.MainActivity;
import com.example.laptopgaumeo.R;
import com.example.laptopgaumeo.bean.User;
import com.example.laptopgaumeo.dungchung.CheckConnection;
import com.example.laptopgaumeo.dungchung.Server;

import java.util.HashMap;
import java.util.Map;

public class SignUp_Fragment extends Fragment implements OnClickListener {
	View view;
	EditText fullName, userName, emailId, mobileNumber, location,
			password, confirmPassword;
	TextView login;
	Button signUpButton;

	public SignUp_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.signup_layout, container, false);
		initViews();
		setListeners();
		return view;
	}

	// Initialize all views
	private void initViews() {
		fullName = (EditText) view.findViewById(R.id.fullName);
		userName = (EditText) view.findViewById(R.id.userName);
		emailId = (EditText) view.findViewById(R.id.userEmailId);
		mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
		location = (EditText) view.findViewById(R.id.location);
		password = (EditText) view.findViewById(R.id.password);
		confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
		signUpButton = (Button) view.findViewById(R.id.signUpBtn);
		login = (TextView) view.findViewById(R.id.already_user);
	}

	// Set Listeners
	private void setListeners() {
		signUpButton.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signUpBtn:
			checkValidation();
			return;

		case R.id.already_user:

			// Replace login fragment
			new LoginActivity().replaceLoginFragment();
			return;
			default: return;
		}

	}

	// Check Validation Method
	private void checkValidation() {

		// Get all edittext texts
		String getFullName = fullName.getText().toString();
		String getUserName = userName.getText().toString();
		String getEmailId = emailId.getText().toString();
		String getMobileNumber = mobileNumber.getText().toString();
		String getLocation = location.getText().toString();
		String getPassword = password.getText().toString();
		String getConfirmPassword = confirmPassword.getText().toString();


		// Check if all strings are null or not
		if (getFullName.equals("") || getFullName.length() == 0
				|| getUserName.equals("") || getUserName.length() == 0
				|| getEmailId.equals("") || getEmailId.length() == 0
				|| getMobileNumber.equals("") || getMobileNumber.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0
				|| getConfirmPassword.equals("") || getConfirmPassword.length() == 0)

			CheckConnection.ShowToast_Short(getContext(),"All fields are required.");


		// Check if both password should be equal
		else if (!getConfirmPassword.equals(getPassword))
			CheckConnection.ShowToast_Short(getContext(),"Both password doesn't match.");

		else if(checkUser(getUserName) == 1){
			CheckConnection.ShowToast_Short(getContext(),"Username already exist");
		}

		else{
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            StringRequest stringRequest =new StringRequest(Request.Method.POST, Server.DuongdanRegister, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("id",response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String , String> hashMap = new HashMap<String,String>();
                    hashMap.put("username",getUserName);
                    hashMap.put("password",getPassword);
                    hashMap.put("email",getEmailId);
                    hashMap.put("phone",getMobileNumber);
                    hashMap.put("fullname",getFullName);
                    return hashMap;
                }
            };
            requestQueue.add(stringRequest);
            new LoginActivity().replaceLoginFragment();
            Toast.makeText(getActivity(), "Do SignUp.", Toast.LENGTH_SHORT).show();
        }

	}

	public int checkUser(String username){
		for(User us: MainActivity.getDSUser){
			if(us.getUsername().equals(username)) return 1;
		}
		return 0;
	}
}

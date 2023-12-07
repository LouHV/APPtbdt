package com.example.shop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.Domain.Content;
import com.example.shop.Domain.RetrofitClient;
import com.example.shop.Domain.User;
import com.example.shop.Domain.UserResponse;
import com.example.shop.R;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sigup extends AppCompatActivity {
    private TextView login;
    private EditText fname, email, password, address, phone;
    private ImageView btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);
        anhxa();
        init();
        processEvents();
    }
    private void anhxa() {
        fname = findViewById(R.id.editTextTextName);
        email = findViewById(R.id.emailTxt);
        password = findViewById(R.id.passTxt);
        address = findViewById(R.id.editTextAddress);
        phone = findViewById(R.id.editTextPhone);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(v -> {
            registerUser(createUser());
        });
    }

    public User createUser() {
        User user = new User();
        user.setFullname(fname.getText().toString());
        user.setEmail(email.getText().toString());
        user.setAddress(address.getText().toString());
        user.setYourphone(phone.getText().toString());
        user.setPassword(password.getText().toString());
        return user;
    }

    public void registerUser(User user) {
        String Email = email.getText().toString();
        String name = fname.getText().toString();
        String Address = address.getText().toString();
        String Phone = phone.getText().toString();
        String Pass = password.getText().toString();
        if(validateData(name,Email,Address,Phone,Pass)){
            Call<UserResponse> userCall = RetrofitClient.getInstance().getMyApi().registerUser(user);
            userCall.enqueue(new Callback<UserResponse>() {

                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(Sigup.this, "Đăng kí thành công", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Sigup.this, Login.class));
                    } else {
                        try {
                            Toast.makeText(Sigup.this,  response.errorBody().string(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "lỗi call API", Toast.LENGTH_LONG).show();
                    Log.e("error", t.getMessage());
                }
            });
        }

    }

    private void init() {
        login = findViewById(R.id.login);
    }

    private void processEvents() {
        login.setOnClickListener(v -> {
            startActivity(new Intent(Sigup.this, Login.class));
            finish();
        });
    }
    public boolean validateData(String name,String Email,String Address,String Phone,String Pass){
        if(name==null || name.equals("")){
            fname.setError("Vui lòng không để trống");
            fname.requestFocus();
            return false;
        } else if (Email==null || Email.equals("")) {
            email.setError("Vui lòng không để trống");
            email.requestFocus();
            return false;
        }
        else if (!Email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            email.setError("Nhập đúng định dạng.Vd: abc@gmail.com");
            email.requestFocus();
            return false;
        }
        else if (Address==null || Address.equals("")) {
            address.setError("Vui lòng không để trống");
            address.requestFocus();
            return false;
        } else if (Phone==null || Phone.equals("")) {
            phone.setError("Vui lòng không để trống");
            phone.requestFocus();
            return false;
        }
        else if (Pass==null || Pass.equals("")) {
            password.setError("Vui lòng không để trống");
            password.requestFocus();
            return false;
        }
        return true;
    }
}
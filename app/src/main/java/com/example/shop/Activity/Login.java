package com.example.shop.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.Domain.RetrofitClient;
import com.example.shop.Domain.User;
import com.example.shop.Domain.UserResponse;
import com.example.shop.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private ImageView btnLogin;
    private EditText emailTxt, passwordTxt;
    private TextView register;
    //khai bao doi tuong dang ky callback
    private ActivityResultLauncher<Intent> callBackForLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        processEvents();
    }

    //khoi tao cac thanh phan
    private void anhxa() {
        btnLogin = findViewById(R.id.Login);
        emailTxt = findViewById(R.id.emailTxtLogin);
        passwordTxt = findViewById(R.id.passwordTxt);
        register = findViewById(R.id.register);
        btnLogin.setOnClickListener(v -> {
            SigninUser(loginUser());
        });
    }
    //xu ly su kien

    public User loginUser() {
        User user = new User();
        user.setEmail(emailTxt.getText().toString());
        user.setPassword(passwordTxt.getText().toString());
        return user;
    }

    public void SigninUser(User user) {
        String Email = emailTxt.getText().toString();
        String Pass = passwordTxt.getText().toString();
        if (validateData(Email, Pass)) {
            Call<UserResponse> userCall = RetrofitClient.getInstance().getMyApi().SigninUser(user);
            userCall.enqueue(new Callback<UserResponse>() {

                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 1500);
                    } else {
                            Toast.makeText(Login.this,"Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_LONG).show();
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

    public void processEvents() {
        register.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, Sigup.class));
        });
    }

    //kiem tra du lieu tren giao dien
    public boolean validateData(String email, String password) {
        if (email == null || email.equals("")) {
            emailTxt.setError("Vui lòng không để trống");
            emailTxt.requestFocus();
            return false;
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            emailTxt.setError("Nhập đúng định dạng.Vd: abc@gmail.com");
            emailTxt.requestFocus();
            return false;
        } else if (password == null || password.equals("")) {
            passwordTxt.setError("Vui lòng không để trống");
            passwordTxt.requestFocus();
            return false;
        }
        return true;
    }
}
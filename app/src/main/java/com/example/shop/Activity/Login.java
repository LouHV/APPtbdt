package com.example.shop.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.R;

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
        init();
        processEvents();
    }
    //khoi tao cac thanh phan
    public void init(){
        try {
            btnLogin = findViewById(R.id.Login);
            emailTxt = findViewById(R.id.emailTxt);
            passwordTxt = findViewById(R.id.passwordTxt);
            register = findViewById(R.id.register);
        }catch (Exception ex){
            Log.e("Init", ex.getMessage());
        }
    }
    //xu ly su kien
    public void processEvents(){
        try {
            //dang ky su kien khi nao Main Activity tra ve ket qua
            callBackForLogin = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                //code here
                if(result!=null && result.getResultCode()==RESULT_OK) {
                    //lay ve gia tri tai result
                    String value = result.getData().getStringExtra("");
                    if(value!=null){
                        //set len edittext
                        emailTxt.setText(value);
                        passwordTxt.setText("");
                    }
                }
            });
            //dang ky su kien button login click
            btnLogin.setOnClickListener(v -> {
                try {
                    String email, password;
                    email = emailTxt.getText().toString();
                    password = passwordTxt.getText().toString();
                    Log.e("Email", email);
                    Log.e("Password", password);
                    if (validateData(email, password)) {
                        //dang nhap thanh cong
                        if (email.equals("1") && password.equals("1")) {
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            callBackForLogin.launch(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception ex){
                    Log.e("btnLogin Clicked: ", ex.getMessage());
                }

            });
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Co loi xay ra vui long thu lai sau.", Toast.LENGTH_SHORT).show();
            Log.e("Button login clicked: ", ex.getMessage());
        }
        register.setOnClickListener(v -> {
            startActivity(new Intent(Login.this,Sigup.class));
        });
    }
    //kiem tra du lieu tren giao dien
    public boolean validateData(String username, String password){
        if(username==null || username.equals("")){
            emailTxt.setError("Mời nhập email");
            emailTxt.requestFocus();
            return false;
        }else if(password==null || password.equals("")){
            passwordTxt.setError("Mời nhập password");
            passwordTxt.requestFocus();
            return false;
        }
        return true;
    }

}
package com.example.shop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.shop.R;

public class Intro extends AppCompatActivity {

    private Button btnLogin,btnSignup;
//    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        SharedPreferences preferences = getSharedPreferences("jwt", MODE_PRIVATE);
        if(preferences.getBoolean("isLogin",true)){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            event();
        }

    }

    private void event() {
        Button btnLogin = findViewById(R.id.Login);
        Button btnSignup = findViewById(R.id.Signup);
        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(Intro.this, Login.class));

        });
        btnSignup.setOnClickListener(v -> {
            startActivity(new Intent(Intro.this,Sigup.class));
        });

    }
//    private void CheckLogin(){
//        if (!sessionManager.Check()){
//            Toast.makeText(this, "Vui long dang nhap", Toast.LENGTH_SHORT).show();
//        }else{
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
//        }
//    }
}
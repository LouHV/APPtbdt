package com.example.shop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.shop.R;

public class Intro extends AppCompatActivity {

    private Button btnLogin,btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(Intro.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, 2000);

        event();

    }

    private void event() {
        Button btnLogin = findViewById(R.id.Login);
        Button btnSignup = findViewById(R.id.Signup);
        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(Intro.this, Login.class));
            finish();
        });
        btnSignup.setOnClickListener(v -> {
            startActivity(new Intent(Intro.this,Sigup.class));
            finish();
        });
    }
}
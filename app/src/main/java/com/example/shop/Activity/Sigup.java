package com.example.shop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shop.R;

public class Sigup extends AppCompatActivity {
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);
        init();
        processEvents();
    }

    private void init(){
        login = findViewById(R.id.login);
    }
    private void processEvents() {
        login.setOnClickListener(v -> {
            startActivity(new Intent(Sigup.this,Login.class));
            finish();
        });
    }

}
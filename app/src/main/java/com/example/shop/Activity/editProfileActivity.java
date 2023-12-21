package com.example.shop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.Domain.RetrofitClient;
import com.example.shop.Domain.UserResponse;
import com.example.shop.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editProfileActivity extends AppCompatActivity {
    private TextView nameTxt,telTxt,addressTxt,gmailTxt, name;
    private Button logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        anhxa();
        getUser();
        logOut.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("jwt", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isLogin",false).commit();
            finish();
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);

        });
    }
    private void getUser() {
        SharedPreferences preferences = getSharedPreferences("jwt", MODE_PRIVATE);
        String Token = preferences.getString("accessToken","N/A");
        String email = preferences.getString("email","N/A");
        Call<UserResponse> call = RetrofitClient.getInstance().getMyApi().getUserIdByEmail("Bearer " + Token,email);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.isSuccessful()) {
                    UserResponse users = response.body();
                    nameTxt.setText(users.getFullname());
                    gmailTxt.setText(users.getEmail());
                    addressTxt.setText(users.getAddress());
                    telTxt.setText(users.getYourphone());
                    name.setText(users.getFullname());
                } else {
                    Toast.makeText(getApplicationContext(), "loi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                Log.e("error", t.getMessage());
            }
        });
    }

    private void anhxa() {
        nameTxt = findViewById(R.id.nameTxt);
        telTxt = findViewById(R.id.telTxt);
        addressTxt = findViewById(R.id.addressTxt);
        gmailTxt = findViewById(R.id.gmailTxt);
        name = findViewById(R.id.namee);
        logOut = findViewById(R.id.logout);
    }

}
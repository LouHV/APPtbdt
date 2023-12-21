package com.example.shop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.Adapter.CartListAdapter;
import com.example.shop.Domain.Cart;
import com.example.shop.Domain.Content;
import com.example.shop.Domain.DetailCart;
import com.example.shop.Domain.RetrofitClient;
import com.example.shop.Helper.ChangeNumberItemsListener;
import com.example.shop.Helper.ManagmentCart;
import com.example.shop.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private CartListAdapter cartListAdapter;
    private RecyclerView recyclerView;
    private ManagmentCart managmentCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    private ImageView backBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cartView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // Khóa xoay màn hình theo chiều dọc (portrait)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        setVariavle();
        initList();
//        caculateCart(cart);
    }

    private void initList() {


        SharedPreferences preferences = getSharedPreferences("jwt", MODE_PRIVATE);
        String Token = preferences.getString("accessToken","N/A");
        String id = preferences.getString("userID","N/A");

        Call<Cart> userCall = RetrofitClient.getInstance().getMyApi().getListCart("Bearer " + Token, Long.valueOf(id));
        userCall.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                    Cart cart = response.body();

                if (cart == null) {
                    SharedPreferences preferences = getSharedPreferences("jwt", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("countcart", "0").commit();
                    emptyTxt.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);

                } else {
                    List<DetailCart> detailCarts = cart.getDetailCart();
                    SharedPreferences preferences = getSharedPreferences("jwt", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("countcart", String.valueOf(cart.getDetailCart().size())).commit();;
                    editor.apply();
                    if (detailCarts == null || detailCarts.isEmpty()) {
                        emptyTxt.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.GONE);
                    } else {
                        cartListAdapter = new CartListAdapter((ArrayList<DetailCart>) detailCarts);
                        recyclerView.setAdapter(cartListAdapter);
                        emptyTxt.setVisibility(View.GONE);
                        scrollView.setVisibility(View.VISIBLE);
                        double percentTax = 0.02; //you can change this value for tax price
                        double delivery = 10;
                        tax = Math.round((cart.getTotal() * percentTax * 100.0)) / 100.0;
                        double total = (cart.getTotal() + tax + delivery);
                        double itemTotal = Math.round(cart.getTotal() * 100) / 100;

                        totalFeeTxt.setText("$" + itemTotal);
                        taxTxt.setText("$" + tax);
                        deliveryTxt.setText("$" + delivery);
                        totalTxt.setText("$" + total);
                    }
                }

            }
            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "lỗi call API", Toast.LENGTH_LONG).show();
                Log.e("error", t.getMessage());
            }

        });

    }

//    public void caculateCart() {
//        double percentTax = 0.02; //you can change this value for tax price
//        double delivery = 10;
//        tax = Math.round((cart.getTotal() * percentTax * 100.0)) / 100.0;
//        double total = (cart.getTotal() + tax + delivery);
//        double itemTotal = Math.round(cart.getTotal() * 100) / 100;
//
//        totalFeeTxt.setText("$" + itemTotal);
//        taxTxt.setText("$" + tax);
//        deliveryTxt.setText("$" + delivery);
//        totalTxt.setText("$" + total);
//    }

    private void setVariavle() {
        backBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    private void initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        recyclerView = findViewById(R.id.cartView);
        scrollView = findViewById(R.id.scrollView2);
        backBtn = findViewById(R.id.backBtn);
        emptyTxt = findViewById(R.id.emptyTxt);

    }
}
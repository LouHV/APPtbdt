package com.example.shop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.shop.Adapter.CartListAdapter;
import com.example.shop.Helper.ChangeNumberItemsListener;
import com.example.shop.Helper.ManagmentCart;
import com.example.shop.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
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
        managmentCart = new ManagmentCart(this);
        // Khóa xoay màn hình theo chiều dọc (portrait)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        setVariavle();
        initList();
        caculateCart();
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //Khi có sự kiện thay đổi số lượng từ Adapter thông qua
        // ChangeNumberItemsListener, gọi phương thức cacualteCart()
        // để tính toán lại giỏ hàng (đây có thể là phương thức để cập nhật tổng
        // số tiền trong giỏ hàng hoặc thực hiện các công việc khác liên quan).
        adapter = new CartListAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                caculateCart();
            }
        });
        recyclerView.setAdapter(adapter);
        if (managmentCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    public void caculateCart() {
        double percentTax = 0.02; //you can change this value for tax price
        double delivery = 10;
        tax = Math.round((managmentCart.getTotalFee() * percentTax * 100.0)) / 100.0;
        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100;

        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);
    }

    private void setVariavle() {
        backBtn.setOnClickListener(v -> {
            finish();
        });
    }

    private void initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        recyclerView = findViewById(R.id.view3);
        scrollView = findViewById(R.id.scrollView2);
        backBtn = findViewById(R.id.backBtn);
        emptyTxt = findViewById(R.id.emptyTxt);
    }
}
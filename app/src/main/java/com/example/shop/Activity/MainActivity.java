package com.example.shop.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Adapter.PopularListAdapter;
import com.example.shop.Adapter.ProductAdapter;
import com.example.shop.Domain.Api;
import com.example.shop.Domain.Content;
import com.example.shop.Domain.PopularDomain;
import com.example.shop.Domain.Product;
import com.example.shop.Domain.RetrofitClient;
import com.example.shop.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerView, recyclerViewPopular, rcvListproduct;
    int page = 1;

    private EditText searchTxt;
    private ProductAdapter productAdapter;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    Boolean isLoading = false;

    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        search();
        initRecyclerview();
        bottom_navigation();

        if (isConnected(this)) {
            Toast.makeText(getApplicationContext(), "Đã kết nối với internet", Toast.LENGTH_LONG).show();
            getProduct();

        } else {

            Toast.makeText(getApplicationContext(), "Không có internet", Toast.LENGTH_LONG).show();
        }

        // Khóa xoay màn hình theo chiều dọc (portrait)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }
    private void Anhxa() {
        api = RetrofitClient.getInstance().getMyApi();
        //Product
        rcvListproduct = findViewById(R.id.view2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        rcvListproduct.setLayoutManager(gridLayoutManager);
        //popular
        recyclerViewPopular = findViewById(R.id.view1);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    private void getProduct() {
        Call<Product> call = RetrofitClient.getInstance().getMyApi().getAllProducts();
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                List<Content> content = response.body().getContent();
                    productAdapter = new ProductAdapter((ArrayList<Content>) content);
                    rcvListproduct.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
//                    productAdapter.notifyItemRangeInserted(350,50);
//                addEventLoad(content);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                Log.e("error", t.getMessage());
            }
        });

    }


    private void search() {
        searchTxt = findViewById(R.id.searchTxt);
        searchTxt.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
        });
    }

    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout profileBtn = findViewById(R.id.ProfileBtn);

        homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        });
        cartBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        });
        profileBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Pofile.class));
        });
    }


    private void initRecyclerview() {

        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("Luu hoang viet", "ertwertwefgwergsdfgs", "pic1", 15, 20, 43));
        items.add(new PopularDomain("Luu hoang viet2", "qertwergdsfbsdf", "pic2", 10, 10, 52));
        items.add(new PopularDomain("Luu hoang viet3", "rewtwerfsdvasdfasdf3q34tadgfa", "pic3", 12, 30, 45));
        items.add(new PopularDomain("Luu hoang viet4", "rewtwerfsdvasdfasdf3q34tadgfa", "pic3", 12, 30, 45));

        adapterPopular = new PopularListAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);
    }

    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            if (network == null) return false;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            return networkCapabilities != null && (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        } else {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE);
        }
    }
}
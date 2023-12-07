package com.example.shop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class SearchActivity extends AppCompatActivity {
    private ImageView btnBack;
    private EditText searchText;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        back();
    }
    private void initView(){
        api = RetrofitClient.getInstance().getMyApi();
        searchText = findViewById((R.id.searchtext));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView=findViewById(R.id.rcvSearch);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        productAdapter=new ProductAdapter(item,this);
//        recyclerView.setAdapter(productAdapter);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getProduct();
            }
        });
    }

    private void getProduct() {
        String str_search = searchText.getText().toString();
//        api.getProductById(Long.valueOf(str_search));
        Call<Product> call = api.getProductById(Long.valueOf(str_search));
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                List<Content> content = response.body().getContent();
                productAdapter = new ProductAdapter((ArrayList<Content>) content);
                recyclerView.setAdapter(productAdapter);
//                productAdapter.notifyDataSetChanged();
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

    private void back(){
        btnBack = findViewById(R.id.backBtn3);
        btnBack.setOnClickListener(v -> {
            finish();
        });


    }
}
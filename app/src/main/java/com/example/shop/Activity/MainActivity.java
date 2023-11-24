package com.example.shop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shop.Adapter.PopularListAdapter;
import com.example.shop.Adapter.ProductAdapter;
import com.example.shop.Domain.PopularDomain;
import com.example.shop.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerViewPopular, recyclerView;
    private ProductAdapter productAdapter;
    private TextView seeAll;
    private EditText searchTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search();
        initRecyclerview();
        bottom_navigation();

        // Khóa xoay màn hình theo chiều dọc (portrait)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    private void search() {
        searchTxt = findViewById(R.id.searchTxt);
        searchTxt.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,SearchActivity.class));
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
            startActivity(new Intent(MainActivity.this,CartActivity.class));
        });
        profileBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,Pofile.class));
        });
    }


    private void initRecyclerview() {

        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("Luu hoang viet","ertwertwefgwergsdfgs","pic1",15,20,43));
        items.add(new PopularDomain("Luu hoang viet2","qertwergdsfbsdf","pic2",10,10,52));
        items.add(new PopularDomain("Luu hoang viet3","rewtwerfsdvasdfasdf3q34tadgfa","pic3",12,30,45));
        items.add(new PopularDomain("Luu hoang viet4","rewtwerfsdvasdfasdf3q34tadgfa","pic3",12,30,45));
        recyclerViewPopular=findViewById(R.id.view1);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapterPopular=new PopularListAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);

        try {
            ArrayList<PopularDomain> item = new ArrayList<>();
            item.add(new PopularDomain("Luu hoang viet","ertwertwefgwergsdfgs","pic1",15,20,43));
            item.add(new PopularDomain("Luu hoang viet2","qertwergdsfbsdf","pic2",10,10,52));
            item.add(new PopularDomain("Luu hoang viet3wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww","rewtwerfsdvasdfasdf3q34tadgfaqwer","pic3",12,30,453333));
            item.add(new PopularDomain("Luu hoang viet4","rewtwerfsdvasdfasdf3q34tadgfa","pic3",12,30,45));
            item.add(new PopularDomain("Luu hoang viet4","rewtwerfsdvasdfasdf3q34tadgfa","pic3",12,30,45));
            item.add(new PopularDomain("Luu hoang viet4","rewtwerfsdvasdfasdf3q34tadgfa","pic3",12,30,45));
            item.add(new PopularDomain("Luu hoang viet4","rewtwerfsdvasdfasdf3q34tadgfa","pic3",12,30,45));
            item.add(new PopularDomain("Luu hoang viet4","rewtwerfsdvasdfasdf3q34tadgfa","pic3",12,30,45));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
            recyclerView=findViewById(R.id.view2);
            recyclerView.setLayoutManager(gridLayoutManager);

            productAdapter=new ProductAdapter(item,this);
            recyclerView.setAdapter(productAdapter);
        }catch (Exception e){
            String eaaa = e.getMessage();
        }
    }
//    private boolean isConnected (Context context){
//
//    }
}
package com.example.shop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.Adapter.ProductAdapter;
import com.example.shop.Domain.PopularDomain;
import com.example.shop.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private ImageView btnBack;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        back();
    }
    private void back(){
        btnBack = findViewById(R.id.backBtn3);
        btnBack.setOnClickListener(v -> {
            finish();
        });

//        ArrayList<PopularDomain> item = new ArrayList<>();
//        item.add(new PopularDomain("Luu hoang viet","ertwertwefgwergsdfgs","pic1",15,20,43));
//        item.add(new PopularDomain("Luu hoang viet2","qertwergdsfbsdf","pic2",10,10,52));
//        item.add(new PopularDomain("Luu hoang viet3wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww","rewtwerfsdvasdfasdf3q34tadgfaqwer","pic3",12,30,453333));
//        item.add(new PopularDomain("Luu hoang viet4","rewtwerfsdvasdfasdf3q34tadgfa","pic3",12,30,45));
//        item.add(new PopularDomain("Luu hoang viet4","rewtwerfsdvasdfasdf3q34tadgfa","pic3",12,30,45));
//        item.add(new PopularDomain("Luu hoang viet4","rewtwerfsdvasdfasdf3q34tadgfa","pic3",12,30,45));
//        item.add(new PopularDomain("Luu hoang viet4","rewtwerfsdvasdfasdf3q34tadgfa","pic3",12,30,45));
//        item.add(new PopularDomain("Luu hoang viet4","rewtwerfsdvasdfasdf3q34tadgfa","pic3",12,30,45));
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
//        recyclerView=findViewById(R.id.rcvSearch);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        productAdapter=new ProductAdapter(item,this);
//        recyclerView.setAdapter(productAdapter);
    }
}
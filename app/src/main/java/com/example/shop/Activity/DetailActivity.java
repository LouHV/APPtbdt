package com.example.shop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shop.Domain.Cart;
import com.example.shop.Domain.Content;
import com.example.shop.Domain.DetailCart;
import com.example.shop.Domain.PopularDomain;
import com.example.shop.Domain.RetrofitClient;
import com.example.shop.Helper.ManagmentCart;
import com.example.shop.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private Button addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, reviewTxt, scoreTxt, count;
    private ImageView picItem , backBtn;
    private int numberOder = 1;
    private PopularDomain object;
    Spinner spinner;
    private Content content;
    private ManagmentCart managmentCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        managmentCart = new ManagmentCart(this);

        initView();
        getBundle();
        initControl();
        CatchEventSpinner();
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> integerArrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(integerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected
            }
        });
    }

    private void initControl() {
        addToCartBtn.setOnClickListener(v -> {
            int selectedQuantity = (int) spinner.getSelectedItem();
            themGioHang(addCart(selectedQuantity));
        });
    }

    public DetailCart addCart(int soluong) {
            DetailCart detailCart = new DetailCart();
            detailCart.setProductId(content.getId());
            detailCart.setPrice(content.getPrice());
            detailCart.setQuantity(soluong);
            return detailCart;

    }
    private void themGioHang(DetailCart detailCart) {
        SharedPreferences preferences = getSharedPreferences("jwt", MODE_PRIVATE);
        String Token = preferences.getString("accessToken","N/A");
        String id = preferences.getString("userID","N/A");
        Call<Cart> userCall = RetrofitClient.getInstance().getMyApi().postCart("Bearer " + Token, Long.valueOf(id),detailCart);
        userCall.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetailActivity.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DetailActivity.this, CartActivity.class));
                    finish();
                } else {
                    try {
                        Toast.makeText(DetailActivity.this,  response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "lỗi call API", Toast.LENGTH_LONG).show();
                Log.e("error", t.getMessage());
            }
        });
//        if(MainActivity.carts.size()>0){
//           int sl = Integer.parseInt(spinner.getSelectedItem().toString());
//           boolean exites = false;
//           for (int i = 0 ;i<MainActivity.carts.size();i++){
//               if(MainActivity.carts.get(i).getProductId() == content.getId()){
//                    MainActivity.carts.get(i).setQuantity(MainActivity.carts.get(i).getQuantity()+sl);
//                    if (MainActivity.carts.get(i).getQuantity() >= 10){
//                        MainActivity.carts.get(i).setQuantity(10);
//                    }
//                    MainActivity.carts.get(i).setQuantity(content.getPrice()* MainActivity.carts.get(i).getQuantity());
//                    exites =true;
//               }
//           }
//           if(exites == false){
//               int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
//               int gia = content.getPrice()*soluong;
//               Cart cart = new Cart();
//               cart.setPrice(gia);
//               cart.setQuantity(soluong);
//               cart.setProductId(content.getId());
//               MainActivity.carts.add(cart);
//           }
//        }
//        else {
//            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
//            int gia = content.getPrice()*soluong;
//            Cart cart = new Cart();
//            cart.setPrice(gia);
//            cart.setQuantity(soluong);
//            cart.setProductId(content.getId());
//            MainActivity.carts.add(cart);
//        }
//        Intent intent = new Intent(getApplicationContext(),CartActivity.class);
//        startActivity(intent);
////        count.setText(String.valueOf(Ultis.cartList.size()));

    }


    private void getBundle() {
//        object = (PopularDomain) getIntent().getSerializableExtra("chitiet");
//        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(), "drawable",this.getPackageName());
//        Glide.with(this)
//                .load(drawableResourceId)
//                .into(picItem);
//
//        titleTxt.setText(object.getTitle());
//        feeTxt.setText("$"+object.getPrice());
//        descriptionTxt.setText(object.getDescription());
//        reviewTxt.setText(object.getReview() + "");
//        scoreTxt.setText(object.getScore() + "");

//        addToCartBtn.setOnClickListener(v -> {
//            object.setNumberinCart(numberOder);
//            managmentCart.insertFood(object);
//        });
        content = (Content) getIntent().getSerializableExtra("chitiet");
        Glide.with(this)
                .load("http://192.168.1.36:8080/api/product/getImage/"+content.getId())
                .into(picItem);
        titleTxt.setText(content.getName());
        feeTxt.setText(content.getPrice()+"đ");
        descriptionTxt.setText(content.getDescription());
        backBtn.setOnClickListener(v -> {
          finish();
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        feeTxt = findViewById(R.id.priceTxt);
        titleTxt = findViewById(R.id.titleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        picItem = findViewById(R.id.itemPic);
        spinner = findViewById(R.id.spinner);
        scoreTxt = findViewById(R.id.scoreTxt);
        backBtn = findViewById(R.id.backBtn);
        count = findViewById(R.id.count);
    }
}
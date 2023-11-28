package com.example.shop.Domain;

import android.database.Observable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "http://192.168.1.20:8080/api/";
    @GET("product")
    Call<Product> getAllProducts();
}

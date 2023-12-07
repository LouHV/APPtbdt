package com.example.shop.Domain;

import android.database.Observable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "http://10.0.0.87:8080/api/";
    @GET("product")
    Call<Product> getAllProducts();
    @POST("auth/signup")
    Call<UserResponse> registerUser(@Body User user);
    @POST("auth/signin")
    Call<UserResponse> SigninUser(@Body User user);

    @GET("product/{id}")
    Call<Product> getProductById(@Path("id") Long id);
}

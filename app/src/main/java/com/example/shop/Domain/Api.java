package com.example.shop.Domain;

import android.database.Observable;

import java.util.List;

import kotlin.ParameterName;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "http://192.168.1.36:8080/api/";
    @GET("product")
    Call<Product> getAllProducts();
    @POST("auth/signup")
    Call<String> registerUser(@Body User user);
    @POST("auth/signin")
    Call<Jwt> SigninUser(@Body User user);

    @GET("product/search")
    Call<Product> getProductByName(@Query("search") String name);
    @GET("userId")
    Call<UserResponse> getUserIdByEmail(@Header("Authorization") String authorization, @Query("email") String email);
    @GET("cart/userId")

    Call<Cart> getCartDetail(@Header("Authorization") String authorization, @Path("productId") Long productId);
    @POST("cart/{userId}")
    Call<Cart> postCart(@Header("Authorization") String authorization, @Path("userId") Long productId,@Body DetailCart cart);
    @GET("cart/{userId}")
    Call<Cart> getListCart(@Header("Authorization") String authorization, @Path("userId") Long productId);
    @GET("getUserId")
    Call<Integer> getId(@Header("Authorization") String authorization, @Query("email") String email);
}

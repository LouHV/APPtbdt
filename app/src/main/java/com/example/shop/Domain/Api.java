package com.example.shop.Domain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    String BASE_URL = "http://10.0.0.177:8080/api/";
    @GET("product")
    Call<PopularDomain> getAllProduct();
}

package com.example.gplxhanga.api;

import com.example.gplxhanga.entities.BienBaoGiaoThong;
import com.example.gplxhanga.entities.CauHoi;
import com.example.gplxhanga.entities.ItemQuestion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().create();
    ApiService apiService = new Retrofit
            .Builder()
            //chạy trên android emulation
            //.baseUrl("http://10.0.2.2:8080/")
            //chạy trên máy thực thì lấy ip của máy
            // cmd -> ipconfig -> chọn cổng IPv4 Address
            .baseUrl("http://192.168.203.66:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("cauhoi")
    Call<List<ItemQuestion>> findALlQuestion();

    @GET("traffic_signs")
    Call<List<BienBaoGiaoThong>> findALlTrafficSigns();
}

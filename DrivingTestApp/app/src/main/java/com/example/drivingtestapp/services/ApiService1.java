package com.example.drivingtestapp.services;

import com.example.drivingtestapp.models.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService1 {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    // http://10.0.84.176/AndroidWebService/views/QuestionView.php

    ApiService1 apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.35/")
            //192.168.1.35
            //172.20.10.2
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService1.class);
    @GET("/AndroidWebService/views/QuestionView1.php")
    public Call<List<Question>> questionCall();

}

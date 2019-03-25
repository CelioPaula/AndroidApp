package com.example.androidproject.controller;

import com.example.androidproject.OMDbService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InjectionDependances {

    public static Retrofit getRetrofitDependances(){
        return new Retrofit.Builder()
                .baseUrl(OMDbService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

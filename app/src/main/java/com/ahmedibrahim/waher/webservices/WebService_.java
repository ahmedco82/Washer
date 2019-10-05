package com.ahmedibrahim.waher.webservices;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cca on 01/01/2019.
 */
//https://mobikul.com/android-retrofit-handling-sslhandshakeexception/
public class WebService_ {

    private static WebService_ instance;
    private API api;


    public WebService_(){
   //     OkHttpClient client = new OkHttpClient.Builder().build();


        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder().client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://192.168.1.2/Pagination/")
        .build();
        api = retrofit.create(API.class);
    }

    public static WebService_ getInstance() {
        if (instance == null) {
            instance = new WebService_();
        }
        return instance;
    }

    public API getApi(){

        return api;
    }
}

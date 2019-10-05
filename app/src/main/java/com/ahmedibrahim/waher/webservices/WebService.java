package com.ahmedibrahim.waher.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  hendiware 2016
 */

public class WebService{
    private static WebService instance;
    private API api;

    public WebService(){
/*
        OkHttpClient httpClient=new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();
        */


//.setLenient()


       // httpClient = new OkHttpClient.Builder();





        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().
        connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60,TimeUnit.SECONDS)
        .writeTimeout(10,TimeUnit.SECONDS).addInterceptor(interceptor).build();



        //client.interceptors().add(interceptor);


        Retrofit retrofit = new Retrofit.Builder().client(client)
                //.addConverterFactory(GsonConverterFactory.create())
               .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Urls.MAIN_URL)
                .client(client)
                .build();
                 api = retrofit.create(API.class);
    }


    public static WebService getInstance(){
        if (instance == null) {
            instance = new WebService();
        }
        return instance;
    }

    public API getApi(){

        return api;
    }
}

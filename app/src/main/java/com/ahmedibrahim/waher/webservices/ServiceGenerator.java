package com.ahmedibrahim.waher.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by sab99r
 */

public class ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass) {

        OkHttpClient httpClient=new OkHttpClient.Builder()
                .connectTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();

       // httpClient.initialize(getApplicationContext());
       // https://192.168.1.3/test

        Retrofit retrofit = new Retrofit.Builder()
             .baseUrl("https://ahmedco.000webhostapp.com/")
             //.baseUrl("http://192.168.1.3/test/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
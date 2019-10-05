package com.ahmedibrahim.waher.webservices;

import com.ahmedibrahim.waher.models.Itemchange;
import com.ahmedibrahim.waher.models.ItemsNotifaction;
import com.ahmedibrahim.waher.models.Customer;
import com.ahmedibrahim.waher.models.LoginResponse;
import com.ahmedibrahim.waher.models.MainResponse;
import com.ahmedibrahim.waher.models.User;
import com.ahmedibrahim.waher.models.Request;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
* hendiware on 2016/12 .
*/

public interface API {
    @POST("regist.php")
    Call<MainResponse>registerUser(@Body User user);
    @POST("login.php")
    Call<LoginResponse>loginUser(@Body User user);
    @POST("send_requst.php")
    Call<MainResponse>sendRequst(@Body Request request);
    /*
    @GET("get_req.php")
    Call<List<Customer>>getCustomer(@Query("index") int index,@Query("user_name") String user_name);
   */
    @GET("get_req.php")
    Call<List<Customer>>getCustomer(@Query("index") int index,@Query("user_name") String user_name);
    @POST("sendNotifaction.php")
    Call<MainResponse>sendNot(@Body ItemsNotifaction itemsNotifaction);

    // ------------------------------//----------------------
/*
    @POST("update.php")
    Call<Itemchange>updateItem(@Query("value") String value);
    */

    @POST("update.php")
    Call<Itemchange>updateItem(@Query("value") String value,@Query("name") String name , @Query("index") int index);

    /*
    @POST("update.php")
    Call<MainResponse>updateItem(@Body Itemchange itemchange);
    */

}


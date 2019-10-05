package com.ahmedibrahim.waher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ahmedibrahim.waher.R;
import com.ahmedibrahim.waher.models.User;
import com.ahmedibrahim.waher.utils.Session;

import io.realm.Realm;


/**
 * Created by cca on 07/12/2018.
 */


public class SplashScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        //User u = new User();
        //u.email= "edu";
        //u.name= "aaa";
        //Session.getInstance().loginUser(u);
        //Session.getInstance().logout();
       // Session.getInstance().logout();
        if(Session.getInstance().isUserLoggedIn()){
           Log.i("Log_0:","Yes");
           Intent intent = new Intent(this, HomeActivity.class);
           startActivity(intent);
           finish();
        }else{
            Log.i("Log_1:","OFF");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
           // finish();
        }
         // setContentView(R.layout.activity_main);
         // Session.getInstance().logout();
         /*
          Intent intent = new Intent(this, MainActivity.class);
          startActivity(intent);
          finish();
        */
    }
}



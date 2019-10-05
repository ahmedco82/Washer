package com.ahmedibrahim.waher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


import com.ahmedibrahim.waher.R;

import butterknife.BindView;

/**
 * Created by cca on 20/11/2018.
 */

public class ForgetpassowrdActivity extends AppCompatActivity {

    @BindView(R.id.et_4)
    EditText etEmail;
    @BindView(R.id.etRepeatPassword)
    EditText enteremail;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
    }


    // Send;
    public void Send(View v){
      Intent k = new Intent(ForgetpassowrdActivity.this, HomeActivity.class);
      startActivity(k);
      // Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }
}
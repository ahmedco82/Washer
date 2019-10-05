package com.ahmedibrahim.waher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmedibrahim.waher.R;
import com.ahmedibrahim.waher.models.MainResponse;
import com.ahmedibrahim.waher.models.User;
import com.ahmedibrahim.waher.webservices.WebService;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
* Created by cca on 20/11/2018.
*/

public class CreateAccountActivity extends AppCompatActivity {

    @BindView(R.id.et_entername)
    EditText etName;
    @BindView(R.id.et_4)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.etRepeatPassword)
    EditText etRepeatPassword;
    @BindView(R.id.b_account)
    Button butCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
    }


    public void regesterUser(View v){

        if (!FUtilsValidation.isEmpty(etName, getString(R.string.enter_username))
        && !FUtilsValidation.isEmpty(etEmail, getString(R.string.enter_email))
        && FUtilsValidation.isValidEmail(etEmail,getString(R.string.enter_valid_email))
        && !FUtilsValidation.isEmpty(etPassword,getString(R.string.enter_password))
        && !FUtilsValidation.isEmpty(etRepeatPassword,getString(R.string.enter_password_again))
        && FUtilsValidation.isPasswordEqual(etPassword,etRepeatPassword,getString(R.string.password_isnt_equal))){

        Regester();
          //Toast.makeText(this, "yes", Toast.LENGTH_LONG).show();
        }else {

          //  Toast.makeText(this, "no", Toast.LENGTH_LONG).show();
        }
    }

    public void Regester(){
        final User user = new User();
        user.user_name = etName.getText().toString();
        user.user_email = etEmail.getText().toString();
        user.user_password = etPassword.getText().toString();
        user.user_token = FirebaseInstanceId.getInstance().getToken();
        // register user with retorfit;
        WebService.getInstance().getApi().registerUser(user).enqueue(new Callback<MainResponse>(){
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response){
                if(response.body().status==2){
                    Toast.makeText(CreateAccountActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                }else if (response.body().status == 1) {
                   //Toast.makeText(CreateAccountActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                    Intent gotToLogin = new Intent(CreateAccountActivity.this, LoginActivity.class);
                    gotToLogin.putExtra("email",user.user_email);
                    gotToLogin.putExtra("pass",user.user_password);
                    startActivity(gotToLogin);
                    finish();
                    // go to login activity
                }else{
                    Toast.makeText(CreateAccountActivity.this,response.body().message, Toast.LENGTH_SHORT).show();
               }
              // setNormalMode();
            }

            @Override
            public void onFailure(Call<MainResponse>call,Throwable t){
               // Log.e("exr", t.toString());
           }
        });
     }
   }


        /*
                    Intent gotToLogin = new Intent(MainActivity.this, Hello.class);
                    gotToLogin.putExtra("email", user.user_email);
                    gotToLogin.putExtra("pass", user.user_password);
                    startActivity(gotToLogin);
                    finish();
                    */
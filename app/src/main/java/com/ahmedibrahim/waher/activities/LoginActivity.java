package com.ahmedibrahim.waher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedibrahim.waher.R;
import com.ahmedibrahim.waher.models.LoginResponse;
import com.ahmedibrahim.waher.utils.Session;
import com.ahmedibrahim.waher.webservices.WebService;


import com.ahmedibrahim.waher.models.User;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/***Created by cca on 20/11/2018.
*/

public class LoginActivity extends AppCompatActivity {
    public int Is_Manegar;
    @BindView(R.id.et_entername)
    EditText etEmail;
    @BindView(R.id.etRepeatPassword)
    EditText etenterPassword;
    @BindView(R.id.tv_forgetpassowd)
    TextView tv_forgetPassword;
    @BindView(R.id.b_account)
    Button butLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         ButterKnife.bind(this);

        if(getIntent()!=null){
            String email = getIntent().getStringExtra("email");
            String pass = getIntent().getStringExtra("pass");
            etEmail.setText(email);
            etenterPassword.setText(pass);
        }
    }


    public void OnClick_tv(View v){
        Intent gotToLogin = new Intent(LoginActivity.this, ForgetpassowrdActivity.class);
        startActivity(gotToLogin);
        finish();
    }

    public void logUser(View v){
        if(!FUtilsValidation.isEmpty(etEmail)
         && FUtilsValidation.isValidEmail(etEmail , getString(R.string.enter_valid_email))
         && !FUtilsValidation.isEmpty(etenterPassword)) {
         log();
         Toast.makeText(LoginActivity.this,"yse", Toast.LENGTH_SHORT).show();
        }else{
          Toast.makeText(LoginActivity.this,"no", Toast.LENGTH_SHORT).show();
        }
        //user.user_email =etEmail.getText().toString();
        //user.user_password=etenterPassword.getText().toString();
        //Log.i("mail_is:",  ""+etEmail.getText().toString());
        //Toast.makeText(LoginActivity.this,"Mail is"+etEmail.getText().toString(), Toast.LENGTH_SHORT).show();
        //log();
    }

    public void log(){
        final User user=new User();
        user.user_email = etEmail.getText().toString();
        user.user_password=etenterPassword.getText().toString();
        user.user_token = FirebaseInstanceId.getInstance().getToken();
        //   user.user_token = FirebaseInstanceId.getInstance().getToken();
        //   user.user_name =
        //  login User using Retrofit .............
        WebService.getInstance().getApi().loginUser(user).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                // check for status value comming from server (response of login-user.php file status)
                if (response.body().status == 2) {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                    Log.i("id_is22:",  ""+response.body().message);
                } else if(response.body().status == 1){
                    //user.username = response.body().user.user_name;
                    user.setId_id(Integer.parseInt(response.body().user.id));
                    user.id = Integer.parseInt(response.body().user.id);
                    user.name = response.body().user.user_name;
                    Is_Manegar= user.id;
                    Log.i("id_is11:",  ""+ response.body().message);
                    Log.i("Is_Manegar isis_0  :",  ""+ Is_Manegar);
                    // Toast.makeText(LoginActivity.this, "ID IS "+user.id, Toast.LENGTH_SHORT).show();
                    // user.isAdmin = response.body().user.is_user_admin.equals("1");
                    // Session.getInstance().loginUser(user);
                    Intent goToMain = new Intent(LoginActivity.this, HomeActivity.class);
                    Session.getInstance().loginUser(user);
                    // goToMain.putExtra("name",Is_Manegar);
                    // if(getIntent()!=null)getIntent().removeExtra("name");
                    // goToMain.putExtra("name",user.getName());
                    // Log.i("login_name_is :",  ""+user.getName());
                    startActivity(goToMain);
                    finish();
                }
                else {
                    Log.i("id_isNo00:",  "not  "+response.body().message);
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call,Throwable t){
                // print error message in logcat
                Log.e("err_0:", t.getLocalizedMessage());
            }
        });
    }
}


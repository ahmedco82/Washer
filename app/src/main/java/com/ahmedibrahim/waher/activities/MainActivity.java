package com.ahmedibrahim.waher.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.ahmedibrahim.waher.R;
import com.ahmedibrahim.waher.models.MainResponse;
import com.ahmedibrahim.waher.models.User;
import com.ahmedibrahim.waher.utils.Session;
import com.ahmedibrahim.waher.webservices.WebService;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public boolean Is_Manegar;
    String NAME;
    int ID;
    String EMAIL;
    String name_;
    String email_;
    int id_;
    //@BindView(R.id.bf)
    //Button butfacebook;
    @BindView(R.id.bm)
    Button butmail;
    @BindView(R.id.tvcreate_newaccount)
    TextView tv_create_newaccount;
    CallbackManager callbackManager;
    private LoginButton loginButton;
    private String facebook_id,f_name, m_name, l_name, gender, profile_image, full_name, email_id;

    Context Mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);
        this.Mcontext = this;

        if(Session.getInstance().isUserLoggedIn()){
            Session.getInstance().logout();
            Log.i("isUserLoggedIn0 = ","logout");
        }
        LoginManager.getInstance().logOut();
        loginButton = (LoginButton)findViewById(R.id.bf);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("public_profile email");

        if(AccessToken.getCurrentAccessToken() != null){
            RequestData();
            Log.i("Visible_1 = ","VISIBLE");
            // goto second activity
            /*
            Intent intent = new Intent(Home.this,SecondActivity.class);
            startActivity(intent);
            */
        }else {
            Log.i("Visible_0 = ","inVISIBLE");
        }

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                facebook_id=f_name= m_name= l_name= gender= profile_image= full_name= email_id="";

                if(AccessToken.getCurrentAccessToken() != null){
                    RequestData();
                    Profile profile = Profile.getCurrentProfile();
                    if (profile != null) {
                        facebook_id=profile.getId();
                        Log.i("facebook_id", facebook_id);
                        f_name=profile.getFirstName();
                        Log.i("f_name", f_name);
                        m_name=profile.getMiddleName();
                        Log.i("m_name", m_name);
                        l_name=profile.getLastName();
                        Log.i("l_name", l_name);
                        full_name=profile.getName();
                        Log.i("full_name", full_name);
                        profile_image=profile.getProfilePictureUri(400, 400).toString();
                        Log.i("profile_image", profile_image);
                    }
                    // goto second activity
                   Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                   startActivity(intent);
                }
            }

            @Override
            public void onCancel() {

            }
            @Override
            public void onError(FacebookException exception) {
            }
        });
    }


    private void getKeyHash() {
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.ahmedibrahim.waher", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    public void RequestData(){
        JSONArray jarr = null;
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                JSONObject json = response.getJSONObject();

                User u =new User();
                System.out.println("Json data :"+json);
                // u.name = json.getString("name").toString();
                // u.id_id = Integer.parseInt(json.getString("id"));
                // u.email = json.getString("email").toString();
                try {
                    Log.i("bdefor = ","0000000");
                     name_ = json.getString("name");
                     email_ = json.getString("email");
                     id_ = json.getInt("id");
                     Reg(name_,email_,id_);
                     u.setName(name_);
                      u.setEmail(email_);
                  //   u.setId_id(id_);
                     Session.getInstance().loginUser(u);
                   //  Log.i("SetIDIDID:", "_0"+id_ +"And "+u.getId_id()+"and "+ Session.getInstance().getUser().getId_id());
                     //Log.i("GetEmail_11:", "_0"+u.getName());
                    if(json != null){
                        Log.i("inside = ","--098ooooo");
                         //String text = "<b>Name :</b> "+json.getString("name")+"<br><br><b>Email :</b> "+json.getString("email")+"<br><br><b>Profile link :</b> "+json.getString("link");
                        //details_txt.setText(Html.fromHtml(text));
                        //profile.setProfileId(json.getString("id"));
                        //NAME=   json.get("name").toString();
                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(intent);
                       // Log.i("goto_activity = ","HomeActivity");
                    }
                    //NAME= json.getString("name").toString();
                    //ID=Integer.parseInt(json.getString("id"));
                    //EMAIL=json.getString("email").toString();
                    //Session.getInstance().loginUser(u);

                }catch(JSONException e){
                    e.printStackTrace();
                    Log.i("JSONException = ","--: "+e.toString());
                }

                 /*
                u.setName(n);
                u.setId_id(idid);
                u.setEmail(e);
                Log.i("sString_0:", "_0"+n);
                */
                //  Log.i("gString_0:", "_0"+u.getName());

                if(Session.getInstance().isUserLoggedIn()) {
                    Log.i("Log_Is:", "Yes");
                //    User user = Session.getInstance().getUser();
                    Log.i("EMAIL IS :", ""+ Session.getInstance().getUser().getName());
                  //  Log.i("Name IS :", ""+ Session.getInstance().getUser().getEmail());
                }else{
                    Log.i("Log_Is:", "nOn---");
                }

            }
        });

       // Log.i("GetName_0:", "_0"+NAME);
       // Log.i("GetIDID_0:", "_0"+ID);

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



/// ----------------------------------------------------------------------------------
    public void button_mail(View v){
        Intent i = new Intent(MainActivity.this , LoginActivity.class);
        startActivity(i);
    }

    public void onClick_tv(View v){
        Intent i = new Intent(MainActivity.this,CreateAccountActivity.class);
        startActivity(i);
    }


    public void Reg(String _name,String _email,int _id){
        //Log.i("Reg_0:", "_0"+_name);
        //Log.i("Reg_1:", "_0"+_email);
        //Log.i("Reg_2:", "_0"+_id);
        final User user = new User();
        user.user_name = _name;
        user.user_email = _email;
        user.user_password = String.valueOf(_id);
        user.user_token = FirebaseInstanceId.getInstance().getToken();
        //  register user with retorfit;
       // Log.i("Reg_user_token:", "_0"+user.user_token);
        WebService.getInstance().getApi().registerUser(user).enqueue(new Callback<MainResponse>(){
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response){
                if(response.body().status==2){
                   //  Toast.makeText(MainActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                    Log.i("response_00:", "_0 "+response.body().message);
                }else if (response.body().status == 1) {
                     Log.i("response_11:", "_0"+response.body().message);
                    ((LoginActivity)Mcontext).Is_Manegar= user.id;
                  //  user.id = Integer.parseInt(response.body().us);
                    //Session.getInstance().loginUser(user);
                    // Toast.makeText(MainActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                    // Intent gotToLogin = new Intent(MainActivity.this, HomeActivity.class);
                    // gotToLogin.putExtra("name",user.user_email);
                    // gotToLogin.putExtra("pass",user.user_password);
                    // startActivity(gotToLogin);
                    // finish();
                    // go to login activity
                  }else if (response.body().status == 3) {
                  //  Log.i("response_13:", "_0"+response.body().message);
                }else{
                    Toast.makeText(MainActivity.this,response.body().message, Toast.LENGTH_SHORT).show();
                }
                // setNormalMode();
            }
            @Override
            public void onFailure(Call<MainResponse>call,Throwable t){
                 Log.e("exr", t.toString());
            }
        });
    }


     /*
    public void Regester(String _name,String _email,int _id){
        final User user = new User();
        user.user_name = _name;
        user.user_email = _email;
        user.id = _id;

        // register user with retorfit;
        WebService.getInstance().getApi().registerUser(user).enqueue(new Callback<MainResponse>(){
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response){
                if(response.body().status==2){
                 //   Toast.makeText(MainActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                }else if (response.body().status == 1) {
                    //Toast.makeText(CreateAccountActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                   // Intent gotToLogin = new Intent(MainActivity.this, HomeActivity.class);
                   // gotToLogin.putExtra("name",user.user_email);
                   // gotToLogin.putExtra("pass",user.user_password);
                   // startActivity(gotToLogin);
                 //   finish();
                    // go to login activity
                }else{
                    Toast.makeText(MainActivity.this,response.body().message, Toast.LENGTH_SHORT).show();
                }
                // setNormalMode();
            }

            @Override
            public void onFailure(Call<MainResponse>call,Throwable t){
                // Log.e("exr", t.toString());
            }
        });
    }
    */

}







// setContentView(R.layout.activity_main);
// setContentView(R.layout.activity_forgetpassword);
// setContentView(R.layout.activity_login);
// setContentView(R.layout.activity_create_account);
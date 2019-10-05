package com.ahmedibrahim.waher.activities;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedibrahim.waher.R;
import com.ahmedibrahim.waher.fragments.Page_1Fragment;
import com.ahmedibrahim.waher.fragments.Page_2Fragment;
import com.ahmedibrahim.waher.fragments.Page_3Fragment;
import com.ahmedibrahim.waher.fragments.Page_4Fragment;
import com.ahmedibrahim.waher.fragments.Page_7Fragment;
import com.ahmedibrahim.waher.models.Request;
import com.ahmedibrahim.waher.adapters.AllUsersAdapter;
import com.ahmedibrahim.waher.fragments.Page_6Fragment;
import com.ahmedibrahim.waher.fragments.PreferenceFragmentCustom;
import com.ahmedibrahim.waher.models.MainResponse;
import com.ahmedibrahim.waher.models.User;
import com.ahmedibrahim.waher.utils.Session;
import com.ahmedibrahim.waher.webservices.WebService;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ahmedibrahim.waher.adapters.AllUsersAdapter.ClearArray;

/**
 * **Created by cca on 20/11/2018.
**/

public class HomeActivity extends AppCompatActivity {
    TextView mToolBarTextView;
    Toolbar toolbar;
    private Context mContext;
    //FragmentManager fm;
    //Fragment selectedFragment = null;
    Fragment selectedFragment;
    private int count;
    private int tootalPrice;
    private int[] totalParts;
    public  int[] tP;
    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    public String s_name;
    User user;
    String LastTitle;
    String currentTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.main);
        mContext = this;
        // FirebaseApp.initializeApp(mContext);
        getLayoutInflater().inflate(R.layout.toolbar,(ViewGroup)findViewById(android.R.id.content));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
        findViewById(R.id.navigation);
        //Cleare
        //AllUsersAdapter activity = AllUsersAdapter.instance;
        //activity.ClearArray(mContext);
        ClearArray(mContext);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectedFragment = null;
                LinearLayout root;
                switch (item.getItemId()){
                    case R.id.action_item1:
                        selectedFragment= null;
                        /*
                        AllUsersAdapter activity = AllUsersAdapter.instance;
                        int s[] = new int[0];
                        activity.saveArray(s,"array", mContext);
                        activity.ClearArray(mContext);
                       */
                        FunctionToolbar("الرئيسية",LastTitle);
                        selectedFragment = Page_1Fragment.newInstance(s_name, "", "","");
                        //selectedFragment = Page_3Fragment.newInstance();
                        // selectedFragment = Page_2Fragment.newInstance();
                        break;
                    case R.id.action_item2:
                        //selectedFragment = Page_2Fragment.newInstance();
                        selectedFragment= null;
                        FunctionToolbar("الطلبات",LastTitle);
                        selectedFragment = Page_6Fragment.newInstance(s_name);
                        break;
                    case R.id.action_item3:
                        selectedFragment= null;
                        //Intent intent = new Intent(HomeActivity.this,PrefsActivity.class);
                        //startActivity(intent);
                        FunctionToolbar("الحساب",LastTitle);
                        selectedFragment = new Page_7Fragment().newInstance(s_name);
                        break;
                }



                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
         });

           // Manually displaying the first fragment - one time only
          //Page_1Fragment.newInstance(nameUser, "", "","");
          FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
          //User user = Session.getInstance().getUser();
          user = Session.getInstance().getUser();
          s_name= "";
        if (user != null) {
            s_name = user.getName();
            Log.i("Session_user_name_is1: ", "" + s_name);
        }else{
            Log.i("Session_user_name_is0: ", "Non");
            s_name = "انتظار..";
        }
        transaction.replace(R.id.frame_layout , Page_1Fragment.newInstance(s_name, "", "",""));
        transaction.commit();
        FunctionToolbar("الرئيسية","الرئيسية");
    }


    public void FunctionToolbar(final String s , final String pastTitle){
        setSupportActionBar(toolbar);
        LastTitle = pastTitle;
        currentTitle= s;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mToolBarTextView.setText(pastTitle);
                onBackPressed();
            }
        });
        // getSupportActionBar().hide();
        mToolBarTextView.setText(currentTitle);
        Configuration config = getResources().getConfiguration();
        mToolBarTextView.setTextSize(18);
        //int width = bd.getBitmap().getWidth();
        mToolBarTextView.setPadding(+toolbar.getContentInsetStartWithNavigation(), 0, 0, 0);
        getSupportActionBar().show();
    }

    public void Button_quit_user(View v){
       LoginManager.getInstance().logOut();
       Session.getInstance().logout();
       if(getIntent()!=null)getIntent().removeExtra("name");
       Toast.makeText(this,"تم تسجيل الخروج", Toast.LENGTH_LONG).show();
       Intent intent = new Intent(HomeActivity.this,SplashScreen.class);
       startActivity(intent);
    }

    public void onButtonClickNext(View v){
        // getting the static instance of activity .........
        AllUsersAdapter activity = AllUsersAdapter.instance;
        if (activity != null) {
            boolean textNotNull = activity.therIs_thereIsNot();
            totalParts = activity.loadArray("array", this);
            count = 0;
            tootalPrice = 0;
            int[] priceItem = new int[]{4, 5, 6, 5, 8, 7, 4};
            for(int i = 0; i < 7; i++) {
                count = count + totalParts[i];
                tootalPrice = tootalPrice + totalParts[i] * priceItem[i];
            }
            if(textNotNull)swapFragment();
            else
            Toast.makeText(this, "لم تقم بالأختيار", Toast.LENGTH_LONG).show();
            //Toast.makeText(this ,"Retrive = "+ activity.loadArray("arr",mContext), Toast.LENGTH_LONG).show();
            // Toast.makeText(this ,"count = "+ count+"Price "+tootalPrice, Toast.LENGTH_LONG).show();
        }
    }


    private void swapFragment() {
        selectedFragment = null;
        AllUsersAdapter activity = AllUsersAdapter.instance;
        int totalParts[] = activity.loadArray("array", this);
        selectedFragment = Page_4Fragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout , selectedFragment);
        transaction.commit();
    }


    private void NewFragment(String j1, String j2, String j3, String j4) {
        String country = j1 + "/" + j2 + "/" + j3;
        selectedFragment = null;
        int rand = (int) (Math.random() * (90000 - 500));
        String NumRequst = "#" + rand;
        selectedFragment = Page_2Fragment.newInstance(String.valueOf(count), String.valueOf(tootalPrice), country, NumRequst, j4);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



    public void NextStep(View v){

        et1 = (EditText) findViewById(R.id.et_1);
        et2 = (EditText) findViewById(R.id.et_2);
        et3 = (EditText) findViewById(R.id.et_3);
        et4 = (EditText) findViewById(R.id.et_4);

        String ett1 = et1.getText().toString();
        String ett2 = et2.getText().toString();
        String ett3 = et3.getText().toString();
        String ett4 = et4.getText().toString();

        if (ett1.trim().equals("") || ett2.trim().equals("") || ett3.trim().equals("") || ett4.trim().equals("")) {
            Toast.makeText(this, "تاكد من ملىء جميع الخانات", Toast.LENGTH_LONG).show();
        } else {
            NewFragment(et1.getText().toString(), et2.getText().toString(), et3.getText().toString(), et4.getText().toString());
            getSupportActionBar().hide();
            //
           LastTitle = currentTitle;
           Log.i("title is_0: ",""+LastTitle);
        }
    }

    public void btn_edit(View v){
        //  Toast.makeText(this ,"Page_4Fragment", Toast.LENGTH_LONG).show();
        selectedFragment = null;
        selectedFragment = new Page_3Fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout , selectedFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        getSupportActionBar().show();
        AllUsersAdapter activity = AllUsersAdapter.instance;

        if (activity != null) {
            tP = activity.loadArray("array", this);
        }

        FunctionToolbar(LastTitle,LastTitle);
    }




    public void btn_SendData(View v){
        AllUsersAdapter activity = AllUsersAdapter.instance;

        int s[] = new int[0];
        activity.saveArray(s,"array", this);
        ClearArray(mContext);

        String s_1= et1.getText().toString();
        String s_2= et2.getText().toString();
        String s_3= et3.getText().toString();
        String s_4= et4.getText().toString();

        if (s_1.matches("") ||s_2.matches("") ||s_3.matches("")||s_4.matches("")) {
            Toast.makeText(this, "تاكد من ادخال البيانات", Toast.LENGTH_SHORT).show();
        }else {
            sendNotficReq(mContext, 2, s_4, s_1 + s_2 + s_3, "0", count, tootalPrice, "-", "-");
            //Toast.makeText(this, "Send Notfic", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(HomeActivity.this,WinActivity.class);
            startActivity(intent);
            String country = s_1 + "/" + s_2 + "/" + s_3;
            Intent gotToLogin = new Intent(HomeActivity.this, WinActivity.class);
            gotToLogin.putExtra("receiving_address",country);
            startActivity(gotToLogin);
        }
    }


    public void printHashKey(Context pContext) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("TAG","printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("TAG", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("TAG", "printHashKey()", e);
        }
    }

    // send Notifaction
    private void sendNotficReq(Context mConttext, final int userid, final String n_user, final String t_user, final String s,final int tp1,final int tp2,final String d,final String h) {
       /*
        final Request req = new Request();
        req.day = d;
        req.hour = h;
        req.user_id = userid;
        req.total_parts = tp1;
        req.total_price = tp2;
        req.name = n_user;
        req.title = t_user;
        req.status = s;
        */
        RequestQueue queue = Volley.newRequestQueue(mConttext);
        // String url = "http://192.168.1.3/Pagination/sendNotifaction.php";
        String url = "https://ahmedco.000webhostapp.com/send_req.php";
        //this is the url where you want to send the request
        //TODO: replace with your own url to send request, as I am using my own localhost for this tutorial
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST,url,new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do it with this it will work
                        //User u =new User();
                        //System.out.println("Json data :"+response.toString());
                        Log.i("ResponseIsJson data::", "yes_" + response.toString());
                        /*
                        try {
                        // JSONObject test_response = new JSONObject(response);

                          ///  json= response.
                            //String res = test_response.getString("success");
                          //   Log.i("ResponseIs::", "yes_" + res.toString());
                            // lock = res.toString();
                            // (currentItem);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        */
                    }
                } , new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Response", "no" + error);
            }
        }){
            // adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idToken", String.valueOf(userid));
                params.put("total_parts", String.valueOf(tp1));
                params.put("total_price", String.valueOf(tp2));
                params.put("name", n_user);
                params.put("title", t_user);
                params.put("status", s);
                params.put("day",d);
                params.put("hour",h);
                //status
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    // send Notifaction
    private void sendNotfic(Context mConttext, final int id, final int currentIndex, final String accept_refuse, final String day, final String hour) {
        RequestQueue queue = Volley.newRequestQueue(mConttext);
        // String url = "http://192.168.1.3/Pagination/sendNotifaction.php";
        JSONObject jObj = null;
        String json = null;
        String url = "https://ahmedco.000webhostapp.com/sendNotifaction.php";
        //this is the url where you want to send the request
        //TODO: replace with your own url to send request, as I am using my own localhost for this tutorial
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do it with this it will work
                        try {
                           JSONObject test_response = new JSONObject(response);
                            //String json = response.replace("\\\"","'");
                           //JSONObject jo = new JSONObject(json.substring(1,json.length()-1));
                           // String res = test_response.getString("success");
                           // Log.i("ResponseIs::", "yes_" +jo.toString());
                           // lock = res.toString();
                            // (currentItem);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } , new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Response", "no" + error);
            }
        }){
            // adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("getToken", String.valueOf(currentIndex));
                params.put("title", accept_refuse);
                params.put("masg", day);
                params.put("hour", hour);
                params.put("id", String.valueOf(id));
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    private void test(){

    }
}












 /*
    public void Regester(int userid, String n_user, String t_user, String s, int tp1, int tp2, String d, String h) {
        final Request req = new Request();
        req.day = d;
        req.hour = h;
        req.user_id = userid;
        req.total_parts = tp1;
        req.total_price = tp2;
        req.name = n_user;
        req.title = t_user;
        req.status = s;
        // register user with retorfit;
        WebService.getInstance().getApi().sendRequst(req).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if(response.body().status==2){
                   Toast.makeText(mContext, response.body().message, Toast.LENGTH_SHORT).show();
                }else if (response.body().status == 1) {
                   Toast.makeText(mContext, response.body().message, Toast.LENGTH_SHORT).show();
                    // go to login activity
                }else{
                    Toast.makeText(mContext,response.body().message, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
             Log.e("erxr",t.toString());
            }
        });
    }
    */



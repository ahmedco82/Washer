package com.ahmedibrahim.waher.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedibrahim.waher.R;
import com.ahmedibrahim.waher.models.User;
import com.ahmedibrahim.waher.utils.Session;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ahmedibrahim.waher.adapters.CustomerAdapter;
import com.ahmedibrahim.waher.models.Customer;
import com.ahmedibrahim.waher.webservices.API;
import com.ahmedibrahim.waher.webservices.ServiceGenerator;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cca on 23/12/2018.
 */

public class Page_6Fragment extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener {

    TextView th, tm, tt, tapm;
    Spinner spin_h, spin_m, spin_apm, spin_day;
    RadioButton radioReject,radioAccipt ;
    RadioGroup radioGroup;
    Button buttonDialogReject,buttonDialogAccipt;
    Dialog dialog;
    RecyclerView recyclerView;
    List<Customer> customers;
    CustomerAdapter adapter;
    View rootView;
    String TAG = "MainActivity - ";
    Context context;
    API api;
    Boolean acceptOrNo = true;
    Context c = null;
    String lock;
    public int[] userLock;
     User user;
    public static String myNameIs;
    public static Page_6Fragment instance;

    public static Page_6Fragment newInstance(String user_name) {
        myNameIs = user_name;
        Page_6Fragment fragment = new Page_6Fragment();

        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_customer, container, false);
        user = Session.getInstance().getUser();

        //myEmailIs= "abc@hotmail.com";
/*
        if(user !=null){
            myEmailIs = user.getEmail();
        }else{
            user = Session.getInstance().getUser();
            myEmailIs = user.getEmail();
        }
*/
     //   Log.i("toast ",""+myEmailIs);
       // myEmailIs= "abc@hotmail.com";

        this.context = getActivity();
        this.instance = this;
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        customers = new ArrayList<>();
         // Postion is index item inside recycleview =
        adapter = new CustomerAdapter(Page_6Fragment.this,customers){
            @Override
            public void buttonClickEvent(int position){
              // Toast.makeText(context,"posIs: "+position+" userLock.len= "+userLock.length,Toast.LENGTH_SHORT).show();
                 //showDialog(context,customers.get(position).user_id , Integer.parseInt(customers.get(position).id));
                 //---- condithion 1
                if(customers.get(position).status ==0){
                   showDialog(context,customers.get(position).user_id , Integer.parseInt(customers.get(position).id));
                }else{
                    Toast.makeText(context,"تم الرد على هذا المستخدم مسبقا", Toast.LENGTH_SHORT).show();
                }
            }
        };


        adapter.setLoadMoreListener(new CustomerAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        int index = customers.size();
                        loadMore(index);
                    }
                });
                //Calling loadMore function in Runnable to fix the
                // java.lang.IllegalStateException: Cannot call this method while RecyclerView is computing a layout or scrolling error
            }
        });
        recyclerView.setHasFixedSize(true);
        //  recyclerView.addItemDecoration(new VerticalLineDecorator(2));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        api = ServiceGenerator.createService(API.class);
        load(0);
        return rootView;
    }

    // load data first time
    private void load(int index) {
        //index = 3 item pagenation , id = ? = 1 or > 1 manager or not manager;
      //  Log.i("getUser_idid{--: ", " "+Session.getInstance().getUser().getName());
                //Session.getInstance().getUser().getEmail()
        Call<List<Customer>> call = api.getCustomer(index , myNameIs);
        Log.i("GeteEmail{--: ", " "+Session.getInstance().getUser().getEmail());
        // user_name ;
        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, final Response<List<Customer>> response) {
                // Toast.makeText(MainActivity.this, "tost "+response.body().get(0).post_writer, Toast.LENGTH_LONG).show();
              // Log.i("TRUE_TRUE_","Yes "+response.body().get(0).name);
                if (response.isSuccessful()) {
                   Log.i("TRUE_TRUE3_000"," ---/ "+response.body().size());
                    //movies.addAll(response.body());
                    //adapter.notifyDataChanged();
                    getActivity().runOnUiThread(new Runnable(){
                    public void run(){
                     // No.1 ..............
                     // ShowDataScreen();
                     // Toast.makeText( MainActivity.this, "ShowDataScreen",Toast.LENGTH_SHORT).show();
                     // if(customers.get()){
                     customers.addAll(response.body());
                     adapter.notifyDataChanged();
                     initiUserlock(customers.size());
                        }
                    });// end of No.1 UI new thread
                    getActivity().runOnUiThread(new Runnable(){
                        public void run() {//No.2
                            // Toast.makeText( MainActivity.this, "This is correct way",Toast.LENGTH_SHORT).show();
                        }
                    });// end of No.2 UI new thread
                    // Toast.makeText(MainActivity.this, "tost "+response.body().get(0).post_writer, Toast.LENGTH_LONG).show();
                } else {
                    //Log.e(TAG, " Response Error " + String.valueOf(response.code()));
                  Log.e(TAG, " Response Error " + String.valueOf(response.body().toString()));
                }
            }
            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Log.e(TAG, " Response Error0 " + t.getMessage().toString());
            }
        });
    }
    //  laod more data .........................
    private void loadMore(int index) {
        // add loading progress view ....
        //Toast.makeText(context, "loadMore", Toast.LENGTH_LONG).show();
        customers.add(new Customer("load"));
        // customers.get(index).user_id =2;
        adapter.notifyItemInserted(customers.size() - 1);
        Call<List<Customer>> call = api.getCustomer(index,myNameIs);
        Log.i("GetName11{--: ", " "+Session.getInstance().getUser().getEmail());
        Log.i("GetmyEmailIs{--: ", " "+myNameIs);
        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(context, "it is Successful", Toast.LENGTH_LONG).show();
                    customers.remove(customers.size() - 1);
                    List<Customer> result = response.body();
                   // List<Customer> objs;
                   // Log.i("_sizeBefore{--: ", " "+result.size());
                    /*
                    for (int i=0; i<result.size(); i++) {
                        if (result.get(i).user_id != Session.getInstance().getUser().id) {
                          result.remove(result);
                        }
                    }
                    for (int i=0; i<result.size(); i++) {
                        Log.i("Len_Result{--: ", " "+result.get(i).name);
                    }
                   // Log.i("a{--: ", " "+result.get(0).name);
                    //Log.i("b{--: ", " "+result.get(0).name);
                  //  Log.i("c{--: ", " "+result.get(0).name);
                     */
                    if(result.size()>0) {
                     customers.addAll(result);
                      //add loaded data
                        /*
                        // How to delete every item = 3 from  customers list
                        for (int i=0; i<customers.size(); i++) {
                            if(customers.get(i).user_id == 3){
                             //  customers.remove(i);
                            }
                        }
                        */
                    } else {
                        //result size 0 means there is no more data available at server
                        adapter.setMoreDataAvailable(false);
                        //telling adapter to stop calling load more as no more server data available
                        Toast.makeText(context,"لايوجد بيانات اخرى", Toast.LENGTH_LONG).show();
                    }
                    adapter.notifyDataChanged();
                    //should call the custom method adapter.notifyDataChanged here to get the correct loading status
                } else {
                    Log.e(TAG, " Load More Response Error000 " + String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call,Throwable t) {
                Log.e(TAG, " Load More Response Error_11 " + t.getMessage());
            }

        });

    }

    // send Notifaction
    private void sendNotfic(Context mConttext,final int id, final int currentIndex, final String accept_refuse, final String day, final String hour) {
        RequestQueue queue = Volley.newRequestQueue(mConttext);
         // String url = "http://192.168.1.3/Pagination/sendNotifaction.php";
         String url = "https://ahmedco.000webhostapp.com/sendNotifaction.php";
         //this is the url where you want to send the request
         //TODO: replace with your own url to send request, as I am using my own localhost for this tutorial
         // Request a string response from the provided URL.
         StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do it with this it will work
                        try {
                            JSONObject test_response = new JSONObject(response);
                             String res = test_response.getString("success");
                             //Log.i("ResponseIs::", "yes_" + res.toString());
                             lock = res.toString();
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
                params.put("title",accept_refuse);
                params.put("masg",day);
                params.put("hour",hour);
                params.put("id",String.valueOf(id));
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    // Show Dialog
    public void showDialog(final Context context, final int getToken, final int id) {
        Toast.makeText(context," Is "+  id, Toast.LENGTH_SHORT).show();
        dialog = new Dialog(context);
        dialog.setCancelable(true);
        // dialog.setTitle("استقبال الطلب");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout);
        dialog.show();
        String[] itemSpinner = {};
        initiDialog(dialog);
        ArrayAdapter<String> a = new ArrayAdapter<String>(context, R.layout.spinner_item, itemSpinner);
        //ArrayAdapter b = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankNames2);
        //a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin_h.setAdapter(a);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) dialog.findViewById(checkedId);
                if (checkedId == R.id.radio_reject) {
                    showDetails(false);
                    acceptOrNo = true;
                } else if (checkedId == R.id.radio_accipt) {
                    showDetails(true);
                    acceptOrNo = false;
                }
            }
        });
       /// Button to send data and updata recycle view
        buttonDialogAccipt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO Auto-generated method stub
                // Log.i("trace_u",""+acceptOrNo);
                if (!acceptOrNo)
                    sendNotfic(context,id,getToken,"1",spin_day.getSelectedItem().toString(), "الساعه" + spin_h.getSelectedItem().toString() + ":" + spin_m.getSelectedItem().toString() + " " + spin_apm.getSelectedItem().toString());
                else
                    sendNotfic(context,id,getToken,"2","--","--");
            dialog.dismiss();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //updateList();
                        checkUser(CustomerAdapter.currentItem);
                    }
                }, 100);
              //dialog.dismiss();
            }
        });
        /// Button to send data and updata recycle view
        buttonDialogReject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
               dialog.dismiss();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       // updateList();
                        checkUser(CustomerAdapter.currentItem);
                    }
                }, 100);
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        //  adapter.refreshEvents(customers);
                    }
                });
            }
        });
       // dialog.dismiss();
    }

    public  void initiDialog(Dialog dialog){
        th = (TextView) dialog.findViewById(R.id.tv_h);
        tm = (TextView) dialog.findViewById(R.id.tv_m);
        tt = (TextView) dialog.findViewById(R.id.tv_title);
        tapm = (TextView) dialog.findViewById(R.id.tv_pam);
        spin_h = (Spinner) dialog.findViewById(R.id.spin_hour);
        spin_m = (Spinner) dialog.findViewById(R.id.spin_minutes);
        spin_apm = (Spinner) dialog.findViewById(R.id.spin_apm);
        spin_day = (Spinner) dialog.findViewById(R.id.spin_day);
        spin_h.setOnItemSelectedListener(this);
        spin_m.setOnItemSelectedListener(this);
        spin_apm.setOnItemSelectedListener(this);
        spin_day.setOnItemSelectedListener(this);
        radioReject = (RadioButton) dialog.findViewById(R.id.radio_reject);
        radioAccipt = (RadioButton) dialog.findViewById(R.id.radio_accipt);
        radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
        buttonDialogAccipt = (Button) dialog.findViewById(R.id.button_cancel);
        buttonDialogReject = (Button) dialog.findViewById(R.id.button_set);
    }


    public  void initiUserlock(int Len){
        userLock = new int[Len];
        for (int i = 0; i <userLock.length; i++){
            userLock[i]=0;
        }
    }

    public  void addUserlock(int newLen ,int index,int val){
        int[] temp = new int[newLen];
        int length = userLock.length;
        for (int j = 0; j < length; j++){
            temp[j] = userLock[j];
        }
        userLock = temp;
        userLock[index]=val;
    }


    public void checkUser(int curr_item){
        View v = recyclerView.getLayoutManager().findViewByPosition(curr_item).findViewById(R.id.textViewOptions);
        if(lock !=null){
            if(lock.trim().equals("1")){
               lock = "0";
               v.setBackground(context.getResources().getDrawable(R.drawable.unlock_));
               //addUserlock(customers.size(),curr_item,1);
            }
        }else{
          Log.i("lockIs",""+lock);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextSize(16);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }


    public void  showDetails(boolean show){
        View vi[] = {spin_h,spin_m,spin_apm,spin_day,th,tm,tt,tapm};
        for (View anArray : vi) {
            if(!show)anArray.setVisibility(View.GONE);
            else
            anArray.setVisibility(View.VISIBLE);
        }
    }
}






//Session.getInstance().getUser().id
/// Update data inside recycleview
//  private void updateList() {
// ArrayList<String> newItems = new ArrayList<String>();
//adapter = new CustomerAdapter(Page_6Fragment.this, customers);
//   Log.i("updateList_", "status(0): " + customers.get(0).status);
// Log.i("updateList_", "status(1): " + customers.get(1).status);
//Log.i("updateList_", "status(2): " + customers.get(2).status);
// Log.i("updateList_", "status(3): " + customers.get(3).status);
// Log.i("curr_item_", ": " + currentItem);
//Log.i("loock", ": " + lock);
//  checkUser(currentItem);
        /*
        View v = recyclerView.getLayoutManager().findViewByPosition(currentItem).findViewById(R.id.textViewOptions);
        v.setVisibility(View.INVISIBLE);

        if(lock.trim().equals("1")){

            lock = "0";
            Log.i("testt", "Oky_one " );

        }
        */
// checkUser(1);

//setBackground(context.getResources().getDrawable(R.drawable.lock));


//setVisibility(View.INVISIBLE);

        /*
        List<Customer> newList = new ArrayList<>();
        newList.addAll(customers);
        customers.clear();
       // customers.addAll(newList);
        recyclerView.setAdapter(new CustomerAdapter(Page_6Fragment.this, newList));
        recyclerView.invalidate();
        */
// }

/*
   if (userLock.length>=position){
           // Toast.makeText(context,"UserLock Is "+ userLock[position], Toast.LENGTH_SHORT).show();
           Log.i("PositionIS ::", "Is:" + position);
           Log.i("userLock_SiezIs::", "Is:" + userLock.length);
           //showDialog(context,customers.get(position).user_id , Integer.parseInt(customers.get(position).id));

                    if(userLock[position]==1 || customers.get(position).status ==1|| customers.get(position).status ==2){
                        Log.i("Masgbox:_:", "Is:" + "no");
                    }else{
                        Log.i("Masgbox::", "Is:" + "yse");
                        showDialog(context,customers.get(position).user_id , Integer.parseInt(customers.get(position).id));
                    }

           }else {
           Log.i(":userLock","userLock.length<position"+userLock.length);
           }

           */

/*
      /*
                        for (int i=0; i<result.size(); i++){
                            if(result.get(i).user_id != Session.getInstance().getUser().id){
                               // result.remove(i);
                           break;
                            }
                            Log.i("Getresult{--: ", " "+result.get(i).name);
                        }
                        */

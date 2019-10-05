package com.ahmedibrahim.waher.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedibrahim.waher.R;
import com.ahmedibrahim.waher.activities.CreateAccountActivity;
import com.ahmedibrahim.waher.activities.HomeActivity;
import com.ahmedibrahim.waher.activities.LoginActivity;
import com.ahmedibrahim.waher.activities.SplashScreen;
import com.ahmedibrahim.waher.activities.WinActivity;
import com.ahmedibrahim.waher.models.Itemchange;
import com.ahmedibrahim.waher.models.MainResponse;
import com.ahmedibrahim.waher.webservices.WebService;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cca on 24/01/2019.
 */
//https://stackoverflow.com/questions/39918814/use-jsonreader-setlenienttrue-to-accept-malformed-json-at-line-1-column-1-path

public class Page_7Fragment  extends Fragment {

    public static String myNameIs;

    public static Page_7Fragment newInstance(String user_name) {

         myNameIs = user_name;

        Page_7Fragment fragment = new Page_7Fragment();

        return fragment;
    }

    TextView inputText;
    int indexItem;
    // Array of strings...
    String[] mobileArray = {"تغير اسم الحساب", "تغير البريد الاليكترونى", "تغير كلمة السر", "حزف حسابى"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.setting_acitivity, container, false);
        // ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),R.layout.custom_list, mobileArray);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        // TextView textView=(TextView) rootView.findViewById(R.id.tv_1);
         /*YOUR CHOICE OF COLOR*/
        // textView.setTextColor(Color.BLUE);
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_items, mobileArray);
        listView.setAdapter(adapter);
         /*
         for (int i = 0; i < listView.getChildCount(); i++) {
            ((TextView)listView.getChildAt(i)).setTextColor(getResources().getColor(R.color.green));
         }
        */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                indexItem = position;
                show_dailoge(indexItem);
                Log.i("traces_0 ", "" + position);

            }
        });
        return rootView;
    }

    private boolean isValidEmailId(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    private void show_dailoge(int pos){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getActivity());
        View mView = layoutInflaterAndroid.inflate(R.layout.custom_dialog, null);
        inputText = (TextView) mView.findViewById(R.id.userInputDialog);
        if (pos == 0) inputText.setHint("ادخل اسمك الجديد");
        else if (pos == 1) inputText.setHint("ادخل البريد الاليكترونى الجديد");
        else if (pos == 2) inputText.setHint("ادخل كلمة السر");
        else if (pos == 3) inputText.setHint("لا يشترط ادخال قيمة");
        /// ------------------------------------------------------// --------------------
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getActivity());
        alertDialogBuilderUserInput.setView(mView);
        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("إرسال", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here
                       boolean validEmail= isValidEmailId(inputText.getText().toString());
                        if(id !=1){
                            update(inputText.getText().toString(), indexItem);
                        }else{
                            if(validEmail){
                                update(inputText.getText().toString(), indexItem);
                            }else {
                                Toast.makeText(getActivity(), "تاكد من البريد الاليكترونى", Toast.LENGTH_SHORT).show();
                            }
                        }
                         // update(inputText.getText().toString(), indexItem);
                        //  Log.i("msg00",""+inputText.getText().toString());
                    }
                }).setNegativeButton("الغاء",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });
        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }


    public void update(String value, int i) {
       // final Itemchange item = new Itemchange(value);
       // item.setValue("ah");
        WebService.getInstance().getApi().updateItem(value,myNameIs,i+1).enqueue(new Callback<Itemchange>(){
            @Override
            public void onResponse(Call<Itemchange> call, Response<Itemchange> response) {
                //  Log.i("status_ Else", "ysysys"+response.body().getMessage().toString());
                Intent intent = new Intent(getActivity(),SplashScreen.class);
                startActivity(intent);
                //   response.message()
            }
            @Override
            public void onFailure(Call<Itemchange> call, Throwable t) {
                Log.e("exr00", t.toString());
            }
        });
    }
}
















/*
 <TextView
        android:id="@+id/dialogTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="ادخل القيمه"
        android:textAppearance="?android:attr/textAppearanceLarge"
        tools:ignore="HardcodedText" />
 */

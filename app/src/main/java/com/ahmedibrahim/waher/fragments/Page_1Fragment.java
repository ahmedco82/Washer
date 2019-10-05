package com.ahmedibrahim.waher.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ahmedibrahim.waher.R;
import com.ahmedibrahim.waher.activities.HomeActivity;

import java.util.ArrayList;

import static com.ahmedibrahim.waher.adapters.AllUsersAdapter.ClearArray;


/**
 * Created by cca on 02/12/2018.
 */

public class Page_1Fragment extends Fragment {

    Button b1,b2,b3,b4;
    //Button bn;
    Toolbar toolbar;
    TextView mToolBarTextView;
    TextView tv_userName;

    /*
    public static Page_1Fragment newInstance() {
        Page_1Fragment fragment = new Page_1Fragment();
        return fragment;
    }
  */

    public static Page_1Fragment newInstance(String s1,String s2,String s3,String s4){

        Page_1Fragment fragment = new Page_1Fragment();

        Bundle args = new Bundle();
        args.putString("s1", s1);
        args.putString("s2", s2);
        args.putString("s3", s3);
        args.putString("s4", s4);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_home, container, false);
        b1 = (Button) rootView.findViewById(R.id.b1);
        b2 = (Button) rootView.findViewById(R.id.b2);
        b3 = (Button) rootView.findViewById(R.id.b3);
        b4 = (Button) rootView.findViewById(R.id.b4);
        //  bn = (Button) rootView.findViewById(R.id.btn_next);
        tv_userName= (TextView)rootView.findViewById(R.id.tv_name_user);

        if (getArguments() != null)
            updateTextFields(getArguments().getString("s1"),getArguments().getString("s2"),getArguments().getString("s3"),getArguments().getString("s4"));
        //t.setText("09");
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
               // Toast.makeText(getActivity(),"b1", Toast.LENGTH_SHORT).show();
                swapFragment("غسيل مكوى");
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                // Toast.makeText(getActivity(),"b1", Toast.LENGTH_SHORT).show();
                swapFragment("غسيل وطي");
            }
        });
        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                // Toast.makeText(getActivity(),"b1", Toast.LENGTH_SHORT).show();
                swapFragment("غسيل فقط");
            }
        });
        b4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // Toast.makeText(getActivity(),"b1", Toast.LENGTH_SHORT).show();
                swapFragment("تنظيف جاف");
            }
        });

      return  rootView;
    }


    private void updateTextFields(String s1, String s2, String s3, String s4) {

        tv_userName.setText(s1);
    }

    private void swapFragment(String title){
        ClearArray(getActivity());
        Page_3Fragment newGamefragment = new Page_3Fragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, newGamefragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        ((HomeActivity)getActivity()).FunctionToolbar(title,"الرئيسية");
    }
}



//  ((HomeActivity)getActivity()).getActionBar().hide();
package com.ahmedibrahim.waher.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmedibrahim.waher.R;


/**
 * Created by cca on 25/11/2018.
 */

public class Page_4Fragment extends Fragment {
    public static Page_4Fragment newInstance() {
        Page_4Fragment fragment = new Page_4Fragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_adddeaitels, container, false);
         //View rootView = inflater.inflate(R.xml.pref, container, false);
         //Intent intent = new Intent(PreferenceDemoActivity.this,PrefsActivity.class);
        //startActivity(intent);
        return rootView;
       //return inflater.inflate(R.layout.activity_wins, container, false);
       // return inflater.inflate(R.layout.activity_adddeaitels, container, false);
    }
}


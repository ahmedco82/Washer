package com.ahmedibrahim.waher.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmedibrahim.waher.R;


/**
 * Created by cca on 12/12/2018.
 */

public class Page_5Fragment extends android.support.v4.app.Fragment {
    public static Page_5Fragment newInstance() {
        Page_5Fragment fragment = new Page_5Fragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_wins, container, false);
        //   View rootView = inflater.inflate(R.xml.pref, container, false);
        //  Intent intent = new Intent(PreferenceDemoActivity.this,PrefsActivity.class);
        // startActivity(intent);
        return rootView;
        // return inflater.inflate(R.layout.activity_wins, container, false);
        // return inflater.inflate(R.layout.activity_adddeaitels, container, false);
    }
}
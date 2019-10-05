/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.ahmedibrahim.waher.fragments;
import android.os.Bundle;
import android.view.View;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;


import com.ahmedibrahim.waher.R;

import java.util.ArrayList;


public class Page_2Fragment extends Fragment {

    private  TextView updateTextParts;
    private  TextView updateTextPrice;
    private  View view;
    private  ArrayList<String>wordsItem;


    public static Page_2Fragment newInstance(String s1,String s2,String s3,String s4,String s5) {
        Page_2Fragment fragment = new Page_2Fragment();
        Bundle args = new Bundle();
        args.putString("s1", s1);
        args.putString("s2", s2);
        args.putString("s3", s3);
        args.putString("s4", s4);
        args.putString("s5", s5);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View rootView = lf.inflate(R.layout.activity_orderdetails,container,false);
        updateTextParts = (TextView)rootView.findViewById(R.id.et_total_parts);
        updateTextPrice= (TextView)rootView.findViewById(R.id.et_total_price);
        wordsItem=new ArrayList<>();
        if (getArguments() != null)
            updateTextFields(getArguments().getString("s1"),getArguments().getString("s2"),getArguments().getString("s3"),getArguments().getString("s4"),getArguments().getString("s5"));
        //t.setText("09");
        final ScrollView main = (ScrollView) rootView.findViewById(R.id.dealscroll);
        main.post(new Runnable() {
            public void run() {

                main.scrollTo(0,0);
            }
        });
        final int bgColor = 0xAAAAFFFF;
        //rootView.setBackgroundColor(bgColor);
        ListView list = (ListView) rootView.findViewById(R.id.lView);
        //TextView tv = (TextView) rootView.findViewById(R.id.tView);
       // ArrayAdapter<String> items = new ArrayAdapter<String>(getActivity() , android.R.layout.simple_list_item_1 , wordsItem);
        // list.setTextColor(Color.RED);
        //simple_list_item_1.setTextColor(Color.WHITE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, wordsItem) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.RED);
                return view;
            }
        };
        list.setAdapter(adapter);
       return rootView;
        //return inflater.inflate(R.layout.activity_orderdetails, container, false);
       // return inflater.inflate(R.layout.activity_home, container, false);
    }


    public  void updateTextFields(String s1,String s2,String s3,String s4,String s5){
        updateTextParts.setText(s1);
        updateTextPrice.setText(s2);
        wordsItem.add("رقم الطلب"+s4);
        wordsItem.add("العنوان: "+s3);
        wordsItem.add("موعد الدفع: الدفع عند الاستلام");
        wordsItem.add("للتواصل:00201280327362");
        wordsItem.add("موعد التسليم :قيد الانتظار");
        wordsItem.add("المستلم "+s5);
    }




    //updateText.setTextSize(size);
        // updateText.setTextColor(color);


}

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
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmedibrahim.waher.R;
import com.ahmedibrahim.waher.adapters.AllUsersAdapter;

import com.ahmedibrahim.waher.models.ProductRecyclerViewItem;

import java.util.ArrayList;

public class Page_3Fragment extends Fragment{

    public static Page_3Fragment newInstance() {

        Page_3Fragment fragment = new Page_3Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_requst_a, container, false);
        RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.rvAllUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<ProductRecyclerViewItem> UserList = new ArrayList<>();
        UserList.add(new ProductRecyclerViewItem(R.drawable.hat, "كاب او طاقية", "4 جنية للقطعة"));
        UserList.add(new ProductRecyclerViewItem(R.drawable.shirt,
                "قميض او تيشيرت",
                "5 جنية للقطعة"));
        UserList.add(new ProductRecyclerViewItem(R.drawable.jacket,
                "جاكيت شيتوي",
                "6 جنية للقطعة"));
        UserList.add(new ProductRecyclerViewItem(R.drawable.jeans,
                "بنطلون",
                "5 جنية للقطعة"));
        UserList.add(new ProductRecyclerViewItem(R.drawable.robe,
                "جلباب او روب",
                "8 جنية للقطعة"));
        UserList.add(new ProductRecyclerViewItem(R.drawable.suit,
                "بدلة",
                "7 جنية للقطعة"));
        UserList.add(new ProductRecyclerViewItem(R.drawable.gloves,
                "قفازات",
                "4 جنية"));
        AllUsersAdapter allUserAdapter = new AllUsersAdapter(getActivity(),UserList);
        recyclerView.setAdapter(allUserAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

}

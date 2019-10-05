package com.ahmedibrahim.waher.activities;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.ahmedibrahim.waher.R;


public class PrefsActivity extends PreferenceActivity{
   Context context;
@Override
protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   //addPreferencesFromResource(R.xml.prefs);
   addPreferencesFromResource(R.xml.pref);

   //to show the custom preference;

   Preference button = (Preference)getPreferenceManager().findPreference("But");

 //  Preference button = (Preference)getPreferenceManager().findPreference("item_d");

   if(button != null) {
      button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

         @Override
         public boolean onPreferenceClick(Preference arg0) {
        	Toast.makeText(context ,"Yesxxxxxxxxxx",Toast.LENGTH_SHORT).show();
            finish();
            //  test();
            return true;
         }
      });
   }






   // End ...............
}




}
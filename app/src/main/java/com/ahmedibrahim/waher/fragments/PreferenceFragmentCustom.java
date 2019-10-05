package com.ahmedibrahim.waher.fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.LinearLayout;

import com.ahmedibrahim.waher.R;


/**
 * The Preference Fragment which shows the Preferences as a List and handles the Dialogs for the
 * Preferences.
 *
 * @author Jakob Ulbrich
 */

public class PreferenceFragmentCustom extends PreferenceFragmentCompat {
    /**
     * {@inheritDoc}
     */
    LinearLayout root;
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.pref);


    }

}

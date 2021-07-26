package it.buch85.prefs;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import java.util.List;

import it.buch85.R.xml;


public class SettingsActivity extends PreferenceActivity  {

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(xml.preference_headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        if (First.class.getName().equals(fragmentName)
                ) {
            return(true);
        }

        return(false);
    }

    public static class First extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(xml.preferences);
        }
    }

}

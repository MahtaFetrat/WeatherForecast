package com.example.weatherforecast.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.weatherforecast.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        //Theme switch listener
        ListPreference themePreference = findPreference("theme_preference");
        themePreference.setOnPreferenceChangeListener((preference, newValue) -> {
            setDayNightModeByThemeValue(newValue.toString());
            return true;
        });
    }

    public static void setDayNightModeByThemeValue(String themeValue) {
        int[] dayNightModes = {AppCompatDelegate.MODE_NIGHT_NO, AppCompatDelegate.MODE_NIGHT_YES, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM};
        AppCompatDelegate.setDefaultNightMode(dayNightModes[Integer.parseInt(themeValue)]);
    }
}
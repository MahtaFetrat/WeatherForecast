package com.example.weatherforecast.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherforecast.MainActivity;
import com.example.weatherforecast.R;

public class DailyWeatherDetailActivity extends AppCompatActivity {

    TextView weekday;
    TextView maxTemp;
    TextView minTemp;
    TextView description;
    ImageView icon;
    TextView windSpeed;
    TextView windDegree;
    TextView humidity;
    TextView dayFeel;
    TextView nightFeel;
    TextView eveFeel;
    TextView mornFeel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather_detail);

        findViews();
        setUIValues();
    }

    private void findViews() {
        weekday = findViewById(R.id.daily_detail_weekday);
        maxTemp = findViewById(R.id.daily_detail_max_temp);
        minTemp = findViewById(R.id.daily_detail_min_temp);
        description = findViewById(R.id.daily_detail_description);
        icon = findViewById(R.id.daily_detail_icon);
        windSpeed = findViewById(R.id.daily_detail_wind_speed);
        windDegree = findViewById(R.id.daily_detail_wind_degree);
        humidity = findViewById(R.id.daily_detail_humidity);
        dayFeel = findViewById(R.id.daily_detail_day_feel);
        nightFeel = findViewById(R.id.daily_detail_night_feel);
        eveFeel = findViewById(R.id.daily_detail_eve_feel);
        mornFeel = findViewById(R.id.daily_detail_morn_feel);
    }

    private void setUIValues() {
        Intent intent = getIntent();
        weekday.setText(intent.getStringExtra("weekday"));
        maxTemp.setText(intent.getStringExtra("maxTemp"));
        minTemp.setText(intent.getStringExtra("minTemp"));
        description.setText(intent.getStringExtra("description"));
        icon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), getResources().getIdentifier(intent.getStringExtra("icon"), "drawable", getPackageName()), null));
        windSpeed.setText(intent.getStringExtra("windSpeed"));
        windDegree.setText(intent.getStringExtra("windDegree"));
        humidity.setText(intent.getStringExtra("humidity"));
        dayFeel.setText(intent.getStringExtra("dayFeel"));
        nightFeel.setText(intent.getStringExtra("nightFeel"));
        eveFeel.setText(intent.getStringExtra("eveFeel"));
        mornFeel.setText(intent.getStringExtra("mornFeel"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(String.valueOf(R.string.latitude_save_key), MainActivity.latitude);
        editor.putFloat(String.valueOf(R.string.longitude_save_key), MainActivity.longitude);
        editor.apply();
    }
}
package com.example.weatherforecast.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weatherforecast.model.WeatherForecastData;

import java.util.List;

import kotlin.NotImplementedError;

public class WeatherViewModel extends AndroidViewModel {
    LiveData<List<WeatherForecastData>> next10Days;
    LiveData<WeatherForecastData> currentWeather;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<WeatherForecastData> getCurrentWeather() {
        return currentWeather;
    }

    public LiveData<List<WeatherForecastData>> getNext10Days() {
        return next10Days;
    }

    public void setLocation(float latitude, float longitude) {
        //asynchronously fetch data and set to livedata variables
        throw new NotImplementedError();
    }

    public void setLocation(String address) {
        //asynchronously finds the corresponding geographic coordinates and calls setLocation with coordinates
        throw new NotImplementedError();
    }
}

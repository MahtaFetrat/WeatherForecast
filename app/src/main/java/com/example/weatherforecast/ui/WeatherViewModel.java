

package com.example.weatherforecast.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weatherforecast.model.Details;


import kotlin.NotImplementedError;

public class WeatherViewModel extends AndroidViewModel {
    LiveData<Details> weatherDetails;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Details> getWeatherDetails() {
        return weatherDetails;
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
package com.example.weatherforecast.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weatherforecast.model.GeographicCoordinates;
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

    public void setLocation(GeographicCoordinates coordinates) {
        //asynchronously fetch data and set to livedata variables
        throw new NotImplementedError();
    }

    public GeographicCoordinates getGeographicCoordinated(String address) {
        //asynchronously finds and returns the corresponding geographic coordinates using input address
        throw new NotImplementedError();
    }
}

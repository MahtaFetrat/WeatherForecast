

package com.example.weatherforecast.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherforecast.MainActivity;
import com.example.weatherforecast.controller.WeatherForecastController;
import com.example.weatherforecast.model.CurrentDetails;
import com.example.weatherforecast.model.DayDetails;
import com.example.weatherforecast.model.Details;
import com.example.weatherforecast.model.FeelsLike;
import com.example.weatherforecast.model.Temp;
import com.example.weatherforecast.model.Weather;
import com.google.gson.Gson;

import java.io.File;

import kotlin.NotImplementedError;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class WeatherViewModel extends AndroidViewModel {
    WeatherForecastController controller;
    MutableLiveData<Details> weatherDetails;
    MutableLiveData<String> toastMessage;
    OkHttpClient client;
    Gson gson;

    public WeatherViewModel(@NonNull Application application) {
        super(application);

        controller = new WeatherForecastController();
        Cache cache = new Cache(new File(getApplication().getCacheDir(),"WeatherForecastCache"), 10 * 1024 * 1024);
        client = new OkHttpClient.Builder().cache(cache).build();
        gson = new Gson();

        toastMessage = new MutableLiveData<String>();
        weatherDetails = new MutableLiveData<Details>();
    }

    public LiveData<Details> getWeatherDetails() {
        return weatherDetails;
    }

    public void setLiveDetails(Details details) {
        if (details != null) {
            weatherDetails.postValue(details);
            MainActivity.setLocationValues(details.getLat(), details.getLon());
        }
    }

    public void reportInternetNotConnected() {
        toastMessage.postValue("No internet connection");
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void setLocation(float latitude, float longitude) {
        controller.getDetails(latitude, longitude, client, gson, this);
    }

    public void setLocation(String cityName) {
        //asynchronously finds the corresponding geographic coordinates and calls setLocation with coordinates
        controller.getCityDetails(cityName, client, gson, this);
    }

    public void reportInvalidLocation() {
        toastMessage.postValue("Couldn't find the desired location");
    }
}
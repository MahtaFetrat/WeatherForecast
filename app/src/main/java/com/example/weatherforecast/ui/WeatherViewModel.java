

package com.example.weatherforecast.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    OkHttpClient client;
    Gson gson;

    public WeatherViewModel(@NonNull Application application) {
        super(application);

        controller = new WeatherForecastController();
        Cache cache = new Cache(new File(getApplication().getCacheDir(),"WeatherForecastCache"), 10 * 1024 * 1024);
        client = new OkHttpClient.Builder().cache(cache).build();
        gson = new Gson();

        // Sample detail object
        weatherDetails = new MutableLiveData<Details>(new Details(33.44,
                -94.04,
                "America/Chicago",
                -21600,
                new CurrentDetails(1618317040,
                        13,
                        14,
                        62,
                        6,
                        300,
                        new Weather[]{new Weather(500, "Rain", "light rain", "10d")}),
                new DayDetails[]{new DayDetails(1618308000,
                        new Temp(5, 27),
                        new FeelsLike(16, 10, 12, 13),
                        81,
                        3.06,
                        294,
                        new Weather[]{new Weather(500, "Rain", "light rain", "10d")}),
                        new DayDetails(1618308000,
                                new Temp(8, 29),
                                new FeelsLike(19, 10, 12, 13),
                                61,
                                3.16,
                                280,
                                new Weather[]{new Weather(500, "Cloudy", "Partially sunny", "02d")})}));
    }

    public LiveData<Details> getWeatherDetails() {
        return weatherDetails;
    }

    public void setLiveDetails(Details details) {
        weatherDetails.setValue(details);
    }

    public void handleNoInternetError() {
        // ToDo
    }

    public void handleNoCacheFound() {
        // ToDo
    }

    public void setLocation(float latitude, float longitude) {
        controller.getDetails(latitude, longitude, client, gson, this);
//        throw new NotImplementedError();
    }

    public void setLocation(String address) {
        //asynchronously finds the corresponding geographic coordinates and calls setLocation with coordinates
        throw new NotImplementedError();
    }
}


package com.example.weatherforecast.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherforecast.model.CurrentDetails;
import com.example.weatherforecast.model.DayDetails;
import com.example.weatherforecast.model.Details;
import com.example.weatherforecast.model.FeelsLike;
import com.example.weatherforecast.model.Temp;
import com.example.weatherforecast.model.Weather;

import kotlin.NotImplementedError;

public class WeatherViewModel extends AndroidViewModel {
    MutableLiveData<Details> weatherDetails;

    public WeatherViewModel(@NonNull Application application) {
        super(application);

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
                        new Temp(27, 5),
                        new FeelsLike(16, 10, 12, 13),
                        81,
                        3.06,
                        294,
                        new Weather[]{new Weather(500, "Rain", "light rain", "10d")})}));
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
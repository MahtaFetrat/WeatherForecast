package com.example.weatherforecast.model;

public class WeatherForecastData {
    public enum WeatherStatus {
        SUNNY_CLEAR,
        CLOUDY,
        PARTIALLY_CLOUDY,
        WINDY,
        RAINY,
        SNOWY,
        STORMY
    }

    int temperature;
    int temperatureFeel;
    int windSpeed;
    WeatherStatus weatherStatus;
}

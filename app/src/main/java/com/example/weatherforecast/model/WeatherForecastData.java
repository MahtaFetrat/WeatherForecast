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

    private int temperature;
    private int temperatureFeel;
    private int windSpeed;
    private WeatherStatus weatherStatus;
}

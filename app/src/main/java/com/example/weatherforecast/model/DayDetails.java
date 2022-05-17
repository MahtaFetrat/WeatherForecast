package com.example.weatherforecast.model;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DayDetails {
    private long dt;
    private Temp temp;
    private FeelsLike feels_like;
    private int humidity;
    private double wind_speed;
    private int wind_deg;
    private Weather[] weather;

    public DayDetails(long dt, Temp temp, FeelsLike feels_like, int humidity, double windSpeed, int wind_deg, Weather[] weather) {
        this.dt = dt;
        this.temp = temp;
        this.feels_like = feels_like;
        this.humidity = humidity;
        this.wind_speed = windSpeed;
        this.wind_deg = wind_deg;
        this.weather = weather;
    }

    public String getWeekday() {
        Date date = new Date(this.dt);
        return new SimpleDateFormat("EEEE").format(date);
    }

    public Temp getTemp() {
        return temp;
    }

    public Weather getWeather() {
        return weather[0];
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public int getWind_deg() {
        return wind_deg;
    }

    public int getHumidity() {
        return humidity;
    }

    public FeelsLike getFeels_like() {
        return feels_like;
    }
}

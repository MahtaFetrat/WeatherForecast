package com.example.weatherforecast.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DayDetails {
    private long dt;
    private long sunrise;
    private long sunset;
    private long moonrise;
    private long moonset;
    private double moon_phase;
    private Temp temp;
    private FeelsLike feelsLike;
    private int pressure;
    private int humidity;
    private double dew_point;
    private double windSpeed;
    private int windDeg;
    private double wind_gust;
    private Weather[] weather;
    private int clouds;
    private double pop;
    private double uvi;

    public DayDetails(long dt, Temp temp, FeelsLike feels_like, int humidity, double windSpeed, int wind_deg, Weather[] weather) {
        this.dt = dt;
        this.temp = temp;
        this.feelsLike = feels_like;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDeg = wind_deg;
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

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public int getHumidity() {
        return humidity;
    }

    public FeelsLike getFeelsLike() {
        return feelsLike;
    }
}

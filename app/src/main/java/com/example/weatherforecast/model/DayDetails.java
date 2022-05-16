package com.example.weatherforecast.model;

public class DayDetails {
    private long dt;
    private long sunrise;
    private long sunset;
    private long moonrise;
    private long moonset;
    private double moon_phase;
    private Temp temp;
    private FeelsLike feels_like;
    private int pressure;
    private int humidity;
    private double dew_point;
    private double wind_speed;
    private int wind_deg;
    private double wind_gust;
    private Weather[] weather;
    private int clouds;
    private double pop;
    private double uvi;

    public DayDetails(long dt, Temp temp, FeelsLike feels_like, int humidity, double wind_speed, int wind_deg, Weather[] weather) {
        this.dt = dt;
        this.temp = temp;
        this.feels_like = feels_like;
        this.humidity = humidity;
        this.wind_speed = wind_speed;
        this.wind_deg = wind_deg;
        this.weather = weather;
    }
}

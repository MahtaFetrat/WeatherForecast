package com.example.weatherforecast.model;

public class CurrentDetails {
    private long dt;
    private long sunrise;
    private long sunset;
    private double temp;
    private double feels_like;
    private int pressure;
    private int humidity;
    private double dew_point;
    private double wind_speed;
    private int wind_deg;
    private double wind_gust;
    private Weather[] weather;
    private int clouds;
    private double uvi;
    private int visibility;

    public CurrentDetails(long dt, double temp, double feels_like, int humidity, double wind_speed, int wind_deg, Weather[] weather) {
        this.dt = dt;
        this.temp = temp;
        this.feels_like = feels_like;
        this.humidity = humidity;
        this.wind_speed = wind_speed;
        this.wind_deg = wind_deg;
        this.weather = weather;
    }

    public String getWeatherIcon() {
        return weather[0].toString();
    }
}

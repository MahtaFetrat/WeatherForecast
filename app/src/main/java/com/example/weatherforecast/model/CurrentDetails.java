package com.example.weatherforecast.model;

public class CurrentDetails {
    private long dt;
    private double temp;
    private double feels_like;
    private int humidity;
    private double wind_speed;
    private int wind_deg;
    private Weather[] weather;

    public CurrentDetails(long dt, double temp, double feels_like, int humidity, double wind_speed, int wind_deg, Weather[] weather) {
        this.dt = dt;
        this.temp = temp;
        this.feels_like = feels_like;
        this.humidity = humidity;
        this.wind_speed = wind_speed;
        this.wind_deg = wind_deg;
        this.weather = weather;
    }

    public double getTemp() {
        return temp;
    }

    public Weather getWeather() {
        return weather[0];
    }

    public double getFeels_like() {
        return feels_like;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public int getWind_deg() {
        return wind_deg;
    }
}

package com.example.weatherforecast.model;

public class Details {
    private double lat;
    private double lon;
    private String timezone;
    private CurrentDetails current;
    private DayDetails[] daily;

    public Details(double lat, double lon, String timezone, int timezone_offset, CurrentDetails current, DayDetails[] daily) {
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.current = current;
        this.daily = daily;
    }

    public CurrentDetails getCurrent() {
        return current;
    }

    public DayDetails[] getDaily() {
        return daily;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getTimezone() {
        return timezone;
    }
 }

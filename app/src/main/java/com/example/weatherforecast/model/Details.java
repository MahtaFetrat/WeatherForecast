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
        this.timezone_offset = timezone_offset;
        this.current = current;
        this.daily = daily;
    }

    public CurrentDetails getCurrent() {
        return current;
    }

    public DayDetails[] getDaily() {
        return daily;
    }
}

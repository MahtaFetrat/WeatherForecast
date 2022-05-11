package com.example.weatherforecast.model;

public class Details {
    private double lat;
    private double lon;
    private String timezone;
    private int timezone_offset;
    private CurrentDetails current;
    private DayDetails[] daily;
}

package com.example.weatherforecast.model;

public class Temp {
    private double day;
    private double min;
    private double max;
    private double night;
    private double eve;
    private double morn;

    public Temp(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}

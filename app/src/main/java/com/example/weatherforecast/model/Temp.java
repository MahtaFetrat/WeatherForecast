package com.example.weatherforecast.model;

public class Temp {
    private double min;
    private double max;

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

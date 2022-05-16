package com.example.weatherforecast.model;

public class Weather {
    private String main;
    private String description;
    private String icon;

    public Weather(int id, String main, String description, String icon) {
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getIconDrawableName() {
        return String.format("@drawable/ic__%s_2x", this.icon);
    }

    public String getDescription() {
        return description;
    }
}
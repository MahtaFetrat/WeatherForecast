package com.example.weatherforecast.model;

import java.util.ArrayList;

public class Geometry {
    public String type;
    public ArrayList<Float> coordinates;

    public Geometry(String type, ArrayList<Float> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
}

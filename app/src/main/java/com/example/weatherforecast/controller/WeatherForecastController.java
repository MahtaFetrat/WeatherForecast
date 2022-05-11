package com.example.weatherforecast.controller;

import androidx.annotation.NonNull;

import com.example.weatherforecast.model.Details;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherForecastController {

    public void getDetails(double lat, double lon, OkHttpClient client, Gson gson) {
        client.newCall(getRequest(lat, lon)).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // ToDo: no internet. handle cache...
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.isSuccessful()) {
                    parseResponse(response, gson);
                } else {
                    // ToDo: request failed. handle bad response...
                }
            }
        });
    }

    public void parseResponse(Response response, Gson gson) {
        Details Details = gson.fromJson(response.body().charStream(), Details.class);
        // ToDo: make livedata instances
    }

    public String getUrl(double lat, double lon) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.openweathermap.org/data/2.5/onecall").newBuilder();
        urlBuilder.addQueryParameter("lat", Double.toString(lat));
        urlBuilder.addQueryParameter("lon", Double.toString(lon));
        urlBuilder.addQueryParameter("exclude", "minutely,hourly,alerts");
        urlBuilder.addQueryParameter("units", "metric");
        urlBuilder.addQueryParameter("appid", "287afd3a55adef419360a4921cbcd4c0");
        return urlBuilder.build().toString();
    }

    public Request getRequest(double lat, double lon) {
        return new Request.Builder().url(getUrl(lat, lon)).build();
    }
}

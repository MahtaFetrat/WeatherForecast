package com.example.weatherforecast.controller;

import androidx.annotation.NonNull;

import com.example.weatherforecast.model.City;
import com.example.weatherforecast.model.Details;
import com.example.weatherforecast.ui.WeatherViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherForecastController {

    public void getDetails(double lat, double lon, OkHttpClient client, Gson gson, WeatherViewModel viewModel) {
        client.newCall(getRequest(lat, lon)).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                viewModel.reportInternetNotConnected();
                try {
                    Response response = client.newCall(getCacheRequest(lat, lon)).execute();
                    if (response.isSuccessful()) {
                        parseResponse(response, gson, viewModel);
                    }
                } catch (IOException ioException) {
                    // this code is never reached because the request is "onlyIfCached".
                    ioException.printStackTrace();
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.isSuccessful()) {
                    parseResponse(response, gson, viewModel);
                } else {
                    viewModel.reportInvalidLocation();
                }
            }
        });
    }

    public void parseResponse(Response response, Gson gson, WeatherViewModel viewModel) {
        Details details = gson.fromJson(response.body().charStream(), Details.class);
        viewModel.setLiveDetails(details);
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
        return new Request.Builder()
                .cacheControl(new CacheControl.Builder().noCache().build())
                .url(getUrl(lat, lon)).build();
    }

    public Request getCacheRequest(double lat, double lon) {
        return new Request.Builder()
                .cacheControl(new CacheControl.Builder().onlyIfCached().maxStale(12, TimeUnit.HOURS).build())
                .url(getUrl(lat, lon)).build();
    }

    public void getCityDetails(String cityName, OkHttpClient client, Gson gson, WeatherViewModel viewModel) {

        Request request = getCityRequest(cityName);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                viewModel.reportInternetNotConnected();
                try {
                    Response response = client.newCall(getCityCacheRequest(cityName)).execute();
                    if (response.isSuccessful()) {
                        parseCityResponse(response, client, gson, viewModel);
                    }
                } catch (IOException ioException) {
                    // this code is never reached because the request is "onlyIfCached".
                    ioException.printStackTrace();
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    parseCityResponse(response, client, gson, viewModel);
                } else {
                    viewModel.reportInvalidLocation();
                }
            }
        });
    }

    public void parseCityResponse(Response response, OkHttpClient client, Gson gson, WeatherViewModel viewModel) {

        JsonObject cities = null;
        try {
            cities = JsonParser.parseString(response.body().string()).getAsJsonObject();
            JsonArray jsonArray = (JsonArray) cities.get("features");
            City city = gson.fromJson(jsonArray.get(0), City.class);
            ArrayList<Float> coordinates = city.geometry.coordinates;
            getDetails(coordinates.get(0), coordinates.get(1), client, gson, viewModel);
        } catch (Exception e) {
            viewModel.reportInvalidLocation();
        }
    }

    public String getCityUrl(String cityName) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.mapbox.com/geocoding/v5/mapbox.places/" + cityName + ".json").newBuilder();
        urlBuilder.addQueryParameter("types", "place");
        urlBuilder.addQueryParameter("access_token", "pk.eyJ1Ijoib3N0YWRla2FjaGFsIiwiYSI6ImNsMzdhZnpieDAyY28za28yemtlZGZxMmUifQ.-6Cs_zk2QFG8_k8h2Jb4JQ");

        return urlBuilder.build().toString();
    }

    public Request getCityRequest(String cityName) {
        return new Request.Builder()
                .cacheControl(new CacheControl.Builder().noCache().build())
                .url(getCityUrl(cityName)).build();
    }

    public Request getCityCacheRequest(String cityName) {
        return new Request.Builder()
                .cacheControl(new CacheControl.Builder().onlyIfCached().maxStale(12, TimeUnit.HOURS).build())
                .url(getCityUrl(cityName)).build();
    }
}

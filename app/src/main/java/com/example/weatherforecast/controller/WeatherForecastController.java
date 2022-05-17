package com.example.weatherforecast.controller;

import androidx.annotation.NonNull;

import com.example.weatherforecast.model.Details;
import com.example.weatherforecast.ui.WeatherViewModel;
import com.google.gson.Gson;

import java.io.IOException;
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
                viewModel.handleNoInternetError();
                try {
                    Response response = client.newCall(getCacheRequest(lat, lon)).execute();
                    if (response.isSuccessful()) {
                        parseResponse(response, gson, viewModel);
                    } else {
                        viewModel.handleNoCacheFound();
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
                    // ToDo: request failed. handle bad response...
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
                .cacheControl(new CacheControl.Builder().onlyIfCached().maxAge(12, TimeUnit.HOURS).build())
                .url(getUrl(lat, lon)).build();
    }
}

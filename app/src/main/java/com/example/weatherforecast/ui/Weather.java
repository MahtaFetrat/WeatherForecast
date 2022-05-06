package com.example.weatherforecast.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.weatherforecast.R;

public class Weather extends Fragment {
    private final Runnable scheduledRequest;
    private final Handler scheduledRequestHandler;
    private static final int REQUEST_SCHEDULE_SECONDS = 5000;

    public Weather() {
        scheduledRequest = () -> {
            //request data
        };
        scheduledRequestHandler = new Handler();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setInputTypeListener(view);
        setLocationChangeListener(view);
    }

    private void setInputTypeListener(View view) {
        ((RadioGroup) view.findViewById(R.id.location_type_radio_group)).setOnCheckedChangeListener((group, checkedId) -> {
            view.findViewById(R.id.coordinate_input_holder).setVisibility(checkedId == R.id.address_type ? View.GONE : View.VISIBLE);
            view.findViewById(R.id.address_input_view).setVisibility(checkedId == R.id.address_type ? View.VISIBLE : View.GONE);
        });
    }

    private void setLocationChangeListener(View view) {
        TextWatcher locationTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                scheduledRequestHandler.removeCallbacks(scheduledRequest);
                scheduledRequestHandler.postDelayed(scheduledRequest, REQUEST_SCHEDULE_SECONDS);
            }
        };

        ((EditText) view.findViewById(R.id.address_input_view)).addTextChangedListener(locationTextWatcher);
        ((EditText) view.findViewById(R.id.latitude)).addTextChangedListener(locationTextWatcher);
        ((EditText) view.findViewById(R.id.longitude)).addTextChangedListener(locationTextWatcher);
    }
}
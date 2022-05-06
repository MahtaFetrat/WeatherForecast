package com.example.weatherforecast.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.weatherforecast.R;

public class Weather extends Fragment {
    private final Runnable scheduledRequest;
    private final Handler scheduledRequestHandler;
    private static final int REQUEST_SCHEDULE_SECONDS = 5000;

    private RadioGroup locationTypeRadioGroup;
    private LinearLayout coordinateInputHolderLayout;
    private EditText addressInputView;
    private EditText latitude, longitude;

    private WeatherViewModel viewModel;

    public Weather() {
        scheduledRequest = () -> {
            if (coordinateInputHolderLayout.getVisibility() == View.VISIBLE && !latitude.getText().toString().isEmpty() && !longitude.getText().toString().isEmpty()) {
                viewModel.setLocation(Float.parseFloat(latitude.getText().toString()), Float.parseFloat(longitude.getText().toString()));
            } else if (!addressInputView.getText().toString().isEmpty()) {
                viewModel.setLocation(addressInputView.getText().toString());
            }
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

        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        findViews(view);
        setInputTypeListener();
        setLocationChangeListener();
    }

    private void findViews(View view) {
        locationTypeRadioGroup = view.findViewById(R.id.location_type_radio_group);
        coordinateInputHolderLayout = view.findViewById(R.id.coordinate_input_holder);
        addressInputView = view.findViewById(R.id.address_input_view);
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);
    }

    private void setInputTypeListener() {
        locationTypeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            coordinateInputHolderLayout.setVisibility(checkedId == R.id.address_type ? View.GONE : View.VISIBLE);
            addressInputView.setVisibility(checkedId == R.id.address_type ? View.VISIBLE : View.GONE);
            resetScheduleRequestHandler();
        });
    }

    private void setLocationChangeListener() {
        TextWatcher locationTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                resetScheduleRequestHandler();
            }
        };

        addressInputView.addTextChangedListener(locationTextWatcher);
        latitude.addTextChangedListener(locationTextWatcher);
        longitude.addTextChangedListener(locationTextWatcher);
    }

    private void resetScheduleRequestHandler() {
        scheduledRequestHandler.removeCallbacks(scheduledRequest);
        scheduledRequestHandler.postDelayed(scheduledRequest, REQUEST_SCHEDULE_SECONDS);
    }
}
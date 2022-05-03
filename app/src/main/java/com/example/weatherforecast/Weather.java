package com.example.weatherforecast;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class Weather extends Fragment {

    public Weather() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setup to show correct input fields
        ((RadioGroup) view.findViewById(R.id.location_type_radio_group)).setOnCheckedChangeListener((group, checkedId) -> {
            view.findViewById(R.id.coordinate_input_holder).setVisibility(checkedId == R.id.address_type ? View.GONE : View.VISIBLE);
            view.findViewById(R.id.address_input_view).setVisibility(checkedId == R.id.address_type ? View.VISIBLE : View.GONE);
        });

    }
}
package com.example.weatherforecast.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherforecast.R;
import com.example.weatherforecast.model.CurrentDetails;
import com.example.weatherforecast.model.DayDetails;
import com.example.weatherforecast.model.Details;
import com.example.weatherforecast.model.FeelsLike;
import com.example.weatherforecast.model.Temp;
import com.example.weatherforecast.model.Weather;

public class WeatherFragment extends Fragment {
    private final Runnable scheduledRequest;
    private final Handler scheduledRequestHandler;
    private static final int REQUEST_SCHEDULE_SECONDS = 5000;

    private RadioGroup locationTypeRadioGroup;
    private LinearLayout coordinateInputHolderLayout;
    private EditText addressInputView;
    private EditText latitude, longitude;

    private ImageView currentWeatherIcon;
    private TextView currentWeatherDescription;
    private TextView currentWeatherTemp;
    private TextView currentWeatherMaxTemp;
    private TextView currentWeatherMinTemp;
    private TextView currentWeatherRealFeel;
    private TextView currentWeatherHumidity;
    private TextView currentWeatherWind;

    private RecyclerView dailyForecastRecyclerView;
    private DailyForecastAdapter dailyForecastAdapter;

    private WeatherViewModel viewModel;

    public WeatherFragment() {
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
        setInputTypeListener(view);
        setLocationChangeListener();
        initializeDailyForecastRecyclerView();

        setViewModelObservers();
    }

    private void findViews(View view) {
        locationTypeRadioGroup = view.findViewById(R.id.location_type_radio_group);
        coordinateInputHolderLayout = view.findViewById(R.id.coordinate_input_holder);
        addressInputView = view.findViewById(R.id.address_input_view);
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);

        currentWeatherIcon = view.findViewById(R.id.current_weather_icon);
        currentWeatherDescription = view.findViewById(R.id.current_weather_description);
        currentWeatherTemp = view.findViewById(R.id.current_weather_temp);
        currentWeatherMaxTemp = view.findViewById(R.id.current_weather_max_temp);
        currentWeatherMinTemp = view.findViewById(R.id.current_weather_min_temp);
        currentWeatherRealFeel = view.findViewById(R.id.current_weather_real_feel);
        currentWeatherHumidity = view.findViewById(R.id.current_weather_humidity);
        currentWeatherWind = view.findViewById(R.id.current_weather_wind);

        dailyForecastRecyclerView = view.findViewById(R.id.daily_forecast_recyclerview);
    }

    private void setInputTypeListener(View view) {
        locationTypeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            coordinateInputHolderLayout.setVisibility(checkedId == R.id.address_type ? View.GONE : View.VISIBLE);
            addressInputView.setVisibility(checkedId == R.id.address_type ? View.VISIBLE : View.GONE);

            // move focus to new input field
            if (view.findViewById(checkedId == R.id.address_type ? R.id.address_input_view : R.id.latitude).requestFocus()) {
                ((InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(
                        InputMethodManager.SHOW_FORCED,
                        InputMethodManager.HIDE_IMPLICIT_ONLY
                );
            }
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

        // add 5sec timer input listeners
        addressInputView.addTextChangedListener(locationTextWatcher);
        latitude.addTextChangedListener(locationTextWatcher);
        longitude.addTextChangedListener(locationTextWatcher);

        // add onAction input listeners
        addressInputView.setOnEditorActionListener((textView, i, keyEvent) -> {
            viewModel.setLocation(addressInputView.getText().toString());
            return true;
        });
        longitude.setOnEditorActionListener((textView, i, keyEvent) -> {
            viewModel.setLocation(Float.parseFloat(latitude.getText().toString()), Float.parseFloat(longitude.getText().toString()));
            return true;
        });
    }

    private void resetScheduleRequestHandler() {
        scheduledRequestHandler.removeCallbacks(scheduledRequest);
        scheduledRequestHandler.postDelayed(scheduledRequest, REQUEST_SCHEDULE_SECONDS);
    }

    private void initializeDailyForecastRecyclerView() {
        dailyForecastRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dailyForecastAdapter = new DailyForecastAdapter(getActivity(), new DayDetails[]{});
        dailyForecastRecyclerView.setAdapter(dailyForecastAdapter);
    }

    private void setViewModelObservers() {
        viewModel.getWeatherDetails().observe(getViewLifecycleOwner(), details -> {
            currentWeatherIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), getResources().getIdentifier(details.getCurrent().getWeather().getIconDrawableName(), "drawable", getActivity().getPackageName()), null));
            currentWeatherDescription.setText(details.getCurrent().getWeather().getDescription());
            currentWeatherTemp.setText(String.format("%.0fºc", details.getCurrent().getTemp()));
            currentWeatherMaxTemp.setText(String.format("%.0fº", details.getDaily()[0].getTemp().getMax()));
            currentWeatherMinTemp.setText(String.format("%.0fº", details.getDaily()[0].getTemp().getMin()));
            currentWeatherRealFeel.setText(String.format("%.0fº", details.getCurrent().getFeels_like()));
            currentWeatherHumidity.setText(String.format("%d%%", details.getCurrent().getHumidity()));
            currentWeatherWind.setText(String.format("%dº, %.1fmp/h", details.getCurrent().getWind_deg(), details.getCurrent().getWind_speed()));
            dailyForecastAdapter.updateList(details.getDaily());
        });
        
        viewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        });
    }
}
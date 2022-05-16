package com.example.weatherforecast.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforecast.R;
import com.example.weatherforecast.model.DayDetails;

public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.ViewHolder> {
    Context context;
    private DayDetails[] dailyDetails;

    public DailyForecastAdapter(Context context, DayDetails[] dailyDetails) {
        this.context = context;
        this.dailyDetails = dailyDetails;
    }

    public void updateList(DayDetails[] dayDetails) {
        this.dailyDetails = dayDetails;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_weather_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dailyWeatherWeekday.setText(dailyDetails[position].getWeekday());
        holder.dailyWeatherMaxTemp.setText(String.format("%.0fº", dailyDetails[position].getTemp().getMax()));
        holder.dailyWeatherMinTemp.setText(String.format("%.0fº", dailyDetails[position].getTemp().getMin()));
        holder.dailyWeatherIcon.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), context.getResources().getIdentifier(dailyDetails[position].getWeather().getIconDrawableName(), "drawable", context.getPackageName()), null));
    }

    @Override
    public int getItemCount() {
        return dailyDetails.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dailyWeatherWeekday;
        TextView dailyWeatherMaxTemp;
        TextView dailyWeatherMinTemp;
        ImageView dailyWeatherIcon;

        public ViewHolder(View view) {
            super(view);

            dailyWeatherWeekday = view.findViewById(R.id.daily_weather_weekday);
            dailyWeatherMaxTemp = view.findViewById(R.id.daily_weather_max_temp);
            dailyWeatherMinTemp = view.findViewById(R.id.daily_weather_min_temp);
            dailyWeatherIcon = view.findViewById(R.id.daily_weather_icon);
        }
    }
}

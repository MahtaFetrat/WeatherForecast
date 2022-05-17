package com.example.weatherforecast.ui;

import android.content.Context;
import android.content.Intent;
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
        holder.view.setOnClickListener(v -> {
            Intent intent = new Intent(context, DailyWeatherDetailActivity.class);
            DayDetails dailyDetail = dailyDetails[position];
            intent.putExtra("weekday", dailyDetail.getWeekday());
            intent.putExtra("maxTemp", String.format("%.0fº", dailyDetail.getTemp().getMax()));
            intent.putExtra("minTemp", String.format("%.0fº", dailyDetail.getTemp().getMin()));
            intent.putExtra("description", dailyDetail.getWeather().getDescription());
            intent.putExtra("icon", dailyDetail.getWeather().getIconDrawableName());
            intent.putExtra("windSpeed", String.format("%.1fmp/h", dailyDetail.getWind_speed()));
            intent.putExtra("windDegree", String.format("%dº", dailyDetail.getWind_deg()));
            intent.putExtra("humidity", String.format("%d%%", dailyDetail.getHumidity()));
            intent.putExtra("dayFeel", String.format("%.0fº", dailyDetail.getFeels_like().getDay()));
            intent.putExtra("nightFeel", String.format("%.0fº", dailyDetail.getFeels_like().getNight()));
            intent.putExtra("eveFeel", String.format("%.0fº", dailyDetail.getFeels_like().getEve()));
            intent.putExtra("mornFeel", String.format("%.0fº", dailyDetail.getFeels_like().getMorn()));
            context.startActivity(intent);
        });
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
        View view;
        TextView dailyWeatherWeekday;
        TextView dailyWeatherMaxTemp;
        TextView dailyWeatherMinTemp;
        ImageView dailyWeatherIcon;

        public ViewHolder(View view) {
            super(view);
            this.view = view;

            dailyWeatherWeekday = view.findViewById(R.id.daily_weather_weekday);
            dailyWeatherMaxTemp = view.findViewById(R.id.daily_weather_max_temp);
            dailyWeatherMinTemp = view.findViewById(R.id.daily_weather_min_temp);
            dailyWeatherIcon = view.findViewById(R.id.daily_weather_icon);
        }
    }
}

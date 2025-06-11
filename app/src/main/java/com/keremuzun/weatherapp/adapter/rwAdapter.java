package com.keremuzun.weatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.keremuzun.weatherapp.R;
import com.keremuzun.weatherapp.databinding.RecyclerRowBinding;
import com.keremuzun.weatherapp.service.weatherModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class rwAdapter extends RecyclerView.Adapter<rwAdapter.Holder>{
    private List<weatherModel> weatherModelList = new ArrayList<>();
    weatherModel weatherModel;

    public rwAdapter(List<weatherModel> weatherModelList) {
        this.weatherModelList = weatherModelList;
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Holder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        //NAME
        String fullName = weatherModelList.get(position).city.name; // Ör: "İstanbul Province"
        String cityName = fullName.split(" ")[0]; // Boşluktan böl, ilk kelimeyi alır

        holder.binding.city.setText(cityName);


        //DATE
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("tr", "TR"));

        for (int i = 0; i < 4; i++) {
            long dt = (long) weatherModelList.get(position).daysList.get(i).dt;
            Date date = new Date(dt * 1000L);
            String dayName = sdf.format(date);

            switch (i) {
                case 0:
                    holder.binding.today.setText(dayName);
                    break;
                case 1:
                    holder.binding.tomorrow.setText(dayName);
                    break;
                case 2:
                    holder.binding.day2.setText(dayName);
                    break;
                case 3:
                    holder.binding.day3.setText(dayName);
                    break;
            }
        }



        //TEMP
        for (int i = 0; i < 4; i++) {
            float temp = weatherModelList.get(position).daysList.get(i).temp.day;

            switch (i) {
                case 0:
                    holder.binding.todaytemp.setText(String.format(Locale.getDefault(), "%.1f °C", temp));
                    break;
                case 1:
                    holder.binding.tomorrowtemp.setText(String.format(Locale.getDefault(), "%.1f °C", temp));
                    break;
                case 2:
                    holder.binding.day2temp.setText(String.format(Locale.getDefault(), "%.1f °C", temp));
                    break;
                case 3:
                    holder.binding.day3temp.setText(String.format(Locale.getDefault(), "%.1f °C", temp));
                    break;
            }
        }

        //ICONS
        for (int i = 0; i < 4; i++) {
            String iconCode = weatherModelList.get(position).daysList.get(i).weatherList.get(0).icon;

            Context context = holder.itemView.getContext();

            int iconResId = context.getResources().getIdentifier("ic_" + iconCode, "drawable", context.getPackageName());

            switch (i) {
                case 0:
                    if (iconResId != 0) holder.binding.todayimage.setImageResource(iconResId);
                    break;
                case 1:
                    if (iconResId != 0) holder.binding.tomorrowimage.setImageResource(iconResId);
                    break;
                case 2:
                    if (iconResId != 0) holder.binding.day2image.setImageResource(iconResId);
                    break;
                case 3:
                    if (iconResId != 0) holder.binding.day3image.setImageResource(iconResId);
                    break;
            }
        }


    }


    @Override
    public int getItemCount() {
        return weatherModelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private RecyclerRowBinding binding;

        public Holder(@NonNull RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
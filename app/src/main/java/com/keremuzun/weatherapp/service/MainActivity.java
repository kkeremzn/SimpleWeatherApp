package com.keremuzun.weatherapp.service;
import static com.keremuzun.weatherapp.service.weatherAPI.cityList;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.keremuzun.weatherapp.R;
import com.keremuzun.weatherapp.adapter.rwAdapter;
import com.keremuzun.weatherapp.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    weatherAPI weatherAPI;
    Retrofit retrofit;
    List<weatherModel> weatherModelList = new ArrayList<>();
    rwAdapter adapter;


    private static final String BASE_URL = "https://api.openweathermap.org/";
    private static final String API_KEY = "303d156bd03c1bdab202d91ad0e9d8c3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //ADAPTER
        adapter = new rwAdapter(weatherModelList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);


        //RETROFIT / GSON
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        getData();


    }
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private void getData(){
        //GETDATA
        weatherAPI = retrofit.create(com.keremuzun.weatherapp.service.weatherAPI.class);

        for (String city : cityList){
            compositeDisposable.add(weatherAPI.getData(city,API_KEY,"metric")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            response ->{
                                weatherModelList.add(response);
                                adapter.notifyItemInserted(weatherModelList.size() - 1);

                            },
                            error ->{
                                Toast.makeText(this, "There is a problem getData", Toast.LENGTH_SHORT).show();


                            }
                    ));

        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}

package com.keremuzun.weatherapp.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class weatherModel {
    @SerializedName("city")
    public City city;
    public static class City{
        @SerializedName("name")
        public String name;
    }
    //
    @SerializedName("list")
    public List<Days> daysList;
    public static class Days{
        @SerializedName("dt")
        public float dt;
        @SerializedName("weather")
        public List<Weather> weatherList;
        @SerializedName("temp")
        public Temp temp;
    }
    //
    public static class Weather{
        @SerializedName("icon")
        public String icon;
    }

    public static class Temp{
        @SerializedName("day")
        public float day;
    }

}





/* {
  "city": {
    "name": "Istanbul"
  },
  "list": [
    {
      "dt": 1717687200,
      "temp": {
        "day": 25.6
      },
      "weather": [
        {
          "icon": "10d"
        }
      ]
    }
  ]
}


@SerializedName("name")
    public String name;

    @SerializedName("main")
    public Main main;

    @SerializedName("weather")
    public Weather[] weather;

    @SerializedName("dt")
    public long dt; // Unix time

    public static class Main {
        @SerializedName("temp")
        public float temp;
    }

    public static class Weather {
        @SerializedName("icon")
        public String icon;
    }*/

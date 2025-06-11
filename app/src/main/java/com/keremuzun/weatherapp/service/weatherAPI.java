package com.keremuzun.weatherapp.service;
import com.keremuzun.weatherapp.service.weatherModel;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface weatherAPI {

    @GET("data/2.5/forecast/daily")
    Observable<weatherModel> getData(
            @Query("q") String cityName,
            @Query("appid") String apiKey,
            @Query("units") String units


    );
    String[] cityList = {  "Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın",
            "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa",
            "Çanakkale", "Çankırı", "Çorum",
            "Denizli", "Diyarbakır",
            "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane",
            "Hakkâri", "Hatay",
            "Isparta", "Mersin", "İstanbul", "İzmir",
            "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir",
            "Kocaeli", "Konya", "Kütahya",
            "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş",
            "Nevşehir", "Niğde",
            "Ordu", "Rize",
            "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas",
            "Tekirdağ", "Tokat", "Trabzon", "Tunceli",
            "Şanlıurfa", "Uşak",
            "Van",
            "Yozgat",
            "Zonguldak",
            "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın",
            "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};
}

package com.example.miniprojectakthemmalek.model.api;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.view.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("ALL")
public class RetrofitInstance {

    private static Retrofit retrofit;
    private static Retrofit retrofit2;
    private static final String BASE_URL = "http://"+ Constants.IP_ADDRESS +":3003/";
    private static final String NOTIFICATION_URL = "https://fcm.googleapis.com/";

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;
    }

    public static Retrofit getNotificationRetrofitInstance(){
        if(retrofit2 == null){

            retrofit2 = new retrofit2.Retrofit.Builder()
                    .baseUrl(NOTIFICATION_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit2;
    }





}

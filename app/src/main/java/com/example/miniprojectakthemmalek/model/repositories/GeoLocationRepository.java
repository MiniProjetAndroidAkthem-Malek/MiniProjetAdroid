package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.ILocate;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IPost;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.google.gson.JsonElement;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeoLocationRepository {



    private static GeoLocationRepository instance;


    public static GeoLocationRepository getInstance(){
        if(instance == null){
            instance = new GeoLocationRepository();
        }
        return instance;
    }

    ILocate iLocate = RetrofitInstance.getRetrofitGoogleApiInstance().create(ILocate.class);

    public void getAllPostOf(Double lat,Double lon,final getAdresses getAdresses)
    {
        Call<JsonElement> call;
        call = iLocate.getAddress("40.714224,-73.961452","AIzaSyBIEZtZepvood6udPAFCJ0JSJt22NMq1ao");
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                System.out.println(response.code());
                getAdresses.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }


    public interface getAdresses
    {
        public void onResponse(JsonElement addresses);
    }

}

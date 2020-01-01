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





    public interface getAdresses
    {
        public void onResponse(JsonElement addresses);
    }

}

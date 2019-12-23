package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Post;
import com.google.gson.JsonElement;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ILocate {


@GET("maps/api/geocode/json")
Call<JsonElement> getAddress(@Query("latlng") String latlng, @Query("key") String key);


}

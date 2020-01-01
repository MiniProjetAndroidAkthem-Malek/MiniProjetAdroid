package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Activities;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IActivities {


@POST("/activity/add")
Call<JsonPrimitive> addActivity(@Body Activities activities);

@GET("/activity/getAll")
Call<List<Activities>> getAllActivities();

@GET("/activity/getAllByUsername/{username}")
Call<List<Activities>> getAllActivitiesByUsername(@Path("username") String username);

}

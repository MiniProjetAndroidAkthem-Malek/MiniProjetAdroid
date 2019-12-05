package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IFollow {




@POST("followers/add")
Call<JsonPrimitive> addFollow(@Body Follow follow);

@GET("/followers/getAllFollowers/{username}")
Call<List<Follow>> getWhatFollows(@Path("username") String username);

@GET("/followers/getAllFollowing/{following}")
Call<List<Follow>> getFollowing(@Path("following") String following);

@GET("/followers/getOne/{username}/{following}")
Call<List<Follow>> getFollower(@Path("username") String username,@Path("following") String following);

@DELETE("followers/delete/{username}/{following}")
Call<JsonPrimitive> deleteFollow(@Path("username") String username,@Path("following") String following);

}

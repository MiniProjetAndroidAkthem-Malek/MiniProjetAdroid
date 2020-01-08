package com.example.miniprojectakthemmalek.model.api.entityInterface;


import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.Tag;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ITag {


@POST("tag/add")
Call<JsonPrimitive> addTag(@Body Tag tag);

@GET("/tag/get/{id}")
Call<List<Tag>> getTagById(@Path("id") int id);

@GET("/tag/get/{name}")
Call<List<Tag>> getTagByName(@Path("name") String name);

}

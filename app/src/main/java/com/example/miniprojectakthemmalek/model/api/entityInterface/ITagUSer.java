package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Tag;
import com.example.miniprojectakthemmalek.model.entities.Tag_user;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ITagUSer {


@POST("tagUser/add")
Call<JsonPrimitive> addTagUser(@Body Tag_user tag_user);

@GET("/tagUSer/get/{id}")
Call<List<Tag_user>> getTagUserById(@Path("id") int id);

@GET("/tagUSer/get/{username}")
Call<List<Tag_user>> getTagByUsername(@Path("username") String username);

@GET("/tagUSer/get/{name}")
Call<List<Tag_user>> getTagByName(@Path("name") String name);



}

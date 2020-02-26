package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Tag;
import com.example.miniprojectakthemmalek.model.entities.Tag_post;
import com.example.miniprojectakthemmalek.model.entities.Tag_user;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ITagPost {


@POST("tagPost/add")
Call<JsonPrimitive> addTagPost(@Body Tag_post tag_post);

@GET("/tagPost/getByName/{name}")
Call<List<Tag_post>> getTagByName(@Path("name") String name);

@GET("/tagPost/get/{id}")
Call<List<Tag_post>> getTagByIdPost(@Path("id") int id);

}

package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.comment;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IComment {




@POST("/comments/add")
Call<JsonPrimitive> addComment(@Body comment comment);

@GET("/comments/getByPost/{post}")
Call<List<comment>> getPostComment(@Path("post") int post);



}

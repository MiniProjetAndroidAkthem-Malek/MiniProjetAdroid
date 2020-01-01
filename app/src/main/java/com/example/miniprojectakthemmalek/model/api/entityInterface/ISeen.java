package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.Seen;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ISeen {


@POST("/seen/add")
Call<JsonPrimitive> addSeen(@Body Seen seen);

@GET("/seen/getByIdMessageAndUsername/{id_message}/{username}")
Call<List<Seen>> getSeenByIdMessageAndUsername(@Path("id_message") int id_message,@Path("username") String username);


@DELETE("/seen/delete/{id_message}")
Call<JsonPrimitive> deleteSeenByIdMessage(@Path("id_message") int id);

@DELETE("/seen/delete/{id_message}/{username}")
Call<JsonPrimitive> deleteSeenByIdMessageAndUsername(@Path("id_message") int id,@Path("username") String username);



}

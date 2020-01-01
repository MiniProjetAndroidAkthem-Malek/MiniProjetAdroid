package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Children;
import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IChildren {


@POST("/children/add")
Call<JsonPrimitive> addChildren(@Body Children children);

@GET("/children/getByParent/{parent}")
Call<List<Children>> getChildrenByParent(@Path("parent") String parent);

@GET("/children/getById/{id}")
Call<List<Children>> getChildrenById(@Path("id") int id);

@DELETE("/children/delete/{id}")
Call<JsonPrimitive> deleteChildren(@Path("id") int id);


}

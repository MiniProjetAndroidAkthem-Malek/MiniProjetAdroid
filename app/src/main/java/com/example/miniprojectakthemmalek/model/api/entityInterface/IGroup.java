package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IGroup {


@POST("group/add")
Call<JsonPrimitive> addGroup(@Body Group group);

@GET("/group/get/{name}")
Call<List<Group>> getGroup(@Path("name") String name);

@GET("/group/getByCreator/{creator}")
Call<List<Group>> getGroupByCreator(@Path("creator") String creator);

@GET("/group/getAll")
Call<List<Group>> getAllGroups();

}

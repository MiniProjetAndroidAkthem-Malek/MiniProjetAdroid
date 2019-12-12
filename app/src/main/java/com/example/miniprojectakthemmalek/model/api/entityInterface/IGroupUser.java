package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.google.gson.JsonPrimitive;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IGroupUser {


@POST("group_user/add")
Call<JsonPrimitive> addGroup(@Body GroupUser groupUser);


}

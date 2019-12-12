package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupPost;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IGroupPosts {

@GET("/group_post/getByGroup/{name}")
Call<List<Post>> getPostsByGroup(@Path("name") String name);

@POST("group_post/add")
Call<JsonPrimitive> addGroupPost(@Body GroupPost groupPost);

}

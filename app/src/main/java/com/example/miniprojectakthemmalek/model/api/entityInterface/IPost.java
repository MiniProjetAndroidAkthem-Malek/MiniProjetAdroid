package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IPost {


@POST("posts/add")
Call<JsonPrimitive> addPost(@Body Post post);

@GET("/posts/getAll")
Call<List<Post>> getAllPosts();

@GET("/posts/getAll/{username}")
Call<List<Post>> getAllPostsOf(@Path("username") String username);


@GET("/post/get/{username}/{description}")
Call<List<Post>> getPost(@Path("username") String username,@Path("description") String description);

}

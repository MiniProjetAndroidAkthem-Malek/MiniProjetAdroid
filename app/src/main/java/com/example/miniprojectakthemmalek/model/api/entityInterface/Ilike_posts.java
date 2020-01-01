package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.like_posts;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Ilike_posts {




@POST("/like_posts/add")
Call<JsonPrimitive> addLike(@Body  like_posts like_posts);

@GET("/like_posts/getAll/{id_post}")
Call<List<like_posts>> getPostLikes(@Path("id_post") int id_post);


@GET("/like_posts/getAllByUsernameAndId/{id_post}/{username}")
Call<List<like_posts>> getPostLikesByUsernameAndId(@Path("id_post") int id_post,@Path("username") String username);

@DELETE("/like_posts/delete/{username}/{id_post}")
Call<JsonPrimitive> Dislike(@Path("username") String username, @Path("id_post") int id_post);

}

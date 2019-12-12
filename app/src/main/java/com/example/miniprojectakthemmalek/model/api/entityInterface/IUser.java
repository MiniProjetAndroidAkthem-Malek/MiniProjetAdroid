package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface IUser {

@GET("/users/getAll")
Call<List<User>> getAllUsers();

@GET("/users/get/{username}")
Call<List<User>> getOneUser(@Path("username") String username);

@POST("users/add")
Call<JsonPrimitive> addUser(@Body User user);

@PUT("users/update")
Call<JsonObject> updateUser(@Body User user);

@PUT("users/updatePartner")
Call<JsonObject> updatePartnerUser(@Body User user);

@DELETE("users/delete/{username}")
Call<JsonObject> deleteUser(@Path("username") String username);

@Multipart
@POST("/users/upload")
Call<String> updatePhoto(@Part MultipartBody.Part image,@Part("username") RequestBody username);

}


package com.example.miniprojectakthemmalek.model.api.entityInterface;


import android.util.JsonReader;

import com.example.miniprojectakthemmalek.model.entities.Post;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface IImage {

@Multipart
@POST("/upload")
Call<ResponseBody> addImage(@Part MultipartBody.Part image, @Part("upload")RequestBody name);
@GET("/uploads/{username}")
Call<ResponseBody> getPictureOf(@Path("username") String username);

@Multipart
@POST("/upload/event")
Call<ResponseBody> addImageForEvent(@Part MultipartBody.Part image, @Part("upload")RequestBody name);
@GET("/uploads/event/{eventName}")
Call<ResponseBody> getPictureForEvent(@Path("eventName") String username);

@Multipart
@POST("/upload/group")
Call<ResponseBody> addImageForGroup(@Part MultipartBody.Part image, @Part("upload")RequestBody name);
@GET("/uploads/group/{groupName}")
Call<ResponseBody> getPictureForGroup(@Path("groupName") String username);

}

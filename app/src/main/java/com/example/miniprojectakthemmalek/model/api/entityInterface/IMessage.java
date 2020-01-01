package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Message;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IMessage {



@POST("message/add")
Call<JsonPrimitive> addMessage(@Body Message message);

@GET("/message/getAll/{sender}/{receiver}")
Call<List<Message>> getAllMessageOf(@Path("sender") String sender,@Path("receiver") String receiver);

@GET("/message/getAll/{username}")
Call<List<Message>> getAllMessagesOf(@Path("username") String username);

@GET("/message/getDiscussionsOfUsername/{connectedUsername}")
Call<List<Message>> getDiscussionsOfUsername(@Path("connectedUsername") String connectedUsername);

}

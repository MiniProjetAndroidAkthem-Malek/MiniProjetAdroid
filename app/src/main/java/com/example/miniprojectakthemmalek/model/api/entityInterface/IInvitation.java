package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Invitation;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IInvitation {


@POST("invitation/add")
Call<JsonPrimitive> addInvitation(@Body Invitation invitation);

@GET("/invitation/getAll/{sender}")
Call<List<Invitation>> getSendedInvitationForUser(@Path("sender") String username);

@GET("/invitation/getAllByReceiver/{receiver}")
Call<List<Invitation>> getReceivedInvitationForUser(@Path("receiver") String username);

@GET("/invitation/get/{sender}/{receiver}")
Call<List<Invitation>> getInvitationForUser(@Path("sender") String sender,@Path("receiver") String receiver);

@DELETE("invitation/delete/{receiver}/{sender}")
Call<JsonObject> deleteInvitation(@Path("receiver") String receiver,@Path("sender") String sender);

}

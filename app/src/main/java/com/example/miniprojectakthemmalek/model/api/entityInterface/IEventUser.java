package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Event;
import com.example.miniprojectakthemmalek.model.entities.EventUser;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IEventUser {


@POST("events_user/add")
Call<JsonPrimitive> addUserEvent(@Body EventUser eventUser);

@DELETE("/events_user/delete/{username}/{event_name}")
Call<JsonObject> deleteUserEvent(@Path("username") String username, @Path("event_name") String event_name);

@GET("/events_user/getByUsername/{username}/{event_name}")
Call<List<EventUser>> getUserEventByName(@Path("username") String username, @Path("event_name") String event_name);

@DELETE("/events_user/deleteAll/{event_name}")
Call<JsonObject> deleteAllUserEvent(@Path("event_name") String event_name);


}

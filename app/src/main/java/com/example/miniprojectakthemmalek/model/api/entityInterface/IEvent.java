package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Event;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.User;
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

public interface IEvent {


@POST("events/add")
Call<JsonPrimitive> add(@Body Event event);

@GET("/events/get/{nom}")
Call<List<Event>> getEvent(@Path("nom") String nom);

@GET("/events/getByCreator/{creator}")
Call<List<Event>> getEventByCreator(@Path("creator") String creator);

@GET("/events/getAll")
Call<List<Event>> getAllEvents();


@DELETE("/events/delete/{nom}")
Call<JsonObject> deleteEvent(@Path("nom") String nom);


@PUT("events/update/{nom}")
Call<JsonObject> updateEvent(@Body Event event,@Path("nom") String nom);





}

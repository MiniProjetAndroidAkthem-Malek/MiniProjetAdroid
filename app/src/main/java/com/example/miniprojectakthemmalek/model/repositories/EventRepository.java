package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IEvent;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IGroup;
import com.example.miniprojectakthemmalek.model.entities.Event;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepository {

    private static EventRepository instance;

    public static EventRepository getInstance(){
        if(instance == null){
            instance = new EventRepository();
        }
        return instance;
    }


    IEvent ievent = RetrofitInstance.getRetrofitInstance().create(IEvent.class);


    public void addEvent(Event event, final FollowRepository.addingCallback callback )
    {

        Call<JsonPrimitive> call ;
        call =  ievent.add(event);
        call.enqueue(new Callback<JsonPrimitive>() {
            @Override
            public void onResponse(Call<JsonPrimitive> call, Response<JsonPrimitive> response) {

                callback.addingCallback(response.code());

            }

            @Override
            public void onFailure(Call<JsonPrimitive> call, Throwable t) {

                t.printStackTrace();

            }
        });

    }


    public void getEventByName(String nom,final EventRepository.getAllGroupCallBack getManyCallback) {
        Call<List<Event>> call = ievent.getEvent(nom);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

                getManyCallback.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.onResponse(null);
            }
        });
    }

    public void getEventByCreator(String creator,final EventRepository.getAllGroupCallBack getManyCallback) {
        Call<List<Event>> call = ievent.getEventByCreator(creator);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

                getManyCallback.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.onResponse(null);
            }
        });
    }



    public void getAllEvents(final getAllGroupCallBack getAllGroupCallBack)
    {
        Call<List<Event>> call;
        call = ievent.getAllEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

                getAllGroupCallBack.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

                t.printStackTrace();
            }
        });


    }

    public void deleteEvent(String  nom,final EventRepository.addingCallback addingCallback)
    {
        Call<JsonObject> call = ievent.deleteEvent(nom);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                addingCallback.addingCallback(response.code());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                addingCallback.addingCallback(0);

            }
        });

    }



    public void updateEvent(Event event,String nom,final EventRepository.addingCallback addingCallback)
    {
        Call<JsonObject> call ;
        call = ievent.updateEvent(event,nom);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                addingCallback.addingCallback(response.code());

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                addingCallback.addingCallback(0);

            }
        });

    }






    public interface addingCallback
    {
        public void addingCallback(int code);
    }

    public interface getAllGroupCallBack
    {
        public void onResponse(List<Event> groupList);
    }

}

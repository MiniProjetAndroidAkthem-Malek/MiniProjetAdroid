package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IEventUser;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IGroupUser;
import com.example.miniprojectakthemmalek.model.entities.Event;
import com.example.miniprojectakthemmalek.model.entities.EventUser;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventUserRepository {

    private static EventUserRepository instance;

    public static EventUserRepository getInstance(){
        if(instance == null){
            instance = new EventUserRepository();
        }
        return instance;
    }


    IEventUser iEventUser = RetrofitInstance.getRetrofitInstance().create(IEventUser.class);

    public void addUserEvent(EventUser eventUser, final EventUserRepository.addingCallback callback )
    {

        Call<JsonPrimitive> call ;
        call =  iEventUser.addUserEvent(eventUser);
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

    public void deleteUserEvent(String  username,String event_name, final addingCallback addingCallback)
    {
        Call<JsonObject> call = iEventUser.deleteUserEvent(username,event_name);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                addingCallback.addingCallback(response.code());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


    public void getUserEventByName(String username,String event_name, final EventUserRepository.getAllGroupCallBack getAllGroupCallBack) {
        Call<List<EventUser>> call = iEventUser.getUserEventByName(username,event_name);
        call.enqueue(new Callback<List<EventUser>>() {
            @Override
            public void onResponse(Call<List<EventUser>> call, Response<List<EventUser>> response) {

                getAllGroupCallBack.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<EventUser>> call, Throwable t) {

                t.printStackTrace();

                getAllGroupCallBack.onResponse(null);
            }
        });
    }




    public void deleteAllUserEvent(String event_name, final addingCallback addingCallback)
    {
        Call<JsonObject> call = iEventUser.deleteAllUserEvent(event_name);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                addingCallback.addingCallback(response.code());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }







    public interface addingCallback
{
    public void addingCallback(int code);
}


    public interface getAllGroupCallBack
    {
        public void onResponse(List<EventUser> list);
    }

}

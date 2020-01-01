package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IActivities;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IPost;
import com.example.miniprojectakthemmalek.model.entities.Activities;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitiesRepository {

    private static ActivitiesRepository instance;

    public static ActivitiesRepository getInstance(){
        if(instance == null){
            instance = new ActivitiesRepository();
        }
        return instance;
    }

    IActivities iActivities = RetrofitInstance.getRetrofitInstance().create(IActivities.class);

    public void addActivities(final Activities activities, final addingCallback callback )
    {
        Call<JsonPrimitive> call ;

        call =  iActivities.addActivity(activities);
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

    public void getAllByUsername(String username,final getAllCallBack allCallBack)
    {
        Call<List<Activities>> call;
        call = iActivities.getAllActivitiesByUsername(username);
        call.enqueue(new Callback<List<Activities>>() {
            @Override
            public void onResponse(Call<List<Activities>> call, Response<List<Activities>> response) {

                allCallBack.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<Activities>> call, Throwable t) {

                t.printStackTrace();

            }

        });

    }

    public interface getAllCallBack
    {
        public void onResponse(List<Activities> activities);
    }

    public interface addingCallback
    {
        public void addingCallback(int code);
    }

}

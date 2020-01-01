package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IActivities;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IChildren;
import com.example.miniprojectakthemmalek.model.entities.Activities;
import com.example.miniprojectakthemmalek.model.entities.Children;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildrenRepository {


    private static ChildrenRepository instance;

    public static ChildrenRepository getInstance(){
        if(instance == null){
            instance = new ChildrenRepository();
        }
        return instance;
    }



    IChildren iChildren = RetrofitInstance.getRetrofitInstance().create(IChildren.class);

    public void addChildren(final Children children, final ActivitiesRepository.addingCallback callback )
    {
        Call<JsonPrimitive> call ;

        call =  iChildren.addChildren(children);
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

    public void getChildrenByParent(String parent,final getAllCallBack allCallBack)
    {
        Call<List<Children>> call;
        call = iChildren.getChildrenByParent(parent);
        call.enqueue(new Callback<List<Children>>() {
            @Override
            public void onResponse(Call<List<Children>> call, Response<List<Children>> response) {

                allCallBack.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<Children>> call, Throwable t) {

                t.printStackTrace();

            }

        });

    }

    public void getChildrenById(int id,final getAllCallBack allCallBack)
    {
        Call<List<Children>> call;
        call = iChildren.getChildrenById(id);
        call.enqueue(new Callback<List<Children>>() {
            @Override
            public void onResponse(Call<List<Children>> call, Response<List<Children>> response) {

                allCallBack.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<Children>> call, Throwable t) {

                t.printStackTrace();

            }

        });

    }

    public void deleteChildren(int id, final addingCallback callback )
    {

        Call<JsonPrimitive> call = iChildren.deleteChildren(id);
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

    public interface getAllCallBack
    {
        public void onResponse(List<Children> childrenList);
    }

    public interface addingCallback
    {
        public void addingCallback(int code);
    }

}

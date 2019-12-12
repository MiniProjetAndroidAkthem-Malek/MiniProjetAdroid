package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IGroup;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IGroupUser;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.google.gson.JsonPrimitive;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupUserRepository {

    private static GroupUserRepository instance;

    public static GroupUserRepository getInstance(){
        if(instance == null){
            instance = new GroupUserRepository();
        }
        return instance;
    }


    IGroupUser iGroupUser = RetrofitInstance.getRetrofitInstance().create(IGroupUser.class);


    public void addGroupUser(GroupUser groupUser, final GroupUserRepository.addingCallback callback )
    {

        Call<JsonPrimitive> call ;
        call =  iGroupUser.addGroup(groupUser);
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



public interface addingCallback
{
    public void addingCallback(int code);
}




}

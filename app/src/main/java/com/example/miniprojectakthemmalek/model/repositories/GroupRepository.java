package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IFollow;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IGroup;
import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.Invitation;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupRepository {

    private static GroupRepository instance;

    public static GroupRepository getInstance(){
        if(instance == null){
            instance = new GroupRepository();
        }
        return instance;
    }


    IGroup iGroup = RetrofitInstance.getRetrofitInstance().create(IGroup.class);


    public void addGroup(Group group, final FollowRepository.addingCallback callback )
    {

        Call<JsonPrimitive> call ;
        call =  iGroup.addGroup(group);
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


    public void getGroupByName(String name,final GroupRepository.getAllGroupCallBack getManyCallback) {
        Call<List<Group>> call = iGroup.getGroup(name);
        call.enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {

                getManyCallback.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<Group>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.onResponse(null);
            }
        });
    }

    public void getGroupByCreator(String creator,final GroupRepository.getAllGroupCallBack getManyCallback) {
        Call<List<Group>> call = iGroup.getGroupByCreator(creator);
        call.enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {

                getManyCallback.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<Group>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.onResponse(null);
            }
        });
    }



    public void getAllGroups(final getAllGroupCallBack getAllGroupCallBack)
    {
        Call<List<Group>> call;
        call = iGroup.getAllGroups();
        call.enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {

                getAllGroupCallBack.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Group>> call, Throwable t) {

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
        public void onResponse(List<Group> groupList);
    }

}

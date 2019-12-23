package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IGroup;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IGroupUser;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

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
    public void getGroupByCreator(String group_name,String status,final getAllGroupCallBack getAllGroupCallBack) {
        Call<List<GroupUser>> call = iGroupUser.getInvitationForGroup(group_name,status);
        call.enqueue(new Callback<List<GroupUser>>() {
            @Override
            public void onResponse(Call<List<GroupUser>> call, Response<List<GroupUser>> response) {

                getAllGroupCallBack.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<GroupUser>> call, Throwable t) {

                t.printStackTrace();

                getAllGroupCallBack.onResponse(null);
            }
        });
    }

    public void getUserGroup(String group_name,String username,final getAllGroupCallBack getAllGroupCallBack) {
        Call<List<GroupUser>> call = iGroupUser.getGroupUser(group_name,username);
        call.enqueue(new Callback<List<GroupUser>>() {
            @Override
            public void onResponse(Call<List<GroupUser>> call, Response<List<GroupUser>> response) {

                getAllGroupCallBack.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<GroupUser>> call, Throwable t) {

                t.printStackTrace();

                getAllGroupCallBack.onResponse(null);
            }
        });
    }

    public void getUserGroupByRoleAndStatus(String group_name,String role,String status,final getAllGroupCallBack getAllGroupCallBack) {
        Call<List<GroupUser>> call = iGroupUser.getGroupUserByRoleAndStatus(group_name,status,role);
        call.enqueue(new Callback<List<GroupUser>>() {
            @Override
            public void onResponse(Call<List<GroupUser>> call, Response<List<GroupUser>> response) {

                getAllGroupCallBack.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<GroupUser>> call, Throwable t) {

                t.printStackTrace();

                getAllGroupCallBack.onResponse(null);
            }
        });
    }


    public void updateUser(GroupUser groupUser,final addingCallback addingCallback)
    {
        Call<JsonObject> call ;
        call = iGroupUser.updateGroupUser(groupUser);
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

    public void updateRoleUser(GroupUser groupUser,final addingCallback addingCallback)
    {
        Call<JsonObject> call ;
        call = iGroupUser.updateRoleGroupUser(groupUser);
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

    public void deleteUser(String  username,String group_name, final addingCallback addingCallback)
    {
        Call<JsonObject> call = iGroupUser.deleteGroupUser(username,group_name);
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
        public void onResponse(List<GroupUser> list);
    }

}

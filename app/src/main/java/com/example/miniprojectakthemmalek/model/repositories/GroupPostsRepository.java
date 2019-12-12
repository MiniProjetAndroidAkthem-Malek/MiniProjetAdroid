package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IGroup;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IGroupPosts;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupPost;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupPostsRepository {



    private static GroupPostsRepository instance;

    public static GroupPostsRepository getInstance(){
        if(instance == null){
            instance = new GroupPostsRepository();
        }
        return instance;
    }


    IGroupPosts iGroupPosts = RetrofitInstance.getRetrofitInstance().create(IGroupPosts.class);


    public void addGrouPost(GroupPost groupPost, final GroupUserRepository.addingCallback callback )
    {

        Call<JsonPrimitive> call ;
        call =  iGroupPosts.addGroupPost(groupPost);
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


    public void getPostsByGroup(String name,final GroupPostsRepository.getAllGroupCallBack getManyCallback) {
        Call<List<Post>> call = iGroupPosts.getPostsByGroup(name);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                getManyCallback.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.onResponse(null);
            }
        });
    }




    public interface addingCallback
    {
        public void addingCallback(int code);
    }

    public interface getAllGroupCallBack
    {
        public void onResponse(List<Post> groupList);
    }
}

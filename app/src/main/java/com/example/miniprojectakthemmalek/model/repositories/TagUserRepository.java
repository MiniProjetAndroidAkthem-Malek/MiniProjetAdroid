package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.ITagPost;
import com.example.miniprojectakthemmalek.model.api.entityInterface.ITagUSer;
import com.example.miniprojectakthemmalek.model.entities.Tag_post;
import com.example.miniprojectakthemmalek.model.entities.Tag_user;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TagUserRepository {

    private static TagUserRepository instance;


    public static TagUserRepository getInstance(){
        if(instance == null){
            instance = new TagUserRepository();
        }
        return instance;
    }

    ITagUSer iTagUser = RetrofitInstance.getRetrofitInstance().create(ITagUSer.class);


    public void addTagUser(Tag_user tag_user, final getLastInsertedCallBack callback )
    {
        Call<JsonPrimitive> call ;

        call =  iTagUser.addTagUser(tag_user);
        call.enqueue(new Callback<JsonPrimitive>() {
            @Override
            public void onResponse(Call<JsonPrimitive> call, Response<JsonPrimitive> response) {

                callback.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<JsonPrimitive> call, Throwable t) {

                t.printStackTrace();

            }
        });

    }






    public void getTagUserByName(String name ,final getAllPostCallBack allPostCallBack)
    {
        Call<List<Tag_user>> call;
        call = iTagUser.getTagByName(name);
        call.enqueue(new Callback<List<Tag_user>>() {
            @Override
            public void onResponse(Call<List<Tag_user>> call, Response<List<Tag_user>> response) {

                allPostCallBack.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Tag_user>> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }
    public void getTagUserById(int id ,final getAllPostCallBack allPostCallBack)
    {
        Call<List<Tag_user>> call;
        call = iTagUser.getTagUserById(id);
        call.enqueue(new Callback<List<Tag_user>>() {
            @Override
            public void onResponse(Call<List<Tag_user>> call, Response<List<Tag_user>> response) {

                allPostCallBack.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Tag_user>> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }



public interface getAllPostCallBack
{
    public void onResponse(List<Tag_user> tagUsers);
}

public interface getLastInsertedCallBack
{
    public void onResponse(JsonPrimitive id);
}

    public interface addingCallback
    {
        public void addingCallback(int code);
    }

    public interface addingPostCallback
    {
        public void addingCallback(Tag_user tag_user);
    }
}

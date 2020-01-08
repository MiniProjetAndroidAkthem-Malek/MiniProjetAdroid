package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IPost;
import com.example.miniprojectakthemmalek.model.api.entityInterface.ITag;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.Tag;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TagRepository {

    private static TagRepository instance;


    public static TagRepository getInstance(){
        if(instance == null){
            instance = new TagRepository();
        }
        return instance;
    }

    ITag iTag = RetrofitInstance.getRetrofitInstance().create(ITag.class);


    public void addTag(Tag tag, final getLastInsertedCallBack callback )
    {
        Call<JsonPrimitive> call ;

        call =  iTag.addTag(tag);
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






    public void getTagByName(String name ,final getAllPostCallBack allPostCallBack)
    {
        Call<List<Tag>> call;
        call = iTag.getTagByName(name);
        call.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {

                allPostCallBack.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }
    public void getTagById(int id ,final getAllPostCallBack allPostCallBack)
    {
        Call<List<Tag>> call;
        call = iTag.getTagById(id);
        call.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {

                allPostCallBack.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }



public interface getAllPostCallBack
{
    public void onResponse(List<Tag> tags);
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
        public void addingCallback(Tag tag);
    }
}

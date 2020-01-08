package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.ITag;
import com.example.miniprojectakthemmalek.model.api.entityInterface.ITagPost;
import com.example.miniprojectakthemmalek.model.entities.Tag;
import com.example.miniprojectakthemmalek.model.entities.Tag_post;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TagPostRepository {

    private static TagPostRepository instance;


    public static TagPostRepository getInstance(){
        if(instance == null){
            instance = new TagPostRepository();
        }
        return instance;
    }

    ITagPost iTagPost = RetrofitInstance.getRetrofitInstance().create(ITagPost.class);


    public void addTagPost(Tag_post tag_post, final getLastInsertedCallBack callback )
    {
        Call<JsonPrimitive> call ;

        call =  iTagPost.addTagPost(tag_post);
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






    public void getTagPostByName(String name ,final getAllPostCallBack allPostCallBack)
    {
        Call<List<Tag_post>> call;
        call = iTagPost.getTagByName(name);
        call.enqueue(new Callback<List<Tag_post>>() {
            @Override
            public void onResponse(Call<List<Tag_post>> call, Response<List<Tag_post>> response) {

                allPostCallBack.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Tag_post>> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }
    public void getTagPostById(int id ,final getAllPostCallBack allPostCallBack)
    {
        Call<List<Tag_post>> call;
        call = iTagPost.getTagByIdPost(id);
        call.enqueue(new Callback<List<Tag_post>>() {
            @Override
            public void onResponse(Call<List<Tag_post>> call, Response<List<Tag_post>> response) {

                allPostCallBack.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Tag_post>> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }



public interface getAllPostCallBack
{
    public void onResponse(List<Tag_post> tagPosts);
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
        public void addingCallback(Tag_post tag_post);
    }
}

package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IPost;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IUser;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {

    private static PostRepository instance;


    public static PostRepository getInstance(){
        if(instance == null){
            instance = new PostRepository();
        }
        return instance;
    }

    IPost iPost = RetrofitInstance.getRetrofitInstance().create(IPost.class);

    public void addPost(Post post, final addingCallback callback )
    {
        Call<JsonPrimitive> call ;

        call =  iPost.addPost(post);
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

    public void getAllPost(final getAllPostCallBack allPostCallBack)
{
    Call<List<Post>> call;
    call = iPost.getAllPosts();
    call.enqueue(new Callback<List<Post>>() {
        @Override
        public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

            allPostCallBack.onResponse(response.body());
        }

        @Override
        public void onFailure(Call<List<Post>> call, Throwable t) {

            t.printStackTrace();
        }
    });

}

public interface getAllPostCallBack
{
    public void onResponse(List<Post> posts);
}

    public interface addingCallback
    {
        public void addingCallback(int code);
    }

}

package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IComment;
import com.example.miniprojectakthemmalek.model.entities.comment;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRepository {

    private static CommentRepository instance;

    public static CommentRepository getInstance(){
        if(instance == null){
            instance = new CommentRepository();
        }
        return instance;
    }

    IComment iComment = RetrofitInstance.getRetrofitInstance().create(IComment.class);

    public void addComment(comment comment, final CommentRepository.addingCallback callback )
    {

        Call<JsonPrimitive> call ;
        call =  iComment.addComment(comment);
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


    public void getPostComments(int id_post,final CommentRepository.getManyCallback getManyCallback) {
        Call<List<comment>> call = iComment.getPostComment(id_post);
        call.enqueue(new Callback<List<comment>>() {
            @Override
            public void onResponse(Call<List<comment>> call, Response<List<comment>> response) {

                getManyCallback.getManyOneFollow(response.body());

            }

            @Override
            public void onFailure(Call<List<comment>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.getManyOneFollow(null);
            }
        });
    }

    public interface addingCallback
    {
        public void addingCallback(int code);
    }



public interface getManyCallback
{
    public void getManyOneFollow(List<comment> like_posts);

}


}

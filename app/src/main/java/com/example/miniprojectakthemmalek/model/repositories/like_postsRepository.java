package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.Ilike_posts;
import com.example.miniprojectakthemmalek.model.entities.like_posts;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class like_postsRepository {

    private static like_postsRepository instance;

    public static like_postsRepository getInstance(){
        if(instance == null){
            instance = new like_postsRepository();
        }
        return instance;
    }


    Ilike_posts ilike_posts = RetrofitInstance.getRetrofitInstance().create(Ilike_posts.class);


    public void addlike_posts(like_posts like_posts, final like_postsRepository.addingCallback callback )
    {

        Call<JsonPrimitive> call ;
        call =  ilike_posts.addLike(like_posts);
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

public void dislike(String  username,int id_post, final like_postsRepository.deletingCallback callback )
    {

        Call<JsonPrimitive> call = ilike_posts.Dislike(username,id_post);
        call.enqueue(new Callback<JsonPrimitive>() {
            @Override
            public void onResponse(Call<JsonPrimitive> call, Response<JsonPrimitive> response) {

                System.out.println(response.message());
                callback.deletingCallback(response.code());

            }

            @Override
            public void onFailure(Call<JsonPrimitive> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }




    public void getPostLikes(int id_post,final like_postsRepository.getManyCallback getManyCallback) {
        Call<List<like_posts>> call = ilike_posts.getPostLikes(id_post);
        call.enqueue(new Callback<List<like_posts>>() {
            @Override
            public void onResponse(Call<List<like_posts>> call, Response<List<like_posts>> response) {

                getManyCallback.getManyOneFollow(response.body());

            }

            @Override
            public void onFailure(Call<List<like_posts>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.getManyOneFollow(null);
            }
        });
    }


    public void getPostLikesByUsernameAndId(int id_post,String username,final like_postsRepository.getManyCallback getManyCallback) {
        Call<List<like_posts>> call = ilike_posts.getPostLikesByUsernameAndId(id_post,username);
        call.enqueue(new Callback<List<like_posts>>() {
            @Override
            public void onResponse(Call<List<like_posts>> call, Response<List<like_posts>> response) {

                getManyCallback.getManyOneFollow(response.body());

            }

            @Override
            public void onFailure(Call<List<like_posts>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.getManyOneFollow(null);
            }
        });
    }



    public interface addingCallback
    {
        public void addingCallback(int code);
    }

public interface deletingCallback
{
    public void deletingCallback(int code);
}

public interface getManyCallback
{
    public void getManyOneFollow(List<like_posts> like_posts);

}


}

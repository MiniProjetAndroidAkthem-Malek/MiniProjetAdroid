package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IFollow;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IPost;
import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowRepository {

    private static FollowRepository instance;

    public static FollowRepository getInstance(){
        if(instance == null){
            instance = new FollowRepository();
        }
        return instance;
    }

    IFollow iFollow = RetrofitInstance.getRetrofitInstance().create(IFollow.class);

    public void addFollow(Follow follow, final FollowRepository.addingCallback callback )
    {

        Call<JsonPrimitive> call ;
        call =  iFollow.addFollow(follow);
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

public void deleteUser(String  username,String following, final FollowRepository.deletingCallback callback )
    {

        Call<JsonPrimitive> call = iFollow.deleteFollow(username,following);
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

    public void getOneFollow(String username,String following,final FollowRepository.getOneCallback getOneCallback) {
        Call<List<Follow>> call = iFollow.getFollower(username,following);
        call.enqueue(new Callback<List<Follow>>() {
            @Override
            public void onResponse(Call<List<Follow>> call, Response<List<Follow>> response) {

                if(response.body().isEmpty())
                {
                    getOneCallback.getOneFollow(null);
                }else{

                    getOneCallback.getOneFollow(response.body().get(0));

                }

            }

            @Override
            public void onFailure(Call<List<Follow>> call, Throwable t) {

                t.printStackTrace();

                getOneCallback.getOneFollow(null);
            }
        });
    }


    public void getWhatFollows(String username,final FollowRepository.getManyCallback getManyCallback) {
        Call<List<Follow>> call = iFollow.getWhatFollows(username);
        call.enqueue(new Callback<List<Follow>>() {
            @Override
            public void onResponse(Call<List<Follow>> call, Response<List<Follow>> response) {

                    getManyCallback.getManyOneFollow(response.body());

            }

            @Override
            public void onFailure(Call<List<Follow>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.getManyOneFollow(null);
            }
        });
    }

    public void getFollowing(String following,final FollowRepository.getManyCallback getManyCallback) {
        Call<List<Follow>> call = iFollow.getFollowing(following);
        call.enqueue(new Callback<List<Follow>>() {
            @Override
            public void onResponse(Call<List<Follow>> call, Response<List<Follow>> response) {

                getManyCallback.getManyOneFollow(response.body());

            }

            @Override
            public void onFailure(Call<List<Follow>> call, Throwable t) {

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

public interface getOneCallback
{
    public void getOneFollow(Follow follow);

}

public interface getManyCallback
{
    public void getManyOneFollow(List<Follow> follow);

}


}

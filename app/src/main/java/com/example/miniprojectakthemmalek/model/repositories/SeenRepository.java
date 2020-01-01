package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IPost;
import com.example.miniprojectakthemmalek.model.api.entityInterface.ISeen;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.Seen;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeenRepository {

    private static SeenRepository instance;


    public static SeenRepository getInstance(){
        if(instance == null){
            instance = new SeenRepository();
        }
        return instance;
    }

    ISeen iSeen = RetrofitInstance.getRetrofitInstance().create(ISeen.class);


    public void addSeen(Seen seen, final addingCallback callback )
    {
        Call<JsonPrimitive> call ;

        call =  iSeen.addSeen(seen);
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


    public void getAllByMessageUsername(int id_message,String username,final getAll callBack)
    {
        Call<List<Seen>> call;
        call = iSeen.getSeenByIdMessageAndUsername(id_message,username);
        call.enqueue(new Callback<List<Seen>>() {
            @Override
            public void onResponse(Call<List<Seen>> call, Response<List<Seen>> response) {

                callBack.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Seen>> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }




    public interface addingCallback
    {

        public void addingCallback(int code);

    }

    public interface addingPostCallback
    {
        public void addingCallback(Post post);
    }

    public interface getAll
    {
        public void onResponse(List<Seen> seens);
    }

}

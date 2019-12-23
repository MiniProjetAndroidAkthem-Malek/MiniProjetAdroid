package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IMessage;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IPost;
import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Message;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageRepository {


    private static MessageRepository instance;


    public static MessageRepository getInstance(){
        if(instance == null){
            instance = new MessageRepository();
        }
        return instance;
    }

    IMessage iMessage = RetrofitInstance.getRetrofitInstance().create(IMessage.class);




    public void addFollow(Message message, final FollowRepository.addingCallback callback )
    {

        Call<JsonPrimitive> call ;
        call =  iMessage.addMessage(message);
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

    public void getMessages(final String sender, final String receiver, final MessageRepository.getManyCallback getManyCallback) {
        Call<List<Message>> call = iMessage.getAllMessageOf(sender,receiver);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {

                if(response.body().isEmpty())
                {

                      getManyCallback.getManyOneFollow(null);

                }else{

                    getManyCallback.getManyOneFollow(response.body());

                }

            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.getManyOneFollow(null);
            }
        });
    }


    public void getMessages(String username,final MessageRepository.getManyCallback getManyCallback) {
        Call<List<Message>> call = iMessage.getAllMessagesOf(username);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {

                if(response.body().isEmpty())
                {
                    getManyCallback.getManyOneFollow(null);
                }else{

                    getManyCallback.getManyOneFollow(response.body());

                }

            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

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
        public void getManyOneFollow(List<Message> messages);

    }
}

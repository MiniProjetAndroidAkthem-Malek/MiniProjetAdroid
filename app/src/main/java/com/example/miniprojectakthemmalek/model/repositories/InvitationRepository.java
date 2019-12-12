package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IFollow;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IInvitation;
import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Invitation;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvitationRepository {



    private static InvitationRepository instance;

    public static InvitationRepository getInstance(){
        if(instance == null){
            instance = new InvitationRepository();
        }
        return instance;
    }

    IInvitation iInvitation = RetrofitInstance.getRetrofitInstance().create(IInvitation.class);

    public void addInvitation(Invitation invitation, final InvitationRepository.addingCallback callback )
    {

        Call<JsonPrimitive> call ;
        call =  iInvitation.addInvitation(invitation);
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


    public void getSended(String sender,final InvitationRepository.getManyCallback getManyCallback) {
        Call<List<Invitation>> call = iInvitation.getSendedInvitationForUser(sender);
        call.enqueue(new Callback<List<Invitation>>() {
            @Override
            public void onResponse(Call<List<Invitation>> call, Response<List<Invitation>> response) {

                getManyCallback.getMany(response.body());

            }

            @Override
            public void onFailure(Call<List<Invitation>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.getMany(null);
            }
        });
    }


    public void getReceivedForUser(String receiver,final InvitationRepository.getManyCallback getManyCallback) {
        Call<List<Invitation>> call = iInvitation.getReceivedInvitationForUser(receiver);
        call.enqueue(new Callback<List<Invitation>>() {
            @Override
            public void onResponse(Call<List<Invitation>> call, Response<List<Invitation>> response) {

                getManyCallback.getMany(response.body());

            }

            @Override
            public void onFailure(Call<List<Invitation>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.getMany(null);
            }
        });
    }


    public void getInvitationForUser(String sender,String receiver,final InvitationRepository.getManyCallback getManyCallback) {
        Call<List<Invitation>> call = iInvitation.getInvitationForUser(sender,receiver);
        call.enqueue(new Callback<List<Invitation>>() {
            @Override
            public void onResponse(Call<List<Invitation>> call, Response<List<Invitation>> response) {

                getManyCallback.getMany(response.body());

            }

            @Override
            public void onFailure(Call<List<Invitation>> call, Throwable t) {

                t.printStackTrace();

                getManyCallback.getMany(null);
            }
        });
    }

    public void deleteInvitionFor(String receiver,String sender,final InvitationRepository.addingCallback addingCallback) {
        Call<JsonObject> call = iInvitation.deleteInvitation(receiver,sender);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(  Call<JsonObject>  call, Response<JsonObject> response) {

addingCallback.addingCallback(response.code());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                t.printStackTrace();

                addingCallback.addingCallback(0);


            }
        });
    }


public interface addingCallback
{
    public void addingCallback(int code);
}

public interface getManyCallback
{
    public void getMany(List<Invitation> invitationList);

}


}

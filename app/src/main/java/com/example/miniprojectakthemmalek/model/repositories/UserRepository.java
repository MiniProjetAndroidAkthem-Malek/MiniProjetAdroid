package com.example.miniprojectakthemmalek.model.repositories;

import com.example.miniprojectakthemmalek.interfacesUseCase.ILogin;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IUser;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository implements ILogin.model {


    private static UserRepository instance;


    public static UserRepository getInstance(){
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }



    IUser iUser = RetrofitInstance.getRetrofitInstance().create(IUser.class);


public void addUser(User user, final addingCallback callback )
{
    Call<JsonPrimitive> call ;

    call = iUser.addUser(user);
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
public void getAllUsers(final getAllUserCallBack getAllUserCallBack)
{
Call<List<User>> call;
call = iUser.getAllUsers();
call.enqueue(new Callback<List<User>>() {
    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {

        getAllUserCallBack.onResponse(response.body());
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {

        t.printStackTrace();
    }
});

}
    @Override
    public void getOneUser(String username, final getOneUserCallBack callBack) {
        Call<List<User>> call = iUser.getOneUser(username);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if(response.body().isEmpty())
                {
                    callBack.onResponse(null);
                }else{
                    callBack.onResponse(response.body().get(0));
                    //System.out.println(response.body().get(0));
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
                callBack.onResponse(null);
            }
        });
    }


    public void updateUser(User user)
    {
        Call<JsonObject> call ;
        call = iUser.updateUser(user);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }

public void deleteUser(String  username)
{
    Call<JsonObject> call = iUser.deleteUser(username);
    call.enqueue(new Callback<JsonObject>() {
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {
            t.printStackTrace();
        }
    });

}


public void uploadPhotos(File photo,String username)
{
    RequestBody requestImage = RequestBody.create(MediaType.parse("image"), photo);
    MultipartBody.Part body = MultipartBody.Part.createFormData("image", photo.getName(), requestImage);
    Call<String> call = iUser.updatePhoto(body,
            RequestBody.create(MediaType.parse("text/plain"),username));

    call.enqueue(new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            System.out.println(response.body());
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {

            t.printStackTrace();

        }
    });

}

public interface getOneUserCallBack
{
    public void onResponse(User user);
}

public interface getAllUserCallBack
{
    public void onResponse(List<User> user);
}

public interface addingCallback
{
    public void addingCallback(int code);
}

}










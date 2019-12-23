package com.example.miniprojectakthemmalek.model.repositories;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.JsonReader;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IFollow;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IImage;
import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.google.gson.JsonPrimitive;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {


    private static ImageRepository instance;

    public static ImageRepository getInstance(){
        if(instance == null){
            instance = new ImageRepository();
        }
        return instance;
    }

    IImage iImage = RetrofitInstance.getRetrofitInstance().create(IImage.class);

    public void uploadPhotos(MultipartBody.Part body, RequestBody name)
    {
        Call<ResponseBody> call = iImage.addImage(body,name);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                t.printStackTrace();

            }
        });

    }


public void loadPicutreOf(String username,final float width_scale,final float height_scale,final getPictureCallBack getPictureCallBack)
{
    Call<ResponseBody> call = iImage.getPictureOf(username);
    call.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    // display the image data in a ImageView or save it
                    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                   Bitmap resizedBmp = Bitmap.createScaledBitmap(bmp,(int)(bmp.getWidth()*width_scale), (int)(bmp.getHeight()*height_scale), true);

                    getPictureCallBack.onResponse(resizedBmp);

                } else {
                    getPictureCallBack.onResponse(null);
                }
            } else {

            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
        t.printStackTrace();
       }
    });
}


    public interface addingCallback
    {
        public void addingCallback(int code);
    }


    public interface getPictureCallBack
    {
        public void onResponse(Bitmap picBitmap);
    }
}

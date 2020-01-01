package com.example.miniprojectakthemmalek.model.repositories.NotificationRepositories;

import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IGroup;
import com.example.miniprojectakthemmalek.model.api.entityInterface.INotification;
import com.example.miniprojectakthemmalek.model.entities.NotificationEntities.RequestNotification;
import com.example.miniprojectakthemmalek.model.entities.NotificationEntities.SendNotificationModel;
import com.example.miniprojectakthemmalek.model.repositories.GroupRepository;

import java.sql.SQLOutput;

import okhttp3.ResponseBody;
import retrofit2.Callback;

public class NotificationRepository {

    private static NotificationRepository instance;

    public static NotificationRepository getInstance(){
        if(instance == null){
            instance = new NotificationRepository();
        }
        return instance;
    }


    INotification iNotification = RetrofitInstance.getNotificationRetrofitInstance().create(INotification.class);

    public void sendNotificationToPatner(String body,String title,String to) {

        SendNotificationModel sendNotificationModel = new SendNotificationModel();
        sendNotificationModel.setTitle(title);
        sendNotificationModel.setText(body);
        RequestNotification requestNotificaton = new RequestNotification();
        requestNotificaton.setSendNotificationModel(sendNotificationModel);

        requestNotificaton.setTo("/topics/"+to);


        retrofit2.Call<ResponseBody> responseBodyCall = iNotification.sendChatNotification(requestNotificaton);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

           //     Log.d("kkkk","done");
                System.out.println(response.code());
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }
}

package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.NotificationEntities.RequestNotification;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface INotification {


@Headers({"Authorization: key=AAAAFgZOW1w:APA91bG4NIJguYV27BlA0QSYUnFuuCzYxrhgllLxRo4f80kOApnCxqJf-Q9ZuVJ5Kc-0_8se4w0BNbPBYVSLgBhP1eFtGwm5W_R4qJ5vrin4SDYmJ3-t3z7-C4qlyqeOPVVDviYOxi2E",
        "Content-Type:application/json"})
@POST("fcm/send")
Call<ResponseBody> sendChatNotification(@Body RequestNotification requestNotificaton);
}

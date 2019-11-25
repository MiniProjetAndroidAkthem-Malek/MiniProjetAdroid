package com.example.miniprojectakthemmalek.view;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class ServiceApp extends Service {

SessionManager sessionManager;
String username;
@Nullable
    @Override
    public IBinder onBind(Intent intent) {
    return null;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



    username = intent.getStringExtra("username");

        return super.onStartCommand(intent, flags, startId);
  }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        System.out.println("App did closed --> "+username);

        sessionManager=new SessionManager(getApplicationContext());
        sessionManager.updateConnectionStatusForUser(username,0);

    }
}

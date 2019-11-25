package com.example.miniprojectakthemmalek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;

import com.example.miniprojectakthemmalek.presenter.ProfilePresenter;

public class MainActivity extends AppCompatActivity  {


ProfilePresenter profilePresenter;

    String URL="http://192.168.1.7:3003/users/getAll";
    String POST_URL="http://192.168.1.7:3003/users/add";
    String PUT_URL="http://192.168.1.7:3003/users/update";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_purple);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        //call.execute();

    }




}

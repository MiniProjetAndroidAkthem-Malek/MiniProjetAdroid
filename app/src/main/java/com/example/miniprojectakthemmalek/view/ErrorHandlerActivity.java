package com.example.miniprojectakthemmalek.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectakthemmalek.R;

public class ErrorHandlerActivity extends AppCompatActivity {

    LinearLayout retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_item_internet_icon);

    retry= findViewById(R.id.retry);
    retry.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent=new Intent(getApplicationContext(),AuthentificationActivity.class);
            startActivity(intent);
        }
    });



    }







}

package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.miniprojectakthemmalek.R;

public class NoInternet extends AppCompatActivity {
AppCompatButton retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_item_internet_image);
retry = findViewById(R.id.retry);
retry.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intentHome =new Intent(getApplicationContext(), AuthentificationActivity.class);
        startActivity(intentHome);
    }
});
    }
}

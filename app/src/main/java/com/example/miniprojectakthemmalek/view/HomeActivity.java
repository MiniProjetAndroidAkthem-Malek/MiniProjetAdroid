package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.User;

public class HomeActivity extends AppCompatActivity {

    Button movetoprofile;
    SessionManager sessionManager;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_feed);
        movetoprofile=findViewById(R.id.movetoprofile);
       sessionManager=new SessionManager(this);
      System.out.println("home="+sessionManager.getAllUsers());

        if(getIntent().hasExtra("username"))
        {
            user = sessionManager.getUser(getIntent().getStringExtra("username"),1);
        }
      movetoprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent =new Intent(getApplicationContext(),ProfileActivity.class);
                intent.putExtra("username",user.getUsername());
                startActivity(intent);


            }
        });

    }


}

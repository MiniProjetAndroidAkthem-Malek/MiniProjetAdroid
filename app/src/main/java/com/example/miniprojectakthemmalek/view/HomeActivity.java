package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.view.fragments.AddPostFragment;
import com.example.miniprojectakthemmalek.view.fragments.PostsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;


public class HomeActivity extends AppCompatActivity {

    Button movetoprofile;
    SessionManager sessionManager;
    User user;
    Toolbar toolbar;
    public void initStyle()
    {

            if(user.getTheme_r()!=0)
            {
                int rgb = Color.rgb(user.getTheme_r(),user.getTheme_g(),user.getTheme_b());

                toolbar.setBackgroundColor(rgb);

            }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_feed);

        movetoprofile=findViewById(R.id.movetoprofile);
        toolbar=findViewById(R.id.toolbar);
       sessionManager=new SessionManager(this);
        if(getIntent().hasExtra("username"))
        {
            user = sessionManager.getUser(getIntent().getStringExtra("username"),1);
        }
     PostsFragment postsFragment =    new PostsFragment();
       Bundle bundle = new Bundle();
       bundle.putString("username",user.getUsername().toString());
       postsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameHome,postsFragment).commit();

      System.out.println("home="+sessionManager.getAllUsers());



        initStyle();



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

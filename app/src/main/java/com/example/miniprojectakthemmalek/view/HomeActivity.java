package com.example.miniprojectakthemmalek.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.view.fragments.PostsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.sql.SQLOutput;


public class HomeActivity extends AppCompatActivity  {

    Button movetoprofile;
    SessionManager sessionManager;
    User user;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView connectedUserName,connectedUserJob;
    FloatingActionButton notifications;
    Animation slideDown;
    TextView inbox_nums;

    NotificationManagerCompat notificationManagerCompat;

    Spinner spinner;
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


        notificationManagerCompat = NotificationManagerCompat.from(this);

        slideDown =  AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);

        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.nav_view);
        //notifications=findViewById(R.id.notifications);
        View headerView = navigationView.getHeaderView(0);
        TextView connectedUserName = headerView.findViewById(R.id.connectedUserName);
        TextView connectedUserJob = headerView.findViewById(R.id.connectedUserJob);
        inbox_nums = findViewById(R.id.inbox_nums);

        sessionManager=new SessionManager(this);
        if(getIntent().hasExtra("username"))
        {
            user = sessionManager.getUser(getIntent().getStringExtra("username"),1);
        }


        FirebaseMessaging.getInstance().subscribeToTopic(user.getUsername().toString());

        connectedUserName.setText(user.getUsername());
        connectedUserJob.setText(user.getJob());
        PostsFragment postsFragment = new PostsFragment();
       Bundle bundle = new Bundle();
       bundle.putString("username",user.getUsername().toString());
       postsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameHome,postsFragment).commit();

      initStyle();

      navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent =new Intent(getApplicationContext(),ProfileActivity.class);
              intent.putExtra("username",user.getUsername());
              startActivity(intent);
          }
      });


      navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id= menuItem.getItemId();
        switch (id)
        {
            case R.id.nav_all_inbox:
            {
                Intent intent =new Intent(getApplicationContext(),ChatActivity.class);
                intent.putExtra("connectedUsername",user.getUsername());
                intent.putExtra("redirect",2);
                startActivity(intent);
                break;
            }
            case R.id.nav_group:
            {
                Intent intent =new Intent(getApplicationContext(),GroupActivity.class);
                intent.putExtra("username",user.getUsername());
                startActivity(intent);
               break;
            }
            case R.id.nav_all_groups:
            {
                Intent intent =new Intent(getApplicationContext(),ShowGroupsActivity.class);
                intent.putExtra("username",user.getUsername());
                startActivity(intent);
              break;
            }
            case R.id.nav_starred:
            {
                Intent intent =new Intent(getApplicationContext(),InvitationActivity.class);
                intent.putExtra("username",user.getUsername());
                startActivity(intent);
                break;
            }

        }

        return true;

          }


      });


       // LocalNotification.getInstance().createSocket();
      //  LocalNotification.getInstance().receiveNotification(this,counter_fab,slideDown);

    }



}

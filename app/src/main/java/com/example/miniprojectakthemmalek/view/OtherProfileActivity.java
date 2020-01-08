package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Activities;
import com.example.miniprojectakthemmalek.model.entities.Enums.ActivityType;
import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.ActivitiesRepository;
import com.example.miniprojectakthemmalek.model.repositories.FollowRepository;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.fragments.ActivityFragment;
import com.example.miniprojectakthemmalek.view.fragments.ChildrenHomeFragment;
import com.example.miniprojectakthemmalek.view.fragments.OtherProfileFragment;
import com.example.miniprojectakthemmalek.view.fragments.ProfileFragment;
import com.google.android.material.tabs.TabLayout;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OtherProfileActivity extends AppCompatActivity {


    String username;
    String connectedUsername;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        tabLayout=findViewById(R.id.tabLayout);

        username=getIntent().getStringExtra("username");
        connectedUsername=getIntent().getStringExtra("ConnectedUsername");


        OtherProfileFragment otherProfileFragment=new OtherProfileFragment();
        Bundle bundle=new Bundle();
        bundle.putString("username",username);
        bundle.putString("ConnectedUsername",connectedUsername);
        otherProfileFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameProfile,otherProfileFragment).commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition()==0)
                {

                    OtherProfileFragment otherProfileFragment=new OtherProfileFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("username",username);
                    bundle.putString("ConnectedUsername",connectedUsername);
                    otherProfileFragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction().replace(R.id.frameProfile,otherProfileFragment).commit();


                }else if(tab.getPosition()==1)
                {

                  ActivityFragment activityFragment=new ActivityFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("username",username);
                    activityFragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction().replace(R.id.frameProfile,activityFragment).commit();


                }else if (tab.getPosition()==2){

                    ChildrenHomeFragment childrenHomeFragment=new ChildrenHomeFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("username",username);
                    bundle.putString("connectedUsername",connectedUsername);
                    childrenHomeFragment.setArguments(bundle);


                    getSupportFragmentManager().beginTransaction().replace(R.id.frameProfile,childrenHomeFragment).commit();

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }




}

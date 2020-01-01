package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.view.fragments.ActivityFragment;
import com.example.miniprojectakthemmalek.view.fragments.ChildrenHomeFragment;
import com.example.miniprojectakthemmalek.view.fragments.ProfileFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ProfileActivity extends AppCompatActivity {

    SessionManager sessionManager;
    User user;
    TabItem MoveToProfile,MoveToActivity;
    TabLayout tabLayout;

    TextView firstNameLabel,lastNameLabel,addressLabel,phoneNumberLabel,usernameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_purple);
        tabLayout=findViewById(R.id.tabLayout);

        sessionManager = new SessionManager(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if(getIntent().hasExtra("username"))
        {
            user = sessionManager.getUser(getIntent().getStringExtra("username"),1);
        }

        ProfileFragment profileFragment=new ProfileFragment();
        Bundle bundle=new Bundle();
        bundle.putString("username",user.getUsername());
        profileFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameProfile,profileFragment).commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition()==0)
                {

                    ProfileFragment profileFragment=new ProfileFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("username",user.getUsername());
                    profileFragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction().replace(R.id.frameProfile,profileFragment).commit();


                }else if(tab.getPosition()==1)
                {

                    ActivityFragment activityFragment=new ActivityFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("username",user.getUsername());
                    activityFragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction().replace(R.id.frameProfile,activityFragment).commit();


                }else if (tab.getPosition()==2){

                    ChildrenHomeFragment childrenHomeFragment=new ChildrenHomeFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("username",user.getUsername());
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

       /* firstNameLabel=findViewById(R.id.firstNameLabel);
        lastNameLabel=findViewById(R.id.lastNameLabel);
        addressLabel=findViewById(R.id.addressLabel);
        phoneNumberLabel=findViewById(R.id.phoneNumberLabel);
        usernameLabel=findViewById(R.id.usernameLabel);

        firstNameLabel.setText(user.getFirst_name());
        lastNameLabel.setText(user.getLast_name());
        addressLabel.setText(user.getAddress());
        phoneNumberLabel.setText(user.getPhone_number().toString());
        usernameLabel.setText(user.getUsername());*/

}


}

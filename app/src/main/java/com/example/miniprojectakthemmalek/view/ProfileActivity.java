package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.view.fragments.ProfileFragment;

public class ProfileActivity extends AppCompatActivity {

    SessionManager sessionManager;
    User user;

    TextView firstNameLabel,lastNameLabel,addressLabel,phoneNumberLabel,usernameLabel;
    LinearLayout FollowingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_purple);
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

        FollowingLayout=findViewById(R.id.FollowingLayout);
       // FollowingLayout.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View v) {
       //        Intent intent =new Intent(getApplicationContext(),ShowFriendsActivity.class);
      //          intent.putExtra("username",user.getUsername());

    //            startActivity(intent);
     //       }
    //    });



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

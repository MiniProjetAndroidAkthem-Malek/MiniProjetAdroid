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
import com.mikhaellopez.circularimageview.CircularImageView;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class OtherProfileActivity extends AppCompatActivity {

    String username,connectedUsername;
    TextView followers,partnerLabel,birth_date,jobProfile, following, postsLabel,first_name_label,last_name_label,address_label,phone_number_label,birth_date_label,partner_label,firstNameLabel,lastNameLabel,addressLabel,phoneNumberLabel,usernameLabel,textView11,textView12;
    AppCompatButton followBtn,chatBtn;
    LinearLayout layoutLinear;
    CircularImageView image;
    int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);

        username=getIntent().getStringExtra("username");
        connectedUsername=getIntent().getStringExtra("ConnectedUsername");

        firstNameLabel=findViewById(R.id.firstNameLabel);
        lastNameLabel=findViewById(R.id.lastNameLabel);
        addressLabel=findViewById(R.id.addressLabel);
        phoneNumberLabel=findViewById(R.id.phoneNumberLabel);
        usernameLabel=findViewById(R.id.usernameLabel);
        followBtn=findViewById(R.id.followBtn);
        layoutLinear=findViewById(R.id.layoutLinear);
        following=findViewById(R.id.following);
        followers=findViewById(R.id.followers);
        postsLabel=findViewById(R.id.postsLabel);
        chatBtn=findViewById(R.id.chatBtn);
        partnerLabel=findViewById(R.id.partnerLabel);
        birth_date=findViewById(R.id.birth_date);
        jobProfile=findViewById(R.id.jobProfile);
        image=findViewById(R.id.image);

        updateNumberOfFollows();
        initFollowBtn();

        PostRepository.getInstance().getAllPostOf(username, new PostRepository.getAllPostCallBack() {
            @Override
            public void onResponse(List<Post> posts) {
                postsLabel.setText(""+posts.size());
            }
        });


         followBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFollowBtn();
            }
        });

        UserRepository.getInstance().getOneUser(username, new UserRepository.getOneUserCallBack() {
            @Override
            public void onResponse(User user) {


                usernameLabel.setText(user.getUsername());

                if(user.getFirst_name()==null || user.getFirst_name()=="" )
                {
                    firstNameLabel.setText("");
                }else
                {
                    firstNameLabel.setText(user.getFirst_name());
                }


                if(user.getLast_name()==null || user.getLast_name()=="")
                {
                    lastNameLabel.setText("");

                }else
                {
                    lastNameLabel.setText(user.getLast_name());
                }


                if(user.getJob()==null || user.getJob()=="")
                {
                    jobProfile.setText("");

                }else
                {
                    jobProfile.setText(user.getJob());
                }

                if(user.getAddress()==null || user.getAddress()=="")
                {
                    addressLabel.setText("");

                }else
                {
                    addressLabel.setText(user.getAddress());
                }

                if(user.getPhone_number()==null || user.getPhone_number()==0)
                {

                    phoneNumberLabel.setText("");

                }else{

                    phoneNumberLabel.setText(user.getPhone_number().toString());

                }

                if(user.getPartner()==null || user.getPartner()=="")
                {
                    partnerLabel.setText("");

                }else {

                    partnerLabel.setText(user.getPartner());
                }

                if(user.getBirth_date()==null || user.getBirth_date()=="")
                {
                    birth_date.setText("");
                }else {
                    birth_date.setText(user.getBirth_date());
                }


            }
        });


        ImageRepository.getInstance().loadPicutreOf(username,0.5f,0.5f, new ImageRepository.getPictureCallBack() {
            @Override
            public void onResponse(Bitmap picUrl) {
                if(picUrl==null)
                {
                    image.setImageResource(R.drawable.default_avatar);

                }else{
                    image.setImageBitmap(picUrl);
                }
            }
        });


chatBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(getApplicationContext(),ChatActivity.class);

        intent.putExtra("username",username);
        intent.putExtra("redirect",1);
        intent.putExtra("ConnectedUsername",connectedUsername);
        startActivity(intent);

    }
});



    }


    public void updateNumberOfFollows()
    {

        FollowRepository.getInstance().getWhatFollows(username, new FollowRepository.getManyCallback() {
            @Override
            public void getManyOneFollow(List<Follow> follow) {
                following.setText(""+follow.size());
            }
        });

        FollowRepository.getInstance().getFollowing(username, new FollowRepository.getManyCallback() {
            @Override
            public void getManyOneFollow(List<Follow> follow) {
                followers.setText(""+follow.size());
            }
        });
    }

    public AppCompatButton showFollowBtn()
    {
        AppCompatButton appCompatButton=(AppCompatButton) layoutLinear.getChildAt(1);
        appCompatButton.setText("Follow");
        chatBtn.setVisibility(View.INVISIBLE);

        return appCompatButton;
    }

    public AppCompatButton showUnFollowBtn()
    {

        AppCompatButton appCompatButton=(AppCompatButton) layoutLinear.getChildAt(1);
        appCompatButton.setText("Unfollow");
        chatBtn.setVisibility(View.VISIBLE);

        return appCompatButton;
    }


    public void initFollowBtn()
    {
        AppCompatButton appCompatButton=(AppCompatButton) layoutLinear.getChildAt(1);
        appCompatButton.setText("Unfollow");

        FollowRepository.getInstance().getOneFollow(connectedUsername, username, new FollowRepository.getOneCallback() {
            @Override
            public void getOneFollow(Follow follow) {

                if(follow==null)
                {
                    x=1;
                    showFollowBtn();

                }else
                {
                    x=2;
                    showUnFollowBtn();
                }

            }
        });


    }

    public void updateFollowBtn()
    {
    x++;

        if(x%2==0)
        {
            showUnFollowBtn();

            FollowRepository.getInstance().getOneFollow(connectedUsername, username, new FollowRepository.getOneCallback() {
                @Override
                public void getOneFollow(Follow follow) {
                    if(follow==null)
                    {
                        FollowRepository.getInstance().addFollow(new Follow(connectedUsername, username), new FollowRepository.addingCallback() {
                            @Override
                            public void addingCallback(int code) {
                                if(code==200)
                                {

                                    updateNumberOfFollows();

                                    Date date= new Date(System.currentTimeMillis());
                                    long time = date. getTime();
                                    Timestamp ts = new Timestamp(time);


                                    ActivitiesRepository.getInstance().addActivities(new Activities(connectedUsername, username, ActivityType.FOLLOW), new ActivitiesRepository.addingCallback() {
                                        @Override
                                        public void addingCallback(int code) {

                                        }
                                    });

                                }
                            }
                        });
                    }
                }
            });

        }else{

            showFollowBtn();

FollowRepository.getInstance().deleteUser(connectedUsername, username, new FollowRepository.deletingCallback() {
    @Override
    public void deletingCallback(int code) {
        if(code==200)
        {
            updateNumberOfFollows();
        }

    }
});

        }


    }

}

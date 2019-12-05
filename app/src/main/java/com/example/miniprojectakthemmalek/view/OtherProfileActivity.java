package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.FollowRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;

import java.util.List;

public class OtherProfileActivity extends AppCompatActivity {

    String username,connectedUsername;
    TextView followers, following, postsLabel,first_name_label,last_name_label,address_label,phone_number_label,birth_date_label,partner_label,firstNameLabel,lastNameLabel,addressLabel,phoneNumberLabel,usernameLabel,textView11,textView12;
    AppCompatButton followBtn,chatBtn;
    LinearLayout layoutLinear;
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
                usernameLabel.setText(user.getUsername().toString());
                firstNameLabel.setText(user.getFirst_name().toString());
                lastNameLabel.setText(user.getLast_name().toString());
                addressLabel.setText(user.getAddress().toString());
                phoneNumberLabel.setText(user.getPhone_number().toString());
            }
        });


chatBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),ChatActivity.class);
        intent.putExtra("username",username);
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

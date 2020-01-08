package com.example.miniprojectakthemmalek.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.miniprojectakthemmalek.view.ChatActivity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class OtherProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String username,connectedUsername;
    TextView followers,partnerLabel,birth_date,jobProfile, following, postsLabel,first_name_label,last_name_label,address_label,phone_number_label,birth_date_label,partner_label,firstNameLabel,lastNameLabel,addressLabel,phoneNumberLabel,usernameLabel,textView11,textView12;
    AppCompatButton followBtn,chatBtn;
    LinearLayout layoutLinear;
    CircleImageView image;
    int x=0;

    public OtherProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OtherProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OtherProfileFragment newInstance(String param1, String param2) {
        OtherProfileFragment fragment = new OtherProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_other_profile, container, false);

        username=getArguments().getString("username");
        connectedUsername=getArguments().getString("ConnectedUsername");

        firstNameLabel=rootView.findViewById(R.id.firstNameLabel);
        lastNameLabel=rootView.findViewById(R.id.lastNameLabel);
        addressLabel=rootView.findViewById(R.id.addressLabel);
        phoneNumberLabel=rootView.findViewById(R.id.phoneNumberLabel);
        usernameLabel=rootView.findViewById(R.id.usernameLabel);
        followBtn=rootView.findViewById(R.id.followBtn);
        layoutLinear=rootView.findViewById(R.id.layoutLinear);
        following=rootView.findViewById(R.id.following);
        followers=rootView.findViewById(R.id.followers);
        postsLabel=rootView.findViewById(R.id.postsLabel);
        chatBtn=rootView.findViewById(R.id.chatBtn);
        partnerLabel=rootView.findViewById(R.id.partnerLabel);
        birth_date=rootView.findViewById(R.id.birth_date);
        jobProfile=rootView.findViewById(R.id.jobProfile);
        image=rootView.findViewById(R.id.image);

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

                Intent intent=new Intent(getContext(), ChatActivity.class);

                intent.putExtra("username",username);
                intent.putExtra("redirect",1);
                intent.putExtra("ConnectedUsername",connectedUsername);
                startActivity(intent);

            }
        });
        return rootView;


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

package com.example.miniprojectakthemmalek.view.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.FollowRepository;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.view.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SessionManager sessionManager;
    User user;
    Toolbar toolbar;
    CircleImageView image;
    TextView jobProfile,birth_date, partnerLabel, postsLabel,first_name_label,last_name_label,address_label,phone_number_label,birth_date_label,partner_label,firstNameLabel,lastNameLabel,addressLabel,phoneNumberLabel,usernameLabel,textView11,textView12;
    FloatingActionButton settingBtn;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    public void initStyle()
    {
        if(user.getTheme_r()!=0)
        {
         int rgb = Color.rgb(user.getTheme_r(),user.getTheme_g(),user.getTheme_b());

        toolbar.setBackgroundColor(rgb);
        image.setBorderColor(rgb);
        textView11.setTextColor(rgb);
        textView12.setTextColor(rgb);

        first_name_label.setTextColor(rgb);
        last_name_label.setTextColor(rgb);
        address_label.setTextColor(rgb);
        phone_number_label.setTextColor(rgb);
        birth_date_label.setTextColor(rgb);
        partner_label.setTextColor(rgb);
        postsLabel.setTextColor(rgb);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_profile, container, false);
        sessionManager = new SessionManager(getContext());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

       user = sessionManager.getUser(getArguments().getString("username"),1);
        System.out.println("------->"+user);
        firstNameLabel=rootView.findViewById(R.id.firstNameLabel);
        lastNameLabel=rootView.findViewById(R.id.lastNameLabel);
        addressLabel=rootView.findViewById(R.id.addressLabel);
        phoneNumberLabel=rootView.findViewById(R.id.phoneNumberLabel);
        usernameLabel=rootView.findViewById(R.id.usernameLabel);
        settingBtn=rootView.findViewById(R.id.settingsAdd);

            toolbar = rootView.findViewById(R.id.toolbar);
            image=rootView.findViewById(R.id.image);
        postsLabel=rootView.findViewById(R.id.postsLabel);

            first_name_label=rootView.findViewById(R.id.first_name_label);
            last_name_label=rootView.findViewById(R.id.last_name_label);
            address_label=rootView.findViewById(R.id.address_label);
            phone_number_label=rootView.findViewById(R.id.phone_number_label);
            birth_date_label=rootView.findViewById(R.id.birth_date_label);
            partner_label=rootView.findViewById(R.id.partner_label);
        partnerLabel=rootView.findViewById(R.id.partnerLabel);
        birth_date=rootView.findViewById(R.id.birth_date);

        postsLabel=rootView.findViewById(R.id.postsLabel);
        textView11=rootView.findViewById(R.id.textView11);
        textView12=rootView.findViewById(R.id.textView12);
        jobProfile=rootView.findViewById(R.id.jobProfile);

       //loadGif(image);

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

        ImageRepository.getInstance().loadPicutreOf(user.getUsername().toString(),0.5f,0.5f, new ImageRepository.getPictureCallBack() {
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






        PostRepository.getInstance().getAllPostOf(user.getUsername(), new PostRepository.getAllPostCallBack() {
            @Override
            public void onResponse(List<Post> posts) {
                postsLabel.setText(""+posts.size());
            }
        });



        FollowRepository.getInstance().getWhatFollows(user.getUsername(), new FollowRepository.getManyCallback() {
            @Override
            public void getManyOneFollow(List<Follow> follow) {
                textView11.setText(""+follow.size());
            }
        });

        FollowRepository.getInstance().getFollowing(user.getUsername(), new FollowRepository.getManyCallback() {
            @Override
            public void getManyOneFollow(List<Follow> follow) {
                textView12.setText(""+follow.size());
            }
        });


initStyle();
settingBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SettingsFragment settingsFragment =new SettingsFragment();
        Bundle bundle =new Bundle();
        bundle.putString("username",user.getUsername());
        settingsFragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.frameProfile,settingsFragment).commit();


    }
});

        return rootView;
    }


    private void loadGif(ImageView iv){

        try {
            ImageDecoder.Source source =
                    ImageDecoder.createSource(getResources(), R.drawable.glow);

            Drawable drawable = ImageDecoder.decodeDrawable(source);
            iv.setImageDrawable(drawable);

            if (drawable instanceof AnimatedImageDrawable) {
                ((AnimatedImageDrawable) drawable).start();
                Toast.makeText(getContext(),
                        "Animation started",
                        Toast.LENGTH_LONG).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),
                    "IOException: \n" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

    }

}

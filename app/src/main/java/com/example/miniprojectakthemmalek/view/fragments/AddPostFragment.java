package com.example.miniprojectakthemmalek.view.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Activities;
import com.example.miniprojectakthemmalek.model.entities.Enums.ActivityType;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.Tag;
import com.example.miniprojectakthemmalek.model.entities.Tag_post;
import com.example.miniprojectakthemmalek.model.repositories.ActivitiesRepository;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.model.repositories.TagPostRepository;
import com.example.miniprojectakthemmalek.view.ProfileActivity;
import com.example.miniprojectakthemmalek.view.utils.Base_Home;
import com.example.miniprojectakthemmalek.view.utils.GeoCoder.GeoLocationManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonPrimitive;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.chip.Chip;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class AddPostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText et_post;
    TextView usernamelabel;
    FloatingActionButton moveToPost;
String username;
SwitchCompat positionSwitch;
    Post post;
CircularImageView image;
    private FusedLocationProviderClient client;

    List<String> list=new ArrayList<String>();

    List<String> list2=new ArrayList<String>();


    public AddPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPostFragment newInstance(String param1, String param2) {
        AddPostFragment fragment = new AddPostFragment();
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

        View rootView =inflater.inflate(R.layout.dialog_add_post, container, false);

        et_post=rootView.findViewById(R.id.et_post);
        moveToPost=rootView.findViewById(R.id.moveToPost);
        usernamelabel=rootView.findViewById(R.id.usernamelabel);
        positionSwitch=rootView.findViewById(R.id.positionSwitch);
        final TagContainerLayout mTagContainer = (TagContainerLayout) rootView.findViewById(R.id.tag);
        final TagContainerLayout mTagContainer2 = (TagContainerLayout) rootView.findViewById(R.id.tag2);
        mTagContainer.setTheme(ColorFactory.NONE);
        mTagContainer.setBackgroundColor(Color.TRANSPARENT);
        mTagContainer2.setTheme(ColorFactory.NONE);
        mTagContainer2.setBackgroundColor(Color.TRANSPARENT);

        image = rootView.findViewById(R.id.image);

        list.addAll(Arrays.asList(getResources().getStringArray(R.array.disease_array)));


        username= getArguments().getString("username");
       
        usernamelabel.setText(username);


        ImageRepository.getInstance().loadPicutreOf(username,0.2f,0.2f, new ImageRepository.getPictureCallBack() {
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

moveToPost.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(positionSwitch.isChecked())
        {

            requestPermission();
            client = LocationServices.getFusedLocationProviderClient(getContext());
            if(ActivityCompat.checkSelfPermission(getContext(), ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
                System.out.println("sssssssssssssssssss-------");
                return;
            }

            client.getLastLocation().addOnSuccessListener(getActivity(),new OnSuccessListener<Location>(){
                @Override
                public void onSuccess(Location location) {
                    if(location != null)
                    {
                        GeoLocationManager geoLocationManager=new GeoLocationManager(getContext(),42.668300,12.776731);
                        post=new Post(username,et_post.getText().toString(),geoLocationManager.getAddress(getContext(),location.getLatitude(),location.getLongitude()));
                    }
                    // System.out.println("lat-----------> "+location.getLatitude());

                }
            });


        }else{

            post=new Post(username,et_post.getText().toString());

        }

        PostRepository.getInstance().addPost(post, new PostRepository.getLastInsertedCallBack() {
            @Override
            public void onResponse(JsonPrimitive id) {

                if(list2.size()!=0)
                {
                    for(String s:list2)
                    {
                        Tag_post tag_post=new Tag_post(id.getAsInt(),s);
                        TagPostRepository.getInstance().addTagPost(tag_post, new TagPostRepository.getLastInsertedCallBack() {
                            @Override
                            public void onResponse(JsonPrimitive id) {

                            }
                        });

                    }
                }


                ActivitiesRepository.getInstance().addActivities(new Activities(username, ""+id, ActivityType.POST), new ActivitiesRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {



                    }
                });

                PostsFragment postsFragment =    new PostsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                postsFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frameHome,postsFragment).commit();



            }
        });
    }
});



mTagContainer.setTags(list);
mTagContainer2.setTags(list2);

mTagContainer.setOnTagClickListener(new TagView.OnTagClickListener() {
    @Override
    public void onTagClick(int position, String text) {

        list.remove(text);
        list2.add(text);

        mTagContainer.setTags(list);
        mTagContainer2.setTags(list2);

    }

    @Override
    public void onTagLongClick(int position, String text) {

    }

    @Override
    public void onSelectedTagDrag(int position, String text) {

    }

    @Override
    public void onTagCrossClick(int position) {

    }
});


mTagContainer2.setOnTagClickListener(new TagView.OnTagClickListener() {
    @Override
    public void onTagClick(int position, String text) {

        list2.remove(text);
        list.add(text);

        mTagContainer.setTags(list);
        mTagContainer2.setTags(list2);

    }

    @Override
    public void onTagLongClick(int position, String text) {

    }

    @Override
    public void onSelectedTagDrag(int position, String text) {

    }

    @Override
    public void onTagCrossClick(int position) {

    }
});

        return rootView;

    }


    private void requestPermission()
    {
        ActivityCompat.requestPermissions(getActivity(),new String[]{ACCESS_FINE_LOCATION},1);
    }

}

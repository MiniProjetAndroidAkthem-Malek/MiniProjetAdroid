package com.example.miniprojectakthemmalek.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.view.HomeActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class AddPostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText et_post;
    FloatingActionButton moveToPost;
String username;
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
username= getArguments().getString("username");
       

moveToPost.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        PostRepository.getInstance().addPost(new Post(username, et_post.getText().toString()), new PostRepository.addingCallback() {
            @Override
            public void addingCallback(int code) {
                if(code==200){

                    PostsFragment postsFragment =    new PostsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("username",username);
                    postsFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.frameHome,postsFragment).commit();
                }
            }
        });
    }
});




        return rootView;
    }


}

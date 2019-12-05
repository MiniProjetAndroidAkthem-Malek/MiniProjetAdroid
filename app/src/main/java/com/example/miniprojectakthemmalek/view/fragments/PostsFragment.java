package com.example.miniprojectakthemmalek.view.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IPost;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.view.SessionManager;
import com.example.miniprojectakthemmalek.view.adapter.AccountsAdapter;
import com.example.miniprojectakthemmalek.view.adapter.PostAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button movetoprofile;
    SessionManager sessionManager;
    User user;
    Toolbar toolbar;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
String username;
    FloatingActionButton moveToAddPost;
    public PostsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostsFragment newInstance(String param1, String param2) {
        PostsFragment fragment = new PostsFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_posts, container, false);
        movetoprofile=rootView.findViewById(R.id.movetoprofile);
        toolbar=rootView.findViewById(R.id.toolbar);
        recyclerView = rootView.findViewById(R.id.recyclerViewPost);
        moveToAddPost=rootView.findViewById(R.id.moveToAddPost);
        username = getArguments().getString("username");

        moveToAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddPostFragment addPostFragment =    new AddPostFragment();
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                addPostFragment.setArguments(bundle);



                getFragmentManager().beginTransaction().replace(R.id.frameHome,addPostFragment).commit();

            }
        });


        IPost iPost = RetrofitInstance.getRetrofitInstance().create(IPost.class);

        Call<List<Post>> call;
        call = iPost.getAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                postAdapter=new PostAdapter(response.body());
                postAdapter.setUsername(username);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(postAdapter);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                t.printStackTrace();
            }

        });


        return rootView;
    }

    public void initStyle()
    {

        if(user.getTheme_r()!=0)
        {
            int rgb = Color.rgb(user.getTheme_r(),user.getTheme_g(),user.getTheme_b());

            toolbar.setBackgroundColor(rgb);

        }
    }

}

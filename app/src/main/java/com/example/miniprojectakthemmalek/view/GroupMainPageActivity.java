package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupPost;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.repositories.GroupPostsRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.view.adapter.PostGroupAdapter;

import java.util.List;

public class GroupMainPageActivity extends AppCompatActivity {

    TextView group_nameTextViw,post_number_group;
    String group_name;
    RecyclerView recyclerViewPosts;
    Button add_post;
    String connectedUsername;
    EditText descriptionPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main_page);

        group_name=getIntent().getStringExtra("group_name");
        connectedUsername=getIntent().getStringExtra("username");

        group_nameTextViw=findViewById(R.id.group_name);
        post_number_group=findViewById(R.id.post_number_group);
        recyclerViewPosts=findViewById(R.id.recyclerViewPosts);
        descriptionPost=findViewById(R.id.description_post);
        add_post  =findViewById(R.id.add_post);
        group_nameTextViw.setText(group_name);


        GroupRepository.getInstance().getGroupByName(group_name, new GroupRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<Group> groupList) {


            }
        });


        GroupPostsRepository.getInstance().getPostsByGroup(group_name, new GroupPostsRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<Post> groupList) {

                post_number_group.setText(""+groupList.size());

                PostGroupAdapter postGroupAdapter=new PostGroupAdapter(groupList);
                recyclerViewPosts.setAdapter(postGroupAdapter);
                recyclerViewPosts.setHasFixedSize(false);
                recyclerViewPosts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        });


        add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostRepository.getInstance().addPost(new Post(connectedUsername, descriptionPost.getText().toString()), new PostRepository.addingPostCallback() {
                    @Override
                    public void addingCallback(Post post) {


                        PostRepository.getInstance().getPost(connectedUsername, descriptionPost.getText().toString(), new PostRepository.getAllPostCallBack() {
                            @Override
                            public void onResponse(List<Post> posts) {

                                GroupPostsRepository.getInstance().addGrouPost(new GroupPost(connectedUsername, group_name,""+ posts.get(0).getId()), new GroupUserRepository.addingCallback() {
                                    @Override
                                    public void addingCallback(int code) {



                                    }
                                });


                            }
                        });
                    }
                });
            }


        });




    }
}

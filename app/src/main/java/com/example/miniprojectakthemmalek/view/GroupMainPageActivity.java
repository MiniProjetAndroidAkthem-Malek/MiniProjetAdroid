package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupPost;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.Role;
import com.example.miniprojectakthemmalek.model.entities.Status;
import com.example.miniprojectakthemmalek.model.repositories.GroupPostsRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.InvitationRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.view.adapter.PostAdapter;
import com.example.miniprojectakthemmalek.view.adapter.PostGroupAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

public class GroupMainPageActivity extends AppCompatActivity {

    TextView group_nameTextViw,post_number_group;
    String group_name;
    RecyclerView recyclerViewPosts;
    Button add_post;
    String connectedUsername;
    EditText descriptionPost;
    PostAdapter postAdapter;
    AppCompatButton bt_join;
    FloatingActionButton settingsAdd;

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
        bt_join=findViewById(R.id.bt_join);
        add_post  =findViewById(R.id.add_post);
        settingsAdd=findViewById(R.id.settingsAdd);
        group_nameTextViw.setText(group_name);

        settingsAdd.setVisibility(View.INVISIBLE);

        GroupRepository.getInstance().getGroupByName(group_name, new GroupRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<Group> groupList) {


            }
        });

settingsAdd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(getApplicationContext(),SettingsGroupActivity.class);
        intent.putExtra("username",connectedUsername);
        intent.putExtra("groupName",group_name);
        startActivity(intent);

    }
});


        PostRepository.getInstance().getAllPostByGroupName(group_name, new PostRepository.getAllPostCallBack() {
            @Override
            public void onResponse(List<Post> posts) {

                postAdapter =new PostAdapter(getApplicationContext(),posts);
                recyclerViewPosts.setAdapter(postAdapter);
                recyclerViewPosts.setHasFixedSize(false);
                recyclerViewPosts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
           //     recyclerViewPosts.notifyAll();

            }
        });

        add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostRepository.getInstance().addPost(new Post(connectedUsername,descriptionPost.getText().toString(),group_name,"qqq"), new PostRepository.addingPostCallback() {
                    @Override
                    public void addingCallback(Post post) {

                   PostRepository.getInstance().getAllPostByGroupName(group_name, new PostRepository.getAllPostCallBack() {
                       @Override
                       public void onResponse(List<Post> posts) {

                           postAdapter =new PostAdapter(getApplicationContext(),posts);
                           postAdapter.notifyDataSetChanged();
                           recyclerViewPosts.setAdapter(postAdapter);
                           descriptionPost.setText("");

                       }
                   });


                    }
                });
            }


        });



        GroupUserRepository.getInstance().getUserGroup(group_name, connectedUsername, new GroupUserRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<GroupUser> list) {

                if(list.size()!=0)
                {
                    GroupUser groupUser=list.get(0);
                    if(groupUser.getRole().toString().equals(Role.ADMIN.toString())  )
                    {

                        bt_join.setVisibility(View.INVISIBLE);
                        settingsAdd.setVisibility(View.VISIBLE);

                    }
                }
            }
        });

        GroupRepository.getInstance().getGroupByName(group_name, new GroupRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<Group> groupList) {


                System.out.println("---->" +groupList);
                if(groupList.size()>0)
                {
                    Group group=groupList.get(0);
                    if(group.getCreator().toString().equals(connectedUsername) )
                    {
                        bt_join.setVisibility(View.INVISIBLE);
                        settingsAdd.setVisibility(View.VISIBLE);

                    }
                }

            }
        });



        bt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GroupUserRepository.getInstance().addGroupUser(new GroupUser(connectedUsername, group_name, Role.USER, Status.WAITING), new GroupUserRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {

                        if(code==200)
                        {
                            bt_join.setText("Waiting for admin's response ...");
                            bt_join.setEnabled(false);
                        }

                    }
                });

            }
        });

    }
}

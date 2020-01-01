package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.Enums.Role;
import com.example.miniprojectakthemmalek.model.entities.Enums.Status;
import com.example.miniprojectakthemmalek.model.repositories.GroupRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.view.adapter.PostGroupAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonPrimitive;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupMainPageActivity extends AppCompatActivity {

    TextView rolePost,group_nameTextViw,post_number_group;
    String group_name;
    RecyclerView recyclerViewPosts;
    Button add_post;
    String connectedUsername;
    EditText descriptionPost;
    PostGroupAdapter postGroupAdapter;
    AppCompatButton bt_join;
    FloatingActionButton settingsAdd;
    CircleImageView usernameCircleImageView;
    TextView usernamelabel;



    LinearLayout editPost;

    CardView postCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main_page);

        group_name=getIntent().getStringExtra("group_name");
        connectedUsername=getIntent().getStringExtra("username");
        editPost=findViewById(R.id.editPost);
        group_nameTextViw=findViewById(R.id.group_name);
        post_number_group=findViewById(R.id.post_number_group);
        recyclerViewPosts=findViewById(R.id.recyclerViewPosts);
        descriptionPost=findViewById(R.id.description_post);
        bt_join=findViewById(R.id.bt_join);
        add_post  =findViewById(R.id.add_post);
        settingsAdd=findViewById(R.id.settingsAdd);
        postCardView=findViewById(R.id.postCardView);

        usernameCircleImageView=findViewById(R.id.usernameCircleImageView);
        usernamelabel=findViewById(R.id.usernamelabel);

        rolePost=findViewById(R.id.rolePost);

        group_nameTextViw.setText(group_name);


        initView();
        initEditPostCardView();

        //  If the connected User is an admin or simple user then

        GroupUserRepository.getInstance().getUserGroup(group_name, connectedUsername, new GroupUserRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<GroupUser> list) {

                if(list.size()!=0)
                {
                    GroupUser groupUser=list.get(0);

                    if(groupUser.getRole()==Role.ADMIN && groupUser.getStatus() == Status.WAITING)
                    {
                        postCardView.setVisibility(View.VISIBLE);
                        editPost.setVisibility(View.GONE);

                    }else if (groupUser.getRole()==Role.ADMIN && groupUser.getStatus() == Status.COMFIRMED)
                    {

                        bt_join.setVisibility(View.GONE);
                        settingsAdd.setVisibility(View.VISIBLE);
                        postCardView.setVisibility(View.VISIBLE);
                        editPost.setVisibility(View.VISIBLE);
                        rolePost.setText("Admin");

                    }else if (groupUser.getRole()==Role.USER && groupUser.getStatus() == Status.WAITING){



                    }else if(groupUser.getRole()==Role.USER && groupUser.getStatus() == Status.COMFIRMED)
                    {

                        bt_join.setVisibility(View.GONE);
                        postCardView.setVisibility(View.VISIBLE);

                    }
                }else
                {


                    //  If the connected User is the creator

                    GroupRepository.getInstance().getGroupByName(group_name, new GroupRepository.getAllGroupCallBack() {
                        @Override
                        public void onResponse(List<Group> groupList) {

                            if(groupList.size()!=0)
                            {
                                Group group=groupList.get(0);
                                if(group.getCreator().equals(connectedUsername))
                                {

                                    bt_join.setVisibility(View.GONE);
                                    settingsAdd.setVisibility(View.VISIBLE);
                                    postCardView.setVisibility(View.VISIBLE);
                                    editPost.setVisibility(View.VISIBLE);
                                    rolePost.setText("Creator");

                                }else
                                {
                                    bt_join.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });

                }

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

                postGroupAdapter =new PostGroupAdapter();

                postGroupAdapter.setPost_list(posts);
                postGroupAdapter.setUsername(connectedUsername);
                postGroupAdapter.setGroupName(group_name);
                recyclerViewPosts.setAdapter(postGroupAdapter);
                recyclerViewPosts.setHasFixedSize(false);
                recyclerViewPosts.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true));
           //     recyclerViewPosts.notifyAll();

            }
        });

        add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostRepository.getInstance().addPost(new Post(connectedUsername, descriptionPost.getText().toString(), group_name, "qqq"), new PostRepository.getLastInsertedCallBack() {
                    @Override
                    public void onResponse(JsonPrimitive id) {

                        PostRepository.getInstance().getAllPostByGroupName(group_name, new PostRepository.getAllPostCallBack() {
                            @Override
                            public void onResponse(List<Post> posts) {

                                postGroupAdapter =new PostGroupAdapter();
                                postGroupAdapter.setPost_list(posts);
                                postGroupAdapter.notifyDataSetChanged();
                                recyclerViewPosts.setAdapter(postGroupAdapter);
                                descriptionPost.setText("");

                            }
                        });

                    }
                });
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
                            bt_join.setText("Please Wait for admin's response ...");
                            bt_join.setEnabled(false);
                        }

                    }
                });

            }
        });

    }

    public void initEditPostCardView()
    {

        usernamelabel.setText(connectedUsername);

        ImageRepository.getInstance().loadPicutreOf(connectedUsername,0.2f,0.2f, new ImageRepository.getPictureCallBack() {
            @Override
            public void onResponse(Bitmap picUrl) {
                if(picUrl==null)
                {
                    usernameCircleImageView.setImageResource(R.drawable.default_avatar);

                }else{
                    usernameCircleImageView.setImageBitmap(picUrl);
                }
            }
        });

    }



public void initView()
{
    this.bt_join.setVisibility(View.GONE);
    this.settingsAdd.setVisibility(View.GONE);
    this.postCardView.setVisibility(View.GONE);
}


}

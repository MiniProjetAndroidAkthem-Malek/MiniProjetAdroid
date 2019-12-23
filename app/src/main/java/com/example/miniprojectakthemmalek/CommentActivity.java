package com.example.miniprojectakthemmalek;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.entities.comment;
import com.example.miniprojectakthemmalek.model.repositories.CommentRepository;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.view.ProfileActivity;
import com.example.miniprojectakthemmalek.view.SessionManager;
import com.example.miniprojectakthemmalek.view.adapter.CommentAdapter;
import com.example.miniprojectakthemmalek.view.fragments.PostsFragment;
import com.example.miniprojectakthemmalek.view.fragments.ProfileFragment;
import com.example.miniprojectakthemmalek.view.utils.Base_Home;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentActivity extends AppCompatActivity {

    TextView et_post;
    EditText et_comment;
    Button commenter;

    TextView usernamelabel;

    String username;
    String description;
    ImageButton movetoprofile;
    ImageButton movetobasehome;
    RecyclerView comments;
    CommentAdapter commentAdapter;
    int id_post;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_comment);

        et_post=findViewById(R.id.et_post);
        et_comment=findViewById(R.id.et_comment);
        commenter = findViewById(R.id.commenter);
        circleImageView = findViewById(R.id.circleImageView);




        movetoprofile=findViewById(R.id.moveeee);
        movetobasehome=findViewById(R.id.movetobasehome);
        usernamelabel=findViewById(R.id.usernamelabel);
        comments=findViewById(R.id.comments);
        username= getIntent().getStringExtra("username");
        description= getIntent().getStringExtra("post");
        id_post=getIntent().getIntExtra("id_post",0);
        usernamelabel.setText(username);
        movetoprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });


        et_post.setText(description);





        ImageRepository.getInstance().loadPicutreOf(username,0.1f,0.1f, new ImageRepository.getPictureCallBack() {
            @Override
            public void onResponse(Bitmap picUrl) {
                if(picUrl==null)
                {
                    circleImageView.setImageResource(R.drawable.default_avatar);

                }else{
                    circleImageView.setImageBitmap(picUrl);
                }
            }
        });








        movetobasehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getApplicationContext(), Base_Home.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });


        CommentRepository.getInstance().getPostComments(id_post, new CommentRepository.getManyCallback() {
            @Override
            public void getManyOneFollow(List<comment> like_posts) {

               if(like_posts.size()>0)
               {

                commentAdapter = new CommentAdapter();
                commentAdapter.setCommentList(like_posts);

                commentAdapter.setConnectedUsername(username);

                comments.setAdapter(commentAdapter);
                comments.setHasFixedSize(false);
                comments.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
               }
            }
        });


         commenter.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 CommentRepository.getInstance().addComment(new comment(id_post, username, et_comment.getText().toString()), new CommentRepository.addingCallback() {
                     @Override
                     public void addingCallback(int code) {
                         if(code == 200){


                             System.out.println("*************ok***********");
                             et_comment.setText("");
                             CommentRepository.getInstance().getPostComments(id_post, new CommentRepository.getManyCallback() {
                                 @Override
                                 public void getManyOneFollow(List<comment> like_posts) {

                                     if(like_posts.size()>0)
                                     {

                                         commentAdapter = new CommentAdapter();
                                         commentAdapter.setCommentList(like_posts);
                                        commentAdapter.setConnectedUsername(username);
                                         comments.setAdapter(commentAdapter);
                                         comments.setHasFixedSize(false);
                                         comments.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                     }
                                 }
                             });


                         }
                     }
                 });

             }
         });







    }


}




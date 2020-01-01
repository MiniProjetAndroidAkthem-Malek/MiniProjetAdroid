package com.example.miniprojectakthemmalek.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.Enums.Role;
import com.example.miniprojectakthemmalek.model.entities.Enums.Status;
import com.example.miniprojectakthemmalek.model.entities.like_posts;
import com.example.miniprojectakthemmalek.model.repositories.GroupUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.model.repositories.like_postsRepository;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostGroupAdapter extends RecyclerView.Adapter<PostGroupAdapter.PostViewHolder> {

    private List<Post> post_list;
    private String username,groupName;


    Context context;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public List<Post> getPost_list() {
        return post_list;
    }

    public void setPost_list(List<Post> post_list) {
        this.post_list = post_list;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public PostGroupAdapter()
    {

    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View item=layoutInflater.inflate(R.layout.item_group_post,parent,false);
        PostViewHolder postViewHolder=new PostViewHolder(item);

        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, final int position) {

        final Post single_post=this.post_list.get(position);

        holder.username_text_view.setText(single_post.getUsername());
        holder.description_text_view.setText(single_post.getDescription());

        GroupUserRepository.getInstance().getUserGroup(groupName, username, new GroupUserRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<GroupUser> list) {
                if(list.size() != 0)
                {
                    GroupUser groupUser=list.get(0);
                    if(groupUser.getRole() == Role.ADMIN && groupUser.getStatus() == Status.COMFIRMED)
                    {
                        holder.deletePost.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        holder.deletePost.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());

            dialog.setTitle("Deleting");
            dialog.setMessage("Are you sure do you want to hide this post ? :/" );
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                    single_post.setDescription("This post has been hidden by admin for abuse or violation content ");

                    PostRepository.getInstance().updateDescriptionPost(single_post, new PostRepository.addingCallback() {
                        @Override
                        public void addingCallback(int code) {


                        }
                    });

                    post_list.remove(position);
                    post_list.add(position,single_post);
                    notifyDataSetChanged();
                }
            })
                    .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Action for "Cancel".
                        }
                    });
            final AlertDialog alert = dialog.create();
            alert.show();



        }
    });

        ImageRepository.getInstance().loadPicutreOf(single_post.getUsername().toString(),0.1f,0.1f, new ImageRepository.getPictureCallBack() {
            @Override
            public void onResponse(Bitmap picUrl) {
                if(picUrl==null)
                {
                    holder.postImageView.setImageResource(R.drawable.default_avatar);

                }else{
                    holder.postImageView.setImageBitmap(picUrl);
                }
            }
        });


        like_postsRepository.getInstance().getPostLikes(single_post.getId(), new like_postsRepository.getManyCallback() {
            @Override
            public void getManyOneFollow(List<like_posts> like_posts) {

                holder.likeNumbers.setText(""+like_posts.size());

            }
        });
        like_postsRepository.getInstance().getPostLikesByUsernameAndId(single_post.getId(),username, new like_postsRepository.getManyCallback() {
            @Override
            public void getManyOneFollow(List<like_posts> like_posts) {


                if(like_posts.size()==0)
                {
                    holder.likePostGroup.setImageResource(R.drawable.ic_favorite_border);
                    holder.x=2;


                }else{

                    holder.likePostGroup.setImageResource(R.drawable.ic_favorites);
                    holder.x=1;
                }

            }
        });



        holder.likePostGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.x++;

                if(holder.x%2==0)
                {

                    holder.likePostGroup.setImageResource(R.drawable.ic_favorite_border);
                    like_postsRepository.getInstance().dislike(username, single_post.getId(), new like_postsRepository.deletingCallback() {
                        @Override
                        public void deletingCallback(int code) {

                            like_postsRepository.getInstance().getPostLikes(single_post.getId(), new like_postsRepository.getManyCallback() {
                                @Override
                                public void getManyOneFollow(List<like_posts> like_posts) {

                                    holder.likeNumbers.setText(""+like_posts.size());

                                }
                            });

                        }
                    });


                }else{

                    holder.likePostGroup.setImageResource(R.drawable.ic_favorites);

                    like_postsRepository.getInstance().getPostLikesByUsernameAndId(single_post.getId(),username    , new like_postsRepository.getManyCallback() {
                        @Override
                        public void getManyOneFollow(List<like_posts> like_posts) {


                            if(like_posts.size()==0)
                            {
                                like_posts like_post=new like_posts(single_post.getId(),username);

                                like_postsRepository.getInstance().addlike_posts(like_post, new like_postsRepository.addingCallback() {
                                    @Override
                                    public void addingCallback(int code) {


                                        like_postsRepository.getInstance().getPostLikes(single_post.getId(), new like_postsRepository.getManyCallback() {
                                            @Override
                                            public void getManyOneFollow(List<like_posts> like_posts) {

                                                holder.likeNumbers.setText(""+like_posts.size());

                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });






                }
            }
        });

    }

    @Override
    public int getItemCount() {

    return post_list.size();

}

public void initView(final ImageButton imageButtonLike,Post post)
{



}

public void updateLikeNumbers(Post post, final TextView textView)
{




}



public void updateView(ImageButton imageButtonLike, final Post post, final TextView textView)
{



}

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        public TextView username_text_view;
        public TextView description_text_view;
        public TextView likeNumbers;
        public CircleImageView postImageView;
        public ImageButton deletePost;
        public ImageButton likePostGroup;
        public  int x=0;

        public PostViewHolder(@NonNull View itemView)
        {
            super(itemView);

            username_text_view=itemView.findViewById(R.id.usernamePost);
            description_text_view=itemView.findViewById(R.id.descriptionPost);
            postImageView=itemView.findViewById(R.id.postImageView);
            deletePost=itemView.findViewById(R.id.deletePost);
            likePostGroup=itemView.findViewById(R.id.likePostGroup);
            likeNumbers=itemView.findViewById(R.id.likeNumbers);

        }
    }

}

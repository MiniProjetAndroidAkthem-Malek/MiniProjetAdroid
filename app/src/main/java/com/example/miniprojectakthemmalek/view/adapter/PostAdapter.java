package com.example.miniprojectakthemmalek.view.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.CommentActivity;
import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.like_posts;
import com.example.miniprojectakthemmalek.model.repositories.CommentRepository;
import com.example.miniprojectakthemmalek.model.repositories.like_postsRepository;
import com.example.miniprojectakthemmalek.view.OtherProfileActivity;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> post_list;
    private String username;

    int x = 0;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PostAdapter(List<Post> postList) {
        post_list = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.post_item_adapter, parent, false);
        PostViewHolder postViewHolder = new PostViewHolder(item);

        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {

        System.out.println(this.post_list.get(position));
        final Post single_post = this.post_list.get(position);
        holder.username_text_view.setText(single_post.getUsername());
        holder.description_text_view.setText(single_post.getDescription());


        holder.show_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(holder.itemView.getContext(), CommentActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("post",single_post.getDescription());
                intent.putExtra("id_post",single_post.getId());
                holder.itemView.getContext().startActivity(intent);



            }
        });





        holder.username_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), OtherProfileActivity.class);
                intent.putExtra("username", holder.username_text_view.getText().toString());
                intent.putExtra("ConnectedUsername", username);
                intent.putExtra("id_post",single_post.getId());
                holder.itemView.getContext().startActivity(intent);

            }
        });
        like_postsRepository.getInstance().getPostLikes(single_post.getId(), new like_postsRepository.getManyCallback() {
            @Override
            public void getManyOneFollow(List<like_posts> like_posts) {
                holder.likes.setText(like_posts.size() + " Likes");
            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick (View v){
                    if(x==0)

                    {
                like_postsRepository.getInstance().addlike_posts(new like_posts(single_post.getId(), username), new like_postsRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {
                        if (code == 200) {
                            x = 1;
                            like_postsRepository.getInstance().getPostLikes(single_post.getId(), new like_postsRepository.getManyCallback() {
                                @Override
                                public void getManyOneFollow(List<like_posts> like_posts) {
                                    holder.likes.setText(like_posts.size() + " Likes");
                                    holder.like.setBackgroundResource(R.drawable.seek_thumb_red);
                                }
                            });


                        }
                    }
                });

            }


            if(x==1)



                like_postsRepository.getInstance().dislike(username, single_post.getId(), new like_postsRepository.deletingCallback() {
                    @Override
                    public void deletingCallback(int code) {
                        if (code == 200) {
                            x = 0;

                            like_postsRepository.getInstance().getPostLikes(single_post.getId(), new like_postsRepository.getManyCallback() {
                                @Override
                                public void getManyOneFollow(List<like_posts> like_posts) {
                                    holder.likes.setText(like_posts.size() + " Likes");
                                    holder.like.setBackgroundResource(R.drawable.ic_thumb_up);

                                }
                            });


                        }
                    }
                });


            }


        });
        System.out.println("**********"+x);
    }





    @Override
    public int getItemCount() {

    return post_list.size();

}

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        public TextView username_text_view;
        public TextView description_text_view;
        public TextView show_comments;
        public ImageView like;
        public Button likes;



        public PostViewHolder(@NonNull View itemView)
        {
            super(itemView);

            username_text_view=itemView.findViewById(R.id.usernamePost);
            description_text_view=itemView.findViewById(R.id.descriptionPost);
            like = itemView.findViewById(R.id.like);
            likes = itemView.findViewById(R.id.likes);
            show_comments=itemView.findViewById(R.id.show_comments);
        }



    }

}

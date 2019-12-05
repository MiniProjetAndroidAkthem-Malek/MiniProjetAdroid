package com.example.miniprojectakthemmalek.view.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.view.OtherProfileActivity;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> post_list;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PostAdapter(List<Post> postList)
    {
    post_list=postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View item=layoutInflater.inflate(R.layout.post_item_adapter,parent,false);
        PostViewHolder postViewHolder=new PostViewHolder(item);

        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {

        System.out.println(this.post_list.get(position));
        final Post single_post=this.post_list.get(position);
        holder.username_text_view.setText(single_post.getUsername());
        holder.description_text_view.setText(single_post.getDescription());

        holder.username_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), OtherProfileActivity.class);
                intent.putExtra("username",holder.username_text_view.getText().toString());
                intent.putExtra("ConnectedUsername",username);
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

    return post_list.size();

}

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        public TextView username_text_view;
        public TextView description_text_view;


        public PostViewHolder(@NonNull View itemView)
        {
            super(itemView);

            username_text_view=itemView.findViewById(R.id.usernamePost);
            description_text_view=itemView.findViewById(R.id.descriptionPost);
        }



    }

}

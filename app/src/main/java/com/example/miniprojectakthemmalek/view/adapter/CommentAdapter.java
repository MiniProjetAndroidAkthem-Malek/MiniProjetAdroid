package com.example.miniprojectakthemmalek.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>  {
    private List<comment> commentList;

    public List<comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.comment_adapter, parent, false);
        CommentAdapter.CommentViewHolder commentViewHolder = new CommentAdapter.CommentViewHolder(item);

        return commentViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {



        final comment single_comment = this.commentList.get(position);
        holder.comment.setText(single_comment.getComment());
        holder.usernameComment.setText(single_comment.getUsername());


    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {

        public TextView usernameComment;
        public TextView comment;




        public CommentViewHolder(@NonNull View itemView)
        {

            super(itemView);
            usernameComment=itemView.findViewById(R.id.usernameComment);
            comment=itemView.findViewById(R.id.comment);

        }



    }

}

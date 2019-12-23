package com.example.miniprojectakthemmalek.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Message;

import java.util.List;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.DiscussionViewHolder> {

    List<Message> messageList;

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public DiscussionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View item=layoutInflater.inflate(R.layout.discussion_item_adapter,parent,false);
        DiscussionViewHolder discussionViewHolder=new DiscussionViewHolder(item);

        return discussionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionViewHolder holder, int position) {


        final Message single_message=this.messageList.get(position);

        holder.txt_view.setText(single_message.getReceiver());


    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class DiscussionViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_view;

        public DiscussionViewHolder(@NonNull View itemView)
        {

            super(itemView);

            txt_view=itemView.findViewById(R.id.receiverUsername);

        }



    }
}

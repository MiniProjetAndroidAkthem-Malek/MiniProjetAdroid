package com.example.miniprojectakthemmalek.view.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Message;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{


    List<Message> messageList;
    String connectedUsername;
    String username;
    Context context;

    public  MessageAdapter(Context context)
    {
        this.context=context;
        messageList = new ArrayList<Message>();
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public String getConnectedUsername() {
        return connectedUsername;
    }

    public void setConnectedUsername(String connectedUsername) {
        this.connectedUsername = connectedUsername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View item=layoutInflater.inflate(R.layout.message_item_adapter,parent,false);
        MessageViewHolder messageViewHolder=new MessageViewHolder(item);

        return messageViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        final Message single_message=this.messageList.get(position);
        holder.messageTextView.setText(single_message.getMessage());

        if(single_message.getSender().toString().equals(connectedUsername))
        {

            holder.messageLinearLayout.removeAllViews();
            //Setting CircularImageView
            CircularImageView avatarCircularImageView=new CircularImageView(context);
            LinearLayout.LayoutParams paramsAvatar = new LinearLayout.LayoutParams(35,35);
            avatarCircularImageView.setLayoutParams(paramsAvatar);
            avatarCircularImageView.setImageResource(R.drawable.photo_male_8);

            //Setting View
            View emptyView = new View(context);
            LinearLayout.LayoutParams paramsEmptyView = new LinearLayout.LayoutParams(15,0);
            emptyView.setLayoutParams(paramsEmptyView);

            //Setting MessageTextView
            LinearLayout.LayoutParams MessageLayoutParamsTextView =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView messageTextView=new TextView(context);
            messageTextView.setLayoutParams(MessageLayoutParamsTextView);
            messageTextView.setText(single_message.getMessage());
            messageTextView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            messageTextView.setTextSize(20);
            messageTextView.setTextColor(R.color.grey_1000);

            holder.messageLinearLayout.addView(avatarCircularImageView);
            holder.messageLinearLayout.addView(emptyView);
            holder.messageLinearLayout.addView(messageTextView);

         //   holder.messageTextView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        }

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView messageTextView;
        public LinearLayout messageLinearLayout;
        public CircularImageView avatarCircularImageView;


        public MessageViewHolder(@NonNull View itemView)
        {
            super(itemView);
            messageTextView=itemView.findViewById(R.id.messageTextView);
            messageLinearLayout=itemView.findViewById(R.id.messageLinearLayout);
            avatarCircularImageView=itemView.findViewById(R.id.avatarCircularImageView);
        }

    }

}

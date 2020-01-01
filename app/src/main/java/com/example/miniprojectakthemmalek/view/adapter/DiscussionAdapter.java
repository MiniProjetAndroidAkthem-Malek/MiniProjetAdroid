package com.example.miniprojectakthemmalek.view.adapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Message;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;
import com.example.miniprojectakthemmalek.view.fragments.ConversationFragment;

import java.util.List;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.DiscussionViewHolder> {

    List<Message> messageList;

    String connectedUsername;
    FragmentManager fragmentManager;

    public String getConnectedUsername() {
        return connectedUsername;
    }

    public void setConnectedUsername(String connectedUsername) {
        this.connectedUsername = connectedUsername;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

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
    public void onBindViewHolder(@NonNull final DiscussionViewHolder holder, int position) {


        final Message single_message=this.messageList.get(position);


        if(connectedUsername.equals(single_message.getReceiver()))
        {
            holder.usernameOther=single_message.getSender();
        }

        if (connectedUsername.equals(single_message.getSender()))
        {
            holder.usernameOther=single_message.getReceiver();
        }

        holder.txt_view.setText(holder.usernameOther);
        holder.messageContent.setText(single_message.getMessage());

        ImageRepository.getInstance().loadPicutreOf(holder.usernameOther, 0.3f, 0.3f, new ImageRepository.getPictureCallBack() {
            @Override
            public void onResponse(Bitmap picBitmap) {

                holder.image_profile.setImageBitmap(picBitmap);

            }
        });

            holder.item_linear_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ConversationFragment conversationFragment=new ConversationFragment();
                    Bundle bundle =new Bundle();
                    bundle.putString("username",holder.usernameOther);
                    bundle.putString("ConnectedUsername",connectedUsername);
                    conversationFragment.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.chatFrameLayout,conversationFragment).commit();



                }
            });



    }

    @Override
    public int getItemCount() {
       return messageList.size();
    }

    public static class DiscussionViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_view,messageContent,dateMessage;
        public ImageView image_profile;
        public String usernameOther;
        public LinearLayout item_linear_layout;
        public DiscussionViewHolder(@NonNull View itemView)
        {

            super(itemView);

            txt_view=itemView.findViewById(R.id.receiverUsername);
            messageContent=itemView.findViewById(R.id.messageContent);
            dateMessage=itemView.findViewById(R.id.dateMessage);
            image_profile=itemView.findViewById(R.id.image_profile);
            item_linear_layout=itemView.findViewById(R.id.item_linear_layout);
        }



    }
}

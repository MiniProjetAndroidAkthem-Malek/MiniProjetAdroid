package com.example.miniprojectakthemmalek.view.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.example.miniprojectakthemmalek.model.entities.Enums.Status;
import com.example.miniprojectakthemmalek.model.repositories.GroupUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class InvitationGroupAdapter extends RecyclerView.Adapter<InvitationGroupAdapter.InvitationGroupViewHolder> {

    List<GroupUser> groupUsers;
    String connectedUsername;
    String group_name;


    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public List<GroupUser> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<GroupUser> groupUsers) {
        this.groupUsers = groupUsers;
    }

    public String getConnectedUsername() {
        return connectedUsername;
    }

    public void setConnectedUsername(String connectedUsername) {
        this.connectedUsername = connectedUsername;
    }

    public List<GroupUser> getInvitationList() {
        return groupUsers;
    }

    public void setInvitationList(List<GroupUser> groupUsers) {
        this.groupUsers = groupUsers;
    }

    @NonNull
    @Override
    public InvitationGroupAdapter.InvitationGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View item=layoutInflater.inflate(R.layout.invitation_group_single_item,parent,false);
        InvitationGroupAdapter.InvitationGroupViewHolder invitationViewHolder=new InvitationGroupAdapter.InvitationGroupViewHolder(item);

        return invitationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final InvitationGroupAdapter.InvitationGroupViewHolder holder, final int position) {

        final GroupUser single_groupUser=this.groupUsers.get(position);

        holder.usernameInvitation.setText(single_groupUser.getUsername());


        ImageRepository.getInstance().loadPicutreOf(single_groupUser.getUsername().toString(),0.1f,0.1f, new ImageRepository.getPictureCallBack() {
            @Override
            public void onResponse(Bitmap picUrl) {
                if(picUrl==null)
                {
                    holder.imageView.setImageResource(R.drawable.default_avatar);

                }else{
                    holder.imageView.setImageBitmap(picUrl);
                }
            }
        });



        holder.bt_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            single_groupUser.setStatus(Status.COMFIRMED);

                GroupUserRepository.getInstance().updateUser(single_groupUser, new GroupUserRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {


                        if(code==200)
                        {


                        }


                    }
                });

                groupUsers.remove(position);
                notifyDataSetChanged();


            }
        });


        holder.bt_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                GroupUserRepository.getInstance().deleteUser(single_groupUser.getUsername(), group_name, new GroupUserRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {


                        if(code==200)
                        {

                            groupUsers.remove(position);
                            notifyDataSetChanged();

                        }
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {

        return groupUsers.size();
    }

    public static class InvitationGroupViewHolder extends RecyclerView.ViewHolder {

        public TextView usernameInvitation;
        public Button bt_accept,bt_decline;
        CircleImageView imageView;

        public InvitationGroupViewHolder(@NonNull View itemView)
        {

            super(itemView);

            usernameInvitation=itemView.findViewById(R.id.usernameInvitation);
            bt_accept=itemView.findViewById(R.id.bt_accept);
            bt_decline = itemView.findViewById(R.id.bt_decline);
            imageView =  itemView.findViewById(R.id.imageView);

        }



    }
}

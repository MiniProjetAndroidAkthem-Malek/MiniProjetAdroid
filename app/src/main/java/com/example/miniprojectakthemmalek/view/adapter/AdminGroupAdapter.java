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
import com.example.miniprojectakthemmalek.model.entities.Enums.Role;
import com.example.miniprojectakthemmalek.model.repositories.GroupUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminGroupAdapter extends RecyclerView.Adapter<AdminGroupAdapter.AdminGroupViewHolder> {

    List<GroupUser> groupUsers;
    String connectedUsername,group_name;

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

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    @NonNull
    @Override
    public AdminGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

    LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
    View item=layoutInflater.inflate(R.layout.admin_group_single_item,parent,false);
    AdminGroupAdapter.AdminGroupViewHolder invitationViewHolder=new AdminGroupAdapter.AdminGroupViewHolder(item);
    return invitationViewHolder;

}

    @Override
    public void onBindViewHolder( final @NonNull AdminGroupViewHolder holder,final int position) {

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


        holder.bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                single_groupUser.setRole(Role.USER);
                GroupUserRepository.getInstance().updateRoleUser(single_groupUser, new GroupUserRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {
                        if (code==200)
                        {


                        }
                    }
                });
                groupUsers.remove(position);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return groupUsers.size();
    }


    public static class AdminGroupViewHolder extends RecyclerView.ViewHolder {

        public TextView usernameInvitation;
        public Button bt_delete;
        CircleImageView imageView;

        public AdminGroupViewHolder(@NonNull View itemView)
        {

            super(itemView);

            usernameInvitation=itemView.findViewById(R.id.usernameInvitation);
            bt_delete=itemView.findViewById(R.id.bt_delete);
            imageView =  itemView.findViewById(R.id.imageView);

        }

    }



}

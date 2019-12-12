package com.example.miniprojectakthemmalek.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Invitation;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.InvitationRepository;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.SessionManager;

import java.util.List;

public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.InvitationViewHolder> {

    List<Invitation> invitationList;

    public List<Invitation> getInvitationList() {
        return invitationList;
    }

    public void setInvitationList(List<Invitation> invitationList) {
        this.invitationList = invitationList;
    }

    @NonNull
    @Override
    public InvitationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View item=layoutInflater.inflate(R.layout.invitation_item_adapter,parent,false);
        InvitationViewHolder invitationViewHolder=new InvitationViewHolder(item);

        return invitationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final InvitationViewHolder holder, final int position) {

        final Invitation single_invitation=this.invitationList.get(position);
        holder.content_txt_view.setText(single_invitation.getContent());
        holder.title_txt_view.setText("Partner's invitation from "+single_invitation.getSender());
       final SessionManager sessionManager = new SessionManager(holder.itemView.getContext());

        holder.bt_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InvitationRepository.getInstance().deleteInvitionFor(single_invitation.getReceiver(), single_invitation.getSender(), new InvitationRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {

                        if(code !=0)
                        {

                        }

                    }
                });

                invitationList.remove(position);
                notifyDataSetChanged();
            }
        });


        holder.bt_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserRepository.getInstance().updatePartnerForUser(new User(single_invitation.getSender(),single_invitation.getReceiver()));
                UserRepository.getInstance().updatePartnerForUser(new User(single_invitation.getReceiver(),single_invitation.getSender()));

                UserRepository.getInstance().getOneUser(single_invitation.getSender(), new UserRepository.getOneUserCallBack() {
                    @Override
                    public void onResponse(User user) {
                        sessionManager.updateUser(user);

                    }
                });
                UserRepository.getInstance().getOneUser(single_invitation.getReceiver(), new UserRepository.getOneUserCallBack() {
                    @Override
                    public void onResponse(User user) {
                        sessionManager.updateUser(user);

                    }
                });


                InvitationRepository.getInstance().deleteInvitionFor(single_invitation.getReceiver(), single_invitation.getSender(), new InvitationRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {

                        if(code !=0)
                        {

                        }


                    }
                });

                invitationList.remove(position);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {

        return invitationList.size();
    }

    public static class InvitationViewHolder extends RecyclerView.ViewHolder {

        public TextView title_txt_view;
        public TextView content_txt_view;
        public Button bt_accept,bt_decline;

        public InvitationViewHolder(@NonNull View itemView)
        {

            super(itemView);
            title_txt_view=itemView.findViewById(R.id.invitation_title);
            content_txt_view=itemView.findViewById(R.id.invitation_content);
            bt_accept=itemView.findViewById(R.id.bt_accept);
            bt_decline = itemView.findViewById(R.id.bt_decline);
        }



    }
}

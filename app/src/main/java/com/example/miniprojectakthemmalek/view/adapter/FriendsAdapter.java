package com.example.miniprojectakthemmalek.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Event;
import com.example.miniprojectakthemmalek.model.entities.EventUser;
import com.example.miniprojectakthemmalek.model.entities.Follow;

import com.example.miniprojectakthemmalek.model.repositories.EventUserRepository;
import com.example.miniprojectakthemmalek.view.EventMainPageActivity;
import com.example.miniprojectakthemmalek.view.OtherProfileActivity;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.EventsViewHolder> {
    private Context context;
    List<Follow> followers;

String connectedUsername;

    public FriendsAdapter(Context context, List<Follow> followers) {
        this.context = context;
        this.followers = followers;
    }

    public FriendsAdapter() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Follow> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follow> followers) {
        this.followers = followers;
    }

    public String getConnectedUsername() {
        return connectedUsername;
    }

    public void setConnectedUsername(String connectedUsername) {
        this.connectedUsername = connectedUsername;
    }

    @NonNull
    @Override
    public FriendsAdapter.EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View item=layoutInflater.inflate(R.layout.friends_adapter,parent,false);
        FriendsAdapter.EventsViewHolder eventsViewHolder=new FriendsAdapter.EventsViewHolder(item);

        return eventsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final EventsViewHolder holder, int position) {
        final Follow single_follow=followers.get(position);
        holder.nom.setText(single_follow.getFollowing());




        holder.nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), OtherProfileActivity.class);
                intent.putExtra("username", single_follow.getFollowing());
                intent.putExtra("ConnectedUsername", connectedUsername);

                holder.itemView.getContext().startActivity(intent);

            }
        });






    }


    @Override
    public int getItemCount() {

        return followers.size();
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder {




        public TextView nom;



        public EventsViewHolder(@NonNull View itemView)
        {

            super(itemView);

           nom=itemView.findViewById(R.id.nom);
           // participate.setVisibility(View.INVISIBLE);

        }



    }
}

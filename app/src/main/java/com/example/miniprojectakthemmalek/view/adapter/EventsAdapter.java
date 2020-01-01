package com.example.miniprojectakthemmalek.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Event;
import com.example.miniprojectakthemmalek.model.entities.EventUser;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.example.miniprojectakthemmalek.model.entities.Role;
import com.example.miniprojectakthemmalek.model.entities.Status;
import com.example.miniprojectakthemmalek.model.repositories.EventUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;
import com.example.miniprojectakthemmalek.view.EventMainPageActivity;
import com.example.miniprojectakthemmalek.view.GroupMainPageActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    private Context context;
    List<Event> events;

String connectedUsername;

    public String getConnectedUsername() {
        return connectedUsername;
    }

    public void setConnectedUsername(String connectedUsername) {
        this.connectedUsername = connectedUsername;
    }

    public EventsAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public EventsAdapter.EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View item=layoutInflater.inflate(R.layout.events_adapter,parent,false);
        EventsAdapter.EventsViewHolder eventsViewHolder=new EventsAdapter.EventsViewHolder(item);

        return eventsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final EventsViewHolder holder, int position) {
        final Event single_event=events.get(position);
        holder.nom.setText(single_event.getNom());
        holder.Description.setText(single_event.getDescription());
        holder.explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("mmmmm");


                Intent intent=new Intent(v.getContext(), EventMainPageActivity.class);
                intent.putExtra("Event_name",single_event.getNom());
                intent.putExtra("Event_Description",single_event.getDescription());
                intent.putExtra("Event_State",single_event.getState());
                intent.putExtra("Event_Contry",single_event.getContry());
                intent.putExtra("username",connectedUsername);

                context.startActivity(intent);




            }
        });
        holder.participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("mmmmm");


                EventUserRepository.getInstance().addUserEvent(new EventUser(connectedUsername,single_event.getNom(), Role.USER) , new EventUserRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {
                        System.out.println("mmmmmmmmmmmmmmm");
                        holder.participate.setVisibility(View.INVISIBLE);

                    }
                });
                           }
        });





        EventUserRepository.getInstance().getUserEventByName(connectedUsername,single_event.getNom().toString(), new EventUserRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<EventUser> list) {
                    System.out.println("200000");
                    if (list.size()!=0) {
                    if(list.get(0).getRole()==Role.ADMIN || list.get(0).getRole()==Role.USER ) {
                        holder.participate.setVisibility(View.GONE);
                    }

                }

            }
        });



    }


    @Override
    public int getItemCount() {

        return events.size();
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder {




        public TextView nom,Description;
    public  Button explore,participate;


        public EventsViewHolder(@NonNull View itemView)
        {

            super(itemView);

           nom=itemView.findViewById(R.id.nom);
           Description=itemView.findViewById(R.id.description);
           explore = itemView.findViewById(R.id.explore);
           participate=itemView.findViewById(R.id.participate);
           // participate.setVisibility(View.INVISIBLE);

        }



    }
}
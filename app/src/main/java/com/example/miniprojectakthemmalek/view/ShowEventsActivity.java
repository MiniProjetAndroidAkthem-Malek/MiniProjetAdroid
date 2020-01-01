package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Event;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.repositories.EventRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupRepository;
import com.example.miniprojectakthemmalek.view.adapter.EventsAdapter;
import com.example.miniprojectakthemmalek.view.adapter.GroupsGridAdapter;
import com.example.miniprojectakthemmalek.view.adapter.InvitationAdapter;

import java.util.List;

public class ShowEventsActivity extends AppCompatActivity {
RecyclerView eventsRV;
String connectedUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);
eventsRV= findViewById(R.id.eventsRV);
        connectedUsername=getIntent().getStringExtra("username");

        EventRepository.getInstance().getAllEvents(new EventRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<Event> groupList) {


                EventsAdapter eventsAdapter=new EventsAdapter(getApplicationContext(),groupList);
                eventsAdapter.setEvents(groupList);
                eventsAdapter.setConnectedUsername(connectedUsername);
                eventsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                eventsRV.setAdapter(eventsAdapter);

            }
        });

            }
}







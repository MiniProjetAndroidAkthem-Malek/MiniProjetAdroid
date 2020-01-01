package com.example.miniprojectakthemmalek.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Event;
import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.repositories.EventRepository;
import com.example.miniprojectakthemmalek.model.repositories.FollowRepository;
import com.example.miniprojectakthemmalek.view.adapter.EventsAdapter;
import com.example.miniprojectakthemmalek.view.adapter.FriendsAdapter;

import java.util.List;

public class ShowFriendsActivity extends AppCompatActivity {
RecyclerView eventsRV;
String connectedUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);
eventsRV= findViewById(R.id.eventsRV);
        connectedUsername=getIntent().getStringExtra("username");

        FollowRepository.getInstance().getWhatFollows(connectedUsername, new FollowRepository.getManyCallback() {
            @Override
            public void getManyOneFollow(List<Follow> follow) {
                FriendsAdapter friendsAdapter= new FriendsAdapter(getApplicationContext(),follow);
                friendsAdapter.setFollowers(follow);
                friendsAdapter.setConnectedUsername(connectedUsername);
                eventsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                eventsRV.setAdapter(friendsAdapter);



            }
        });






            }
}







package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Enums.Role;
import com.example.miniprojectakthemmalek.model.entities.Event;
import com.example.miniprojectakthemmalek.model.entities.EventUser;

import com.example.miniprojectakthemmalek.model.repositories.EventRepository;
import com.example.miniprojectakthemmalek.model.repositories.EventUserRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class EventMainPageActivity extends AppCompatActivity {
    AppCompatButton JoinEventBtn,UnjoinEventBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_overlaps);
        final String Event_name,Event_State,Event_Contry,Event_Description,connectedUsername;
        final TextView NameEventTv,AdressEventTv,DescriptionEventTv;
        final FloatingActionButton settingsadd;
        NameEventTv=findViewById(R.id.NameEventTv);
        AdressEventTv=findViewById(R.id.AddressEventTv);
        DescriptionEventTv=findViewById(R.id.DescriptionEventTv);
        settingsadd = findViewById(R.id.settingsAdd);
        Event_name=getIntent().getStringExtra("Event_name");
        connectedUsername=getIntent().getStringExtra("username");
        Event_Contry=getIntent().getStringExtra("Event_Contry");
        Event_State=getIntent().getStringExtra("Event_State");
        Event_Description=getIntent().getStringExtra("Event_Description");
        System.out.println(Event_name);

        JoinEventBtn=findViewById(R.id.JoinEventBtn);
        UnjoinEventBtn=findViewById(R.id.UnJoinEventBtn);




        EventUserRepository.getInstance().getUserEventByName(connectedUsername,Event_name, new EventUserRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<EventUser> list) {
                System.out.println("200000");
                if (list.size()!=0) {
                    if(list.get(0).getRole()== Role.ADMIN  ) {
                        JoinEventBtn.setVisibility(View.GONE);
                        UnjoinEventBtn.setVisibility(View.GONE);
                    }

                }

            }
        });


        EventUserRepository.getInstance().getUserEventByName(connectedUsername,Event_name, new EventUserRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<EventUser> list) {
                System.out.println("200000");
                if (list.size()!=0) {
                    if(list.get(0).getRole()==Role.USER ) {
                        JoinEventBtn.setVisibility(View.GONE);
                        UnjoinEventBtn.setVisibility(View.VISIBLE);
                    }

                }

            }
        });


        UnjoinEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventUserRepository.getInstance().deleteUserEvent(connectedUsername, Event_name, new EventUserRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {
                        System.out.println("user left the event");
                        JoinEventBtn.setVisibility(View.VISIBLE);
                        UnjoinEventBtn.setVisibility(View.GONE);

                    }
                });
            }
        });








        JoinEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventUserRepository.getInstance().addUserEvent(new EventUser(connectedUsername,Event_name, Role.USER) , new EventUserRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {
                        System.out.println("mmmmmmmmmmmmmmm");
                        JoinEventBtn.setVisibility(View.GONE);
                        UnjoinEventBtn.setVisibility(View.VISIBLE);


                    }
                });
            }
        });






        EventUserRepository.getInstance().getUserEventByName(connectedUsername,Event_name, new EventUserRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<EventUser> list) {
                System.out.println("200000");
                if (list.size()!=0) {
                    if(list.get(0).getRole()== Role.ADMIN  ) {
                        settingsadd.setVisibility(View.VISIBLE);
                    }

                }

            }
        });




settingsadd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent =new Intent(getApplicationContext(),SettingsEventActivity.class);
        intent.putExtra("nom",Event_name);
        intent.putExtra("description",Event_Description);
        intent.putExtra("state",Event_State);
        intent.putExtra("contry",Event_Contry);
        intent.putExtra("username",connectedUsername);

        startActivity(intent);
    }
});






        EventRepository.getInstance().getEventByName(Event_name, new EventRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<Event> groupList) {
                System.out.println(groupList);
                NameEventTv.setText(groupList.get(0).getNom());
                DescriptionEventTv.setText(groupList.get(0).getDescription());
                AdressEventTv.setText("the event will be in : "+groupList.get(0).getState()+","+groupList.get(0).getContry());



            }
        });
        EventRepository.getInstance().getAllEvents(new EventRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<Event> groupList) {
                //System.out.println(groupList);

                System.out.println(groupList.get(0).getState());

            }
        });







    }
}

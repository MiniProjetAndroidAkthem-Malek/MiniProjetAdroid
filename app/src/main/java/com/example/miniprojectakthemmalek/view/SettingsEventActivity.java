package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Event;
import com.example.miniprojectakthemmalek.model.repositories.EventRepository;
import com.example.miniprojectakthemmalek.model.repositories.EventUserRepository;

public class SettingsEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_event);
        Button delete,update;
        final String nom,username,description,contry,state;
        final AutoCompleteTextView NameEvent,DescriptionEvent,StateEvent,ContryEvent;
        NameEvent = findViewById(R.id.EventNameSettings);
        DescriptionEvent = findViewById(R.id.DescriptionEventSettings);
        StateEvent = findViewById(R.id.StateEventSettings);
        ContryEvent= findViewById(R.id.ContryEventSettings);
        delete=findViewById(R.id.deletemyevent);
        update=findViewById(R.id.UpdateEventBtn);
        nom = getIntent().getStringExtra("nom");
        description = getIntent().getStringExtra("description");
        contry = getIntent().getStringExtra("contry");
        state = getIntent().getStringExtra("state");
        username = getIntent().getStringExtra("username");

        NameEvent.setText(nom);
        DescriptionEvent.setText(description);
        ContryEvent.setText(contry);
        StateEvent.setText(state);



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventUserRepository.getInstance().deleteAllUserEvent(nom, new EventUserRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {
                        System.out.println("all users deleted");
                    }
                });

                EventRepository.getInstance().deleteEvent(nom, new EventRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {




                        System.out.println("deleted succesfully ! ");
                        Intent intent =new Intent(getApplicationContext(),ShowEventsActivity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);



                    }
                });
            }
        });


update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        EventRepository.getInstance().updateEvent(new Event(nom, DescriptionEvent.getText().toString(), StateEvent.getText().toString(), ContryEvent.getText().toString()), nom, new EventRepository.addingCallback() {
            @Override
            public void addingCallback(int code) {
                System.out.println("updated Successfully!");
                Intent intent=new Intent(getApplicationContext(), EventMainPageActivity.class);
                intent.putExtra("Event_name",nom);
                intent.putExtra("Event_Description",DescriptionEvent.getText().toString());
                intent.putExtra("Event_State",StateEvent.getText().toString());
                intent.putExtra("Event_Contry",ContryEvent.getText().toString());
                intent.putExtra("username",username);

                getApplicationContext().startActivity(intent);


            }
        });
    }
});





    }
}

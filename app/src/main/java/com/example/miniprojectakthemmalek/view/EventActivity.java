package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Enums.Role;
import com.example.miniprojectakthemmalek.model.entities.Event;
import com.example.miniprojectakthemmalek.model.entities.EventUser;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.example.miniprojectakthemmalek.model.repositories.EventRepository;
import com.example.miniprojectakthemmalek.model.repositories.EventUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.FollowRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupUserRepository;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_address);
        final EditText EventNameET ;
        final EditText EventDescriptionET;
        final EditText EventStateET;
        final EditText EventContryET;
        final LinearLayout linearlayout;
        final Button addEventBtn;
        final String username;
        EventNameET = findViewById(R.id.EventsNameEt);
        EventDescriptionET = findViewById(R.id.EventsDescriptionEt);
        EventStateET = findViewById(R.id.EventStateEt);
        EventContryET = findViewById(R.id.EventsContryEt);
        addEventBtn = findViewById(R.id.addEventBtn);
        linearlayout = findViewById(R.id.linearlayout);
        username = getIntent().getStringExtra("username");
        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventRepository.getInstance().addEvent(new Event(EventNameET.getText().toString(),EventDescriptionET.getText().toString(),EventStateET.getText().toString(),EventContryET.getText().toString(),username), new FollowRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {
            if (code==200) {

                Toast.makeText(getApplicationContext(),"Event Created Successfully ! ",Toast.LENGTH_SHORT).show();
                EventUserRepository.getInstance().addUserEvent(new EventUser(username, EventNameET.getText().toString(), Role.ADMIN), new EventUserRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {
                        System.out.println("Admin is set !");
                    }
                });
                Button btn=new Button(getApplicationContext());

                btn.setText("Continue to your new event");

                linearlayout.addView(btn,linearlayout.indexOfChild(addEventBtn));
                linearlayout.removeView(addEventBtn);


                btn.setBackgroundResource(R.drawable.btn_rounded_accent);
                btn.setTextColor(Color.WHITE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {




                        Intent intent=new Intent(getApplicationContext(),EventMainPageActivity.class);








                        intent.putExtra("username",username);
                        intent.putExtra("Event_name",EventNameET.getText().toString());
                        intent.putExtra("Event_Contry",EventContryET.getText().toString());
                        intent.putExtra("Event_State",EventStateET.getText().toString());
                        intent.putExtra("Event_Description",EventDescriptionET.getText().toString());
                        startActivity(intent);
                    }
                });
               // EventNameET.setText("");
                //EventDescriptionET.setText("");
                //EventStateET.setText("");
                //EventContryET.setText("");











            }
                    }
                });







            }





    });


            }
            }


package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.example.miniprojectakthemmalek.model.entities.Role;
import com.example.miniprojectakthemmalek.model.entities.Status;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.FollowRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends AppCompatActivity {

    String connectedUsername;
    AppCompatEditText group_name,group_description;
    Button bt_create;
    AppCompatAutoCompleteTextView adminAutocomplete;
    NachoTextView nachoTextView;
    List<String> selectedUsernames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_text_area);
        group_name=findViewById(R.id.group_name);
        group_description =findViewById(R.id.group_description);
        bt_create = findViewById(R.id.bt_create);
        adminAutocomplete = findViewById(R.id.adminAutocomplete);
        selectedUsernames=new ArrayList<String>();
        nachoTextView = findViewById(R.id.et_tag);

        connectedUsername=getIntent().getStringExtra("username").toString();

        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!group_name.getText().toString().equals(""))
                {

                GroupRepository.getInstance().addGroup(new Group(group_name.getText().toString(), group_description.getText().toString(), connectedUsername), new FollowRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {

                        if(code==200)
                        {
                        if(selectedUsernames.size()>0)
                        {
                            for(String username:selectedUsernames)
                            {
                                GroupUserRepository.getInstance().addGroupUser(new GroupUser(username, group_name.getText().toString(), Role.ADMIN, Status.COMFIRMED), new GroupUserRepository.addingCallback() {
                                    @Override
                                    public void addingCallback(int code) {

                                    }
                                });
                            }

                        }

                        group_name.setText("");
                        group_description.setText("");
                        nachoTextView.setText("");

                        }


                    }
                });
            }


            }





        });


        UserRepository.getInstance().getAllUsers(new UserRepository.getAllUserCallBack() {
            @Override
            public void onResponse(List<User> user) {

        List<String> usernames=new ArrayList<String>();
        for(User u:user)
        {
            usernames.add(u.getUsername());
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.auto_complete_item_adapter,R.id.usernameAutoCompleteTextView,usernames);

        adminAutocomplete.setAdapter(adapter);

        adminAutocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(!selectedUsernames.contains(adminAutocomplete.getText().toString()))
                {
                    selectedUsernames.add(adminAutocomplete.getText().toString());
                    adminAutocomplete.setText("");
                }

                nachoTextView.setText(selectedUsernames);
                nachoTextView.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);

            }
        });


            }
        });


    }
}

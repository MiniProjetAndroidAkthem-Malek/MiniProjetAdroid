package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.example.miniprojectakthemmalek.model.entities.Invitation;
import com.example.miniprojectakthemmalek.model.entities.Role;
import com.example.miniprojectakthemmalek.model.entities.Status;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.GroupRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.InvitationRepository;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.adapter.AdminGroupAdapter;
import com.example.miniprojectakthemmalek.view.adapter.InvitationGroupAdapter;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import java.util.ArrayList;
import java.util.List;

public class SettingsGroupActivity extends AppCompatActivity {

    RecyclerView recyclerView,recyclerViewAdmin;
    InvitationGroupAdapter invitationGroupAdapter;
    AppCompatAutoCompleteTextView adminAutocomplete;
    List<String> selectedUsernames;
    AppCompatButton addAdmin;
    CardView adminsGroupCardView,invitationCardView;
    String connectedUsername,groupName;
    TextView adminsTextView,invitationTextView;

    AdminGroupAdapter adminGroupAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_group);
        recyclerView=findViewById(R.id.recyclerView);
        adminAutocomplete = findViewById(R.id.adminAutocomplete);
        adminsGroupCardView=findViewById(R.id.adminsGroupCardView);
        recyclerViewAdmin=findViewById(R.id.recyclerViewAdmin);

        adminsTextView=findViewById(R.id.adminsTextView);
        invitationTextView=findViewById(R.id.invitationTextView);

        invitationCardView=findViewById(R.id.invitationCardView);
        addAdmin=findViewById(R.id.addAdmin);
        connectedUsername=getIntent().getStringExtra("username");


        selectedUsernames=new ArrayList<String>();


        groupName=getIntent().getStringExtra("groupName");
        invitationGroupAdapter=new InvitationGroupAdapter();
        adminGroupAdapter=new AdminGroupAdapter();
        adminGroupAdapter.setConnectedUsername(connectedUsername);
        adminGroupAdapter.setGroup_name(groupName);


        GroupUserRepository.getInstance().getUserGroupByRoleAndStatus(""+groupName,Role.USER.toString(), Status.WAITING.toString(), new GroupUserRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<GroupUser> list) {
                invitationTextView.setText("Invitations ("+list.size()+")");

                invitationGroupAdapter.setInvitationList(list);
                invitationGroupAdapter.setConnectedUsername(connectedUsername);
                invitationGroupAdapter.setGroup_name(groupName);
                recyclerView.setAdapter(invitationGroupAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setHasFixedSize(false);
            }
        });


        invitationCardView.setVisibility(View.INVISIBLE);
        adminsGroupCardView.setVisibility(View.INVISIBLE);

    GroupUserRepository.getInstance().getUserGroup(groupName, connectedUsername, new GroupUserRepository.getAllGroupCallBack() {
        @Override
        public void onResponse(List<GroupUser> list) {

            if(list.size()!=0)
            {
                GroupUser groupUser=list.get(0);
                if(groupUser.getRole().toString().equals(Role.ADMIN.toString()))
                {
                    invitationCardView.setVisibility(View.VISIBLE);
                }
            }
        }
    });

        GroupRepository.getInstance().getGroupByName(groupName, new GroupRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<Group> groupList) {

                if(groupList.size()!=0)
                {
                    Group group=groupList.get(0);
                    if(group.getCreator().toString().equals(connectedUsername))
                    {


                        invitationCardView.setVisibility(View.VISIBLE);
                        adminsGroupCardView.setVisibility(View.VISIBLE);

                    }
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

                        adminAutocomplete.setText(selectedUsernames.toString());
                      //  nachoTextView.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);

                    }
                });


            }
        });

        addAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(selectedUsernames.size()!=0)
            {


                for(final String selectedUsername:selectedUsernames)
                {

                    final GroupUser groupUser=new GroupUser(selectedUsername,groupName,Role.ADMIN, Status.WAITING);


                    GroupUserRepository.getInstance().getUserGroup(groupName, selectedUsername.toString(), new GroupUserRepository.getAllGroupCallBack() {
                        @Override
                        public void onResponse(List<GroupUser> list) {

                            if(list.size()==0)
                            {
                                GroupUserRepository.getInstance().addGroupUser(groupUser, new GroupUserRepository.addingCallback() {
                                    @Override
                                    public void addingCallback(int code) {
                                        if(code ==200)
                                        {
                                            Invitation invitation=new Invitation(groupName,selectedUsername,"A creator of group want you to become an admin for his group "+groupName,"GROUP_ADMIN");
                                            InvitationRepository.getInstance().addInvitation(invitation, new InvitationRepository.addingCallback() {
                                                @Override
                                                public void addingCallback(int code) {

                                                    if(code==200)
                                                        adminAutocomplete.setText("");


                                                }
                                            });

                                        }

                                    }

                                });

                            }else{



                                GroupUserRepository.getInstance().updateRoleUser(groupUser, new GroupUserRepository.addingCallback() {
                                    @Override
                                    public void addingCallback(int code) {
                                        if(code==200)
                                            adminAutocomplete.setText("");
                                    }
                                });

                            }


                        }
                    });


                }


            }

            }

        });




        GroupUserRepository.getInstance().getUserGroupByRoleAndStatus(groupName,Role.ADMIN.toString(), Status.COMFIRMED.toString(), new GroupUserRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<GroupUser> list) {
                adminsTextView.setText("admins ("+list.size()+")");

                adminGroupAdapter.setGroupUsers(list);
                recyclerViewAdmin.setAdapter(adminGroupAdapter);
                recyclerViewAdmin.setHasFixedSize(false);
                recyclerViewAdmin.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        });


    }


}


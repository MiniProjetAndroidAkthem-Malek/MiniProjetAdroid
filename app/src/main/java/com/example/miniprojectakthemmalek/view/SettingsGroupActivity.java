package com.example.miniprojectakthemmalek.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
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
import com.example.miniprojectakthemmalek.model.entities.Enums.Role;
import com.example.miniprojectakthemmalek.model.entities.Enums.Status;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.GroupRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.InvitationRepository;
import com.example.miniprojectakthemmalek.model.repositories.NotificationRepositories.NotificationRepository;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.adapter.AdminGroupAdapter;
import com.example.miniprojectakthemmalek.view.adapter.InvitationGroupAdapter;
import com.example.miniprojectakthemmalek.view.utils.ImageManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SettingsGroupActivity extends AppCompatActivity {

    RecyclerView recyclerView,recyclerViewAdmin;
    InvitationGroupAdapter invitationGroupAdapter;
    AppCompatAutoCompleteTextView adminAutocomplete;
    List<String> selectedUsernames;
    AppCompatButton addAdmin;
    CardView adminsGroupCardView,invitationCardView;
    String connectedUsername,groupName;
    TextView adminsTextView,invitationTextView;

    FloatingActionButton fabAdd,fabUpload;
    CircleImageView imageView;

    AdminGroupAdapter adminGroupAdapter;
    ImageManager imageManager;

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private final static int ALL_PERMISSIONS_RESULT = 107;
    private final static int IMAGE_RESULT = 200;
    Bitmap mBitmap;
    LinearLayout loadingUpload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_group);
        recyclerView=findViewById(R.id.recyclerView);
        adminAutocomplete = findViewById(R.id.adminAutocomplete);
        adminsGroupCardView=findViewById(R.id.adminsGroupCardView);
        recyclerViewAdmin=findViewById(R.id.recyclerViewAdmin);
        fabAdd=findViewById(R.id.fabAdd);
        imageView=findViewById(R.id.imageView);
        fabUpload=findViewById(R.id.fabUpload);
        adminsTextView=findViewById(R.id.adminsTextView);
        invitationTextView=findViewById(R.id.invitationTextView);
        loadingUpload=findViewById(R.id.loadingUpload);

        invitationCardView=findViewById(R.id.invitationCardView);
        addAdmin=findViewById(R.id.addAdmin);
        connectedUsername=getIntent().getStringExtra("username");

        imageManager=new ImageManager(getApplicationContext());

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
                                                        NotificationRepository.getInstance().sendNotificationToPatner("A creator of group want you to become an admin for his group","Volks",selectedUsername);



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



        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(imageManager.getPickImageChooserIntent(), IMAGE_RESULT);

            }
        });


        fabUpload.setVisibility(View.INVISIBLE);


        fabUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBitmap != null) {
                    loadingUpload.setVisibility(View.VISIBLE);

                    imageManager.multipartImageUpload(mBitmap, groupName,"group",loadingUpload);
                    fabUpload.setVisibility(View.INVISIBLE);
                } else {


                }
            }
        });

        askPermissions();




    }


    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);

            }
        }
        return true;
    }

    private void askPermissions() {
        permissions.add(CAMERA);
        permissions.add(WRITE_EXTERNAL_STORAGE);
        permissions.add(READ_EXTERNAL_STORAGE);
        permissionsToRequest = findUnAskedPermissions(permissions);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (permissionsToRequest.size() > 0)
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getApplicationContext())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                        }
                                    });
                            return;
                        }
                    }

                }

                break;
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {

            //  ImageView imageView = findViewById(R.id.imageView);

            if (requestCode == IMAGE_RESULT) {

                String filePath = imageManager.getImageFilePath(data);
                if (filePath != null) {
                    fabUpload.setVisibility(View.VISIBLE);
                    mBitmap = BitmapFactory.decodeFile(filePath);
                    imageView.setImageBitmap(mBitmap);
                }
            }

        }

    }

}
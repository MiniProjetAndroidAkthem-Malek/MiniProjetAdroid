package com.example.miniprojectakthemmalek.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Event;
import com.example.miniprojectakthemmalek.model.repositories.EventRepository;
import com.example.miniprojectakthemmalek.model.repositories.EventUserRepository;
import com.example.miniprojectakthemmalek.view.utils.ImageManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SettingsEventActivity extends AppCompatActivity {

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private final static int ALL_PERMISSIONS_RESULT = 107;
    private final static int IMAGE_RESULT = 200;
    Bitmap mBitmap;
    FloatingActionButton fabUpload,fabAdd;
    CircleImageView imageView;
    LinearLayout loadingUpload;
    ImageManager imageManager;
    String place;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_event);
        Button delete,update;
        final String nom,username,description,contry,state;
        final AutoCompleteTextView PlaceEvent, NameEvent,DescriptionEvent,StateEvent,ContryEvent;
        NameEvent = findViewById(R.id.EventNameSettings);
        DescriptionEvent = findViewById(R.id.DescriptionEventSettings);
        PlaceEvent = findViewById(R.id.PlaceEventSettings);
        StateEvent = findViewById(R.id.StateEventSettings);
        ContryEvent= findViewById(R.id.ContryEventSettings);
        delete=findViewById(R.id.deletemyevent);
        update=findViewById(R.id.UpdateEventBtn);
        loadingUpload=findViewById(R.id.loadingUpload);
        nom = getIntent().getStringExtra("nom");
        description = getIntent().getStringExtra("description");
        contry = getIntent().getStringExtra("contry");
        state = getIntent().getStringExtra("state");
        place = getIntent().getStringExtra("place");
        username = getIntent().getStringExtra("username");
        imageManager=new ImageManager(getApplicationContext());

        fabUpload=findViewById(R.id.fabUpload);
        fabAdd=findViewById(R.id.fabAdd);
        imageView=findViewById(R.id.imageView);

        NameEvent.setText(nom);
        DescriptionEvent.setText(description);
        ContryEvent.setText(contry);
        StateEvent.setText(state);
        PlaceEvent.setText(place);

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


        Event event=new Event(nom, DescriptionEvent.getText().toString(), StateEvent.getText().toString(), ContryEvent.getText().toString());
        event.setPlace(PlaceEvent.getText().toString());

        EventRepository.getInstance().updateEvent(event, nom, new EventRepository.addingCallback() {
            @Override
            public void addingCallback(int code) {
                System.out.println("updated Successfully!");
                Intent intent=new Intent(getApplicationContext(), EventMainPageActivity.class);
                intent.putExtra("Event_name",nom);
                intent.putExtra("Event_Description",DescriptionEvent.getText().toString());
                intent.putExtra("Event_State",StateEvent.getText().toString());
                intent.putExtra("Event_Contry",ContryEvent.getText().toString());
                intent.putExtra("Event_Place",PlaceEvent.getText().toString());
                intent.putExtra("username",username);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);


            }
        });
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


                    imageManager.multipartImageUpload(mBitmap, nom,"event",loadingUpload);
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

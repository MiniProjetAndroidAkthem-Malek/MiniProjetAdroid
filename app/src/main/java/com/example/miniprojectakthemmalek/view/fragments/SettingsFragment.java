package com.example.miniprojectakthemmalek.view.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Invitation;
import com.example.miniprojectakthemmalek.model.entities.Status;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.InvitationRepository;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.AuthentificationActivity;
import com.example.miniprojectakthemmalek.view.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText firstnameSettings,lastnameSettings,addressSettings,phonenumberSettings,jobSettings,partnerSettings;
    Button bt_change,logoutSettings;
    FloatingActionButton fab_done;
    int x=0;
    SessionManager sessionManager;
    User user;
   AppCompatButton deletemyaccount,bt_pick_color;
    AppCompatSeekBar red,green,blue;


    AppCompatAutoCompleteTextView partnerAutocomplete;
    NachoTextView nachoTextView;
    List<String> selectedUsernames;


    int r=0;
    int g=0;
    int b=0;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_settings, container, false);
        fab_done=rootView.findViewById(R.id.fab_done);

        sessionManager = new SessionManager(getContext());

        firstnameSettings = rootView.findViewById(R.id.firstnameSettings);
        lastnameSettings = rootView.findViewById(R.id.lastnameSettings);
        addressSettings = rootView.findViewById(R.id.addressSettings);
        jobSettings = rootView.findViewById(R.id.jobSettings);
        phonenumberSettings = rootView.findViewById(R.id.phonenumberSettings);
        partnerAutocomplete = rootView.findViewById(R.id.partnerAutocomplete);
        bt_change=rootView.findViewById(R.id.bt_change);
        deletemyaccount=rootView.findViewById(R.id.deletemyaccount);
        logoutSettings=rootView.findViewById(R.id.logoutSettings);
        bt_pick_color = rootView.findViewById(R.id.bt_pick_color);
        nachoTextView=rootView.findViewById(R.id.et_tag);
        selectedUsernames=new ArrayList<String>();

        red=rootView.findViewById(R.id.red);
        green =rootView.findViewById(R.id.green);
        blue = rootView.findViewById(R.id.blue);

        red.setMax(255);
        red.setMin(0);

        green.setMin(0);
        green.setMax(255);

        blue.setMin(0);
        blue.setMax(255);

        user = sessionManager.getUser(getArguments().getString("username"),1);

        red.setProgress(user.getTheme_r());
        green.setProgress(user.getTheme_g());
        blue.setProgress(user.getTheme_b());
        bt_pick_color.setBackgroundColor(Color.rgb(red.getProgress(),green.getProgress(),blue.getProgress()));

        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                System.out.println(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                bt_pick_color.setBackgroundColor(Color.rgb(seekBar.getProgress(),green.getProgress(),blue.getProgress()));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                bt_pick_color.setBackgroundColor(Color.rgb(seekBar.getProgress(),green.getProgress(),blue.getProgress()));
                sessionManager.updateThemeForUser(user.getUsername(),seekBar.getProgress(),green.getProgress(),blue.getProgress());
            }
        });


        green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                bt_pick_color.setBackgroundColor(Color.rgb(red.getProgress(),seekBar.getProgress(),blue.getProgress()));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                bt_pick_color.setBackgroundColor(Color.rgb(red.getProgress(),seekBar.getProgress(),blue.getProgress()));
                sessionManager.updateThemeForUser(user.getUsername(),red.getProgress(),seekBar.getProgress(),blue.getProgress());
                System.out.println("r= "+sessionManager.getUser(user.getTheme_r()));


            }
        });

blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

        bt_pick_color.setBackgroundColor(Color.rgb(red.getProgress(),green.getProgress(),seekBar.getProgress()));

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        bt_pick_color.setBackgroundColor(Color.rgb(red.getProgress(),green.getProgress(),seekBar.getProgress()));
        sessionManager.updateThemeForUser(user.getUsername(),red.getProgress(),green.getProgress(),seekBar.getProgress());

    }
});

        partnerAutoComplete();
        System.out.println(user);
        if(user.getFirst_name()==null || user.getFirst_name()=="")
        {

            firstnameSettings.setText("");

        }else{
            firstnameSettings.setText(user.getFirst_name().toString());

        }

        if(user.getLast_name()==null || user.getLast_name()=="")
        {

            lastnameSettings.setText("");

        }else{
            lastnameSettings.setText(user.getLast_name().toString());

        }

        if(user.getAddress()==null || user.getAddress()=="")
        {

            addressSettings.setText("");

        }else{

            addressSettings.setText(user.getAddress().toString());

        }

        if(user.getJob()==null || user.getJob()=="")
        {

            jobSettings.setText("");

        }else{
            jobSettings.setText(user.getJob().toString());

        }

        if(user.getPhone_number()==null || user.getPhone_number()==0)
        {

            phonenumberSettings.setText("");

        }else{
            phonenumberSettings.setText(user.getPhone_number().toString());

        }

        if(user.getPartner()==null || user.getPartner()=="")
        {

            partnerAutocomplete.setText("");

        }else{

        partnerAutocomplete.setText(user.getPartner().toString());


        }


        logoutSettings.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        sessionManager.updateConnectionStatusForUser(user.getUsername(),0);
        Intent intent=new Intent(getContext(), AuthentificationActivity.class);
        startActivity(intent);


    }
});

        deletemyaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());

                dialog.setTitle("Deleting");
                dialog.setMessage("Are you sure do you want to delete your account ? :/" );
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        UserRepository.getInstance().deleteUser(user.getUsername());
                        sessionManager.deleteSessionForUser(user.getUsername());

                        Intent intent=new Intent(getContext(), AuthentificationActivity.class);
                        startActivity(intent);

                    }
                })
                        .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                            }
                        });
                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });

        bt_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.setFirst_name(firstnameSettings.getText().toString());
                user.setLast_name(lastnameSettings.getText().toString());
                user.setAddress(addressSettings.getText().toString());
                user.setPhone_number(Long.parseLong(phonenumberSettings.getText().toString()));
                user.setJob(jobSettings.getText().toString());

                if(selectedUsernames.size()>0)
                {


                    InvitationRepository.getInstance().getInvitationForUser(user.getUsername(), selectedUsernames.get(0).toString(), new InvitationRepository.getManyCallback() {
                        @Override
                        public void getMany(List<Invitation> invitationList) {

                            if(invitationList.size()==0)
                            {

                                InvitationRepository.getInstance().addInvitation(new Invitation(user.getUsername(), selectedUsernames.get(0).toString(), "A partner's invitation has been received from"+ user.getUsername(),"PARTNERSHIP"), new InvitationRepository.addingCallback() {
                                    @Override
                                    public void addingCallback(int code) {

                                    }
                                });

                            }


                        }
                    });

                }



                UserRepository.getInstance().updateUser(user);
                sessionManager.updateUser(user);

            }
        });

        fab_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProfileFragment profileFragment=new ProfileFragment();
                Bundle bundle=new Bundle();
                bundle.putString("username",user.getUsername());
                profileFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frameProfile,profileFragment).commit();
            }
        });



        return rootView;
    }

    public void disableEditing(EditText editText)
    {
        editText.setKeyListener(null);
    }

    public void enableEditing(EditText editText)
    {
        editText.setKeyListener(editText.getKeyListener());
    }
public void partnerAutoComplete()
{
    UserRepository.getInstance().getAllUsers(new UserRepository.getAllUserCallBack() {
        @Override
        public void onResponse(List<User> user) {

            List<String> usernames=new ArrayList<String>();
            for(User u:user)
            {
                usernames.add(u.getUsername());
            }

            ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.auto_complete_item_adapter,R.id.usernameAutoCompleteTextView,usernames);

            partnerAutocomplete.setAdapter(adapter);

            partnerAutocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if(!selectedUsernames.contains(partnerAutocomplete.getText().toString()))
                    {

                        if(selectedUsernames.size()==0)
                            {
                                selectedUsernames.add(partnerAutocomplete.getText().toString());
                                partnerAutocomplete.setText("");
                            }
                    }

                    nachoTextView.setText(selectedUsernames);
                    nachoTextView.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);


                }
            });


        }
    });
}


public void updateColor(AppCompatButton bt_pick_color,int r,int g ,int b)
{


}


}

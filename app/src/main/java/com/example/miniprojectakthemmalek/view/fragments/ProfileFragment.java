package com.example.miniprojectakthemmalek.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.view.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SessionManager sessionManager;
    User user;

    TextView firstNameLabel,lastNameLabel,addressLabel,phoneNumberLabel,usernameLabel;
    FloatingActionButton settingBtn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

        View rootView=inflater.inflate(R.layout.fragment_profile, container, false);
        sessionManager = new SessionManager(getContext());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


       user = sessionManager.getUser(getArguments().getString("username"),1);
        System.out.println(user);
        firstNameLabel=rootView.findViewById(R.id.firstNameLabel);
        lastNameLabel=rootView.findViewById(R.id.lastNameLabel);
        addressLabel=rootView.findViewById(R.id.addressLabel);
        phoneNumberLabel=rootView.findViewById(R.id.phoneNumberLabel);
        usernameLabel=rootView.findViewById(R.id.usernameLabel);
        settingBtn=rootView.findViewById(R.id.settingsAdd);

        firstNameLabel.setText(user.getFirst_name());
        lastNameLabel.setText(user.getLast_name());
        addressLabel.setText(user.getAddress());
        phoneNumberLabel.setText(user.getPhone_number().toString());
        usernameLabel.setText(user.getUsername());


settingBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SettingsFragment settingsFragment =new SettingsFragment();
        Bundle bundle =new Bundle();
        bundle.putString("username",user.getUsername());
        settingsFragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.frameProfile,settingsFragment).commit();


    }
});



        return rootView;
    }



}

package com.example.miniprojectakthemmalek.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.HomeActivity;
import com.example.miniprojectakthemmalek.view.SessionManager;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


public class AddAccountFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MaterialRippleLayout addAccount;
    TextInputEditText usernameAddAccount;
    TextInputEditText passwordAddAccount;
    SessionManager sessionManager;
    View parent_view;
    ImageButton backAccount;
    public AddAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddAccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddAccountFragment newInstance(String param1, String param2) {
        AddAccountFragment fragment = new AddAccountFragment();
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
        View rootView =inflater.inflate(R.layout.fragment_add_account, container, false);

        addAccount=rootView.findViewById(R.id.AddAcount);
        usernameAddAccount = rootView.findViewById(R.id.usernameAddAcount);
        passwordAddAccount= rootView.findViewById(R.id.passwordAddAcount);
        parent_view = rootView.findViewById(android.R.id.content);
        sessionManager=new SessionManager(getContext());
        backAccount=rootView.findViewById(R.id.backAccount);

        backAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.accountFrame,new AccountsFragment()).commit();
            }
        });







        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserRepository.getInstance().getOneUser(usernameAddAccount.getText().toString(), new UserRepository.getOneUserCallBack() {
                    @Override
                    public void onResponse(User user) {
                        if(user !=null)
                        {
                            System.out.println(user);
                            if(user.getUsername().equals(usernameAddAccount.getText().toString()) && user.getPassword().equals(passwordAddAccount.getText().toString()))
                            {

                                System.out.println("sssss = "+user);
                                if(sessionManager.getUser(user.getUsername())==null)
                                {
                                    sessionManager.openSessionForUser(user);

                                }else
                                {
                                    sessionManager.updateConnectionStatusForUser(user.getUsername(),1);
                                }


                                Intent intentHome =new Intent(getContext(), HomeActivity.class);
                                intentHome.putExtra("username",user.getUsername());
                                startActivity(intentHome);

                            }

                        }else{

                            Snackbar.make(parent_view, "Wrong password or username", Snackbar.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });






         return rootView;
    }


}

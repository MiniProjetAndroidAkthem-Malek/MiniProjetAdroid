package com.example.miniprojectakthemmalek.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.view.SessionManager;
import com.example.miniprojectakthemmalek.view.adapter.AccountsAdapter;
import com.example.miniprojectakthemmalek.view.database.AppDatabase;


public class AccountsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
RecyclerView recyclerview;
SessionManager sessionManager;
String username;
TextView addAnAccount;
    public AccountsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountsFragment newInstance(String param1, String param2) {
        AccountsFragment fragment = new AccountsFragment();
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
        // Inflate the layout for this fragment

        View rootView =inflater.inflate(R.layout.fragment_accounts, container, false);

        addAnAccount = rootView.findViewById(R.id.addAnAccount);
        addAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.accountFrame,new AddAccountFragment()).commit();
            }
        });

        sessionManager=new SessionManager(getContext());
        recyclerview = rootView.findViewById(R.id.recyclerViewAccount);

        AccountsAdapter accountAdapter=new AccountsAdapter(this);
        accountAdapter.setFilm_list(sessionManager.getAllUsers());

        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerview.setAdapter(accountAdapter);

        return rootView;
    }







}

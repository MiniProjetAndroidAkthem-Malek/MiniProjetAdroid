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

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Activities;
import com.example.miniprojectakthemmalek.model.repositories.ActivitiesRepository;
import com.example.miniprojectakthemmalek.view.SessionManager;
import com.example.miniprojectakthemmalek.view.adapter.AccountsAdapter;
import com.example.miniprojectakthemmalek.view.adapter.ActivityAdapter;

import java.util.List;


public class ActivityFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    String username;


    public ActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivityFragment newInstance(String param1, String param2) {
        ActivityFragment fragment = new ActivityFragment();
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
    final View  rootView =inflater.inflate(R.layout.fragment_activity, container, false);

    recyclerView = rootView.findViewById(R.id.recyclerView);

     username=getArguments().getString("username");

     ActivitiesRepository.getInstance().getAllByUsername(username, new ActivitiesRepository.getAllCallBack() {
         @Override
         public void onResponse(List<Activities> activities) {

             ActivityAdapter activityAdapter =new ActivityAdapter();
             activityAdapter.setActivitiesList(activities);
             activityAdapter.setUsername(username);
             recyclerView.setHasFixedSize(true);
             recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
             recyclerView.setAdapter(activityAdapter);

         }
     });



        return rootView;
    }





}

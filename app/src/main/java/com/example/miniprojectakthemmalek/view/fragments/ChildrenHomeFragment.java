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
import com.example.miniprojectakthemmalek.model.entities.Children;
import com.example.miniprojectakthemmalek.model.repositories.ChildrenRepository;
import com.example.miniprojectakthemmalek.view.adapter.ChildrenAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class ChildrenHomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FloatingActionButton childrenAdd;
    RecyclerView recyclerView;
    ChildrenAdapter childrenAdapter;
    String connectedUsername;
    String username;

    public ChildrenHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChildrenHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChildrenHomeFragment newInstance(String param1, String param2) {
        ChildrenHomeFragment fragment = new ChildrenHomeFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_children, container, false);
        connectedUsername=getArguments().getString("username");

        username=getArguments().getString("connectedUsername");

        childrenAdd = rootView.findViewById(R.id.childrenAdd);
        recyclerView=rootView.findViewById(R.id.recyclerView);


        if(username.equals(connectedUsername))
        {
        childrenAdd.setVisibility(View.VISIBLE);
        }


        childrenAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChildrenAddFragment childrenAddFragment=new ChildrenAddFragment();
                Bundle bundle=new Bundle();
                bundle.putString("username",connectedUsername);
                childrenAddFragment.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.frameProfile,childrenAddFragment).commit();

            }
        });




        ChildrenRepository.getInstance().getChildrenByParent(connectedUsername, new ChildrenRepository.getAllCallBack() {
            @Override
            public void onResponse(List<Children> childrenList) {

                childrenAdapter=new ChildrenAdapter();
                childrenAdapter.setChildrenList(childrenList);
                childrenAdapter.setConnectedUsername(username);
                childrenAdapter.setFragment(getFragmentManager());
                recyclerView.setAdapter(childrenAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }
        });

        return rootView;
    }


}

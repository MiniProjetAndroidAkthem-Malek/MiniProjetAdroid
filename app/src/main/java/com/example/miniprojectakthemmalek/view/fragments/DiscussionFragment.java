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
import com.example.miniprojectakthemmalek.model.entities.Message;
import com.example.miniprojectakthemmalek.model.repositories.MessageRepository;
import com.example.miniprojectakthemmalek.view.adapter.DiscussionAdapter;

import java.util.ArrayList;
import java.util.List;

public class DiscussionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DiscussionAdapter discussionAdapter;
    RecyclerView recyclerView;
    String connectedUsername;

    public DiscussionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscussionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscussionFragment newInstance(String param1, String param2) {
        DiscussionFragment fragment = new DiscussionFragment();
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

        View rootView =inflater.inflate(R.layout.fragment_discussion, container, false);
        recyclerView = rootView.findViewById(R.id.discussionRecyclerView);
        connectedUsername=getArguments().getString("connectedUsername");
        discussionAdapter=new DiscussionAdapter();

        discussionAdapter.setFragmentManager(getFragmentManager());
    MessageRepository.getInstance().getDiscussionsByConnectedUsername(connectedUsername, new MessageRepository.getManyCallback() {
        @Override
        public void getManyOneFollow(List<Message> messages) {

            if(messages.size()!=0)
            {
            List<Message> messageList = new ArrayList<Message>();

            discussionAdapter.setConnectedUsername(connectedUsername);
            discussionAdapter.setMessageList(getLastMessage(messages));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(false);
            recyclerView.setAdapter(discussionAdapter);
            }
        }
    });



        return rootView;
    }



    public List<Message> getLastMessage(List<Message> messageList)
    {
        List<Message> senderList=new ArrayList<Message>();
        List<Message> receiverList=new ArrayList<Message>();
        List<Message> finalList=new ArrayList<Message>();
        for(Message m :messageList )
        {

            if(m.getSender().equals(connectedUsername))
            {
                senderList.add(m);
            }else if(m.getReceiver().equals(connectedUsername))
            {
                receiverList.add(m);
            }

        }

        for(Message senderMessage : senderList)
        {
            Message receiverMessage=getMessageBySenderAndReceiver(receiverList,senderMessage.getReceiver(),senderMessage.getSender());

            if(senderMessage.getId()>receiverMessage.getId())
            {
                finalList.add(senderMessage);

            }else{

                finalList.add(receiverMessage);

            }
        }

            return finalList;
    }


public Message getMessageBySenderAndReceiver(List<Message> messageList,String sender,String receiver)
{
    Message message=new Message();
    for(Message m:messageList)
    {
        if(m.getSender().equals(sender) && m.getReceiver().equals(receiver))
        {
            return m;
        }
    }
return message;
}


}

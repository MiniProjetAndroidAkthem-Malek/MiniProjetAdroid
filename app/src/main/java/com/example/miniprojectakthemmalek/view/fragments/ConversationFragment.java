package com.example.miniprojectakthemmalek.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Message;
import com.example.miniprojectakthemmalek.model.repositories.MessageRepository;
import com.example.miniprojectakthemmalek.view.adapter.MessageAdapter;
import com.example.miniprojectakthemmalek.view.utils.Constants;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConversationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rvChatMessages;
    MessageAdapter messageAdapter;
    List<Message> messageList =new ArrayList<Message>();
    String username,connectedUsername;
    private Socket socket;
    EditText editTextChatMessage;
    TextView title;
    Button buttonSendMessage;


    public ConversationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConversationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConversationFragment newInstance(String param1, String param2) {
        ConversationFragment fragment = new ConversationFragment();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        username=getArguments().getString("username");
        connectedUsername=getArguments().getString("ConnectedUsername");

        try {

            socket = IO.socket("http://"+ Constants.IP_ADDRESS +":3000");
            socket.connect();
            socket.emit("join",connectedUsername);

        } catch (URISyntaxException e) {
            e.printStackTrace();

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_conversation, container, false);

        username=getArguments().getString("username");
        connectedUsername=getArguments().getString("ConnectedUsername");

        try {

            socket = IO.socket("http://"+Constants.IP_ADDRESS+":3000");
            socket.connect();
            socket.emit("join",connectedUsername);

        } catch (URISyntaxException e) {
            e.printStackTrace();

        }

        editTextChatMessage=rootView.findViewById(R.id.editTextChatMessage);
        buttonSendMessage=rootView.findViewById(R.id.buttonSendMessage);
        rvChatMessages=rootView.findViewById(R.id.rvChatMessages);

        messageAdapter = new MessageAdapter(getContext());
        messageAdapter.setUsername(username);
        messageAdapter.setConnectedUsername(connectedUsername);
        rvChatMessages.setHasFixedSize(true);
        rvChatMessages.setAdapter(messageAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rvChatMessages.setLayoutManager(linearLayoutManager);
        rvChatMessages.setItemAnimator(new DefaultItemAnimator());

        title=rootView.findViewById(R.id.title);
        title.setText(username);

       MessageRepository.getInstance().getMessages(connectedUsername, username, new MessageRepository.getManyCallback() {
            @Override
            public void getManyOneFollow(List<Message> messages) {

                if(messages != null)
                {

                    messageList.addAll(messages);

                    messageAdapter.setMessageList(messageList);

                    messageAdapter.notifyDataSetChanged();

                    rvChatMessages.setAdapter(messageAdapter);

                }
            }
        });

        socket.on("userjoinedthechat", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {


                if(getActivity()!=null)
                {


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = (String) args[0];

                        Toast.makeText(getContext(),data,Toast.LENGTH_SHORT).show();

                    }
                });


                }


            }
        });

        socket.on("userdisconnect", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {


            if(getActivity()!=null)
            {


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = (String) args[0];

                        Toast.makeText(getContext(),data,Toast.LENGTH_SHORT).show();

                    }
                });

            }



            }
        });

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(!editTextChatMessage.getText().toString().isEmpty()){

                    Date date =new Date();
                    long time = date.getTime();
                    Timestamp ts =new Timestamp(time);

                    socket.emit("messagedetection",connectedUsername,username,editTextChatMessage.getText().toString(),ts);

                    editTextChatMessage.setText(" ");

                    LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext());
                    linearLayoutManager1.scrollToPosition(messageList.size()-1);
                    rvChatMessages.setLayoutManager(linearLayoutManager1);

                    rvChatMessages.setAdapter(messageAdapter);


                }


            }
        });

        socket.on("message", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {


             if(getActivity()!=null)
             {

               getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject data = (JSONObject) args[0];
                        try {

                            String nickname = data.getString("senderNickname");
                            String receiver = data.getString("receiverNickname");
                            String message = data.getString("message");


                            Message m = new Message(nickname,receiver,message);
                            messageList.add(m);


                            messageAdapter.setMessageList(messageList);

                            messageAdapter.notifyDataSetChanged();

                            LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext());
                            linearLayoutManager1.scrollToPosition(messageList.size()-1);
                            rvChatMessages.setLayoutManager(linearLayoutManager1);
                            rvChatMessages.setAdapter(messageAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
             }
            }
        });




        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    socket.disconnect();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    socket.disconnect();
    }
}

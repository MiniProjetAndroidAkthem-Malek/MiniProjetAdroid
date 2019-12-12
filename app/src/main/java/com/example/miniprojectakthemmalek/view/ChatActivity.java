package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Message;
import com.example.miniprojectakthemmalek.model.repositories.FollowRepository;
import com.example.miniprojectakthemmalek.model.repositories.MessageRepository;
import com.example.miniprojectakthemmalek.view.adapter.MessageAdapter;
import com.example.miniprojectakthemmalek.view.adapter.PostAdapter;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hootsuite.nachos.NachoTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

private Socket socket;
TextView title;
String username,connectedUsername;
EditText editTextChatMessage;
Button buttonSendMessage;
RecyclerView rvChatMessages;
MessageAdapter messageAdapter;
List<Message> messages =new ArrayList<Message>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        username=getIntent().getStringExtra("username");
        connectedUsername=getIntent().getStringExtra("ConnectedUsername");

        try {

            socket = IO.socket("http://192.168.1.109:3000");
            socket.connect();
            socket.emit("join",connectedUsername);

        } catch (URISyntaxException e) {
            e.printStackTrace();

        }

        editTextChatMessage=findViewById(R.id.editTextChatMessage);
        buttonSendMessage=findViewById(R.id.buttonSendMessage);
        rvChatMessages=findViewById(R.id.rvChatMessages);

        messageAdapter = new MessageAdapter(getApplicationContext());
        messageAdapter.setUsername(username);
        messageAdapter.setConnectedUsername(connectedUsername);
        rvChatMessages.setHasFixedSize(true);
        rvChatMessages.setAdapter(messageAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        rvChatMessages.setLayoutManager(linearLayoutManager);
        rvChatMessages.setItemAnimator(new DefaultItemAnimator());

        title=findViewById(R.id.title);
        title.setText(username);


        socket.on("userjoinedthechat", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = (String) args[0];

                        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        socket.on("userdisconnect", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = (String) args[0];

                        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!editTextChatMessage.getText().toString().isEmpty()){

                    socket.emit("messagedetection",connectedUsername,editTextChatMessage.getText().toString());

                    editTextChatMessage.setText(" ");
                }


            }
        });

        socket.on("message", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject data = (JSONObject) args[0];
                        try {

                            String nickname = data.getString("senderNickname");
                            String message = data.getString("message");


                            Message m = new Message(nickname,"pp",message);

                            messages.add(m);

                            messageAdapter.setMessageList(messages);

                            messageAdapter.notifyDataSetChanged();

                            rvChatMessages.setAdapter(messageAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();

    }
}

package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Message;
import com.example.miniprojectakthemmalek.model.repositories.FollowRepository;
import com.example.miniprojectakthemmalek.model.repositories.MessageRepository;

public class ChatActivity extends AppCompatActivity {

    TextView title;
String username,connectedUsername;
EditText editTextChatMessage;
Button buttonSendMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        username=getIntent().getStringExtra("username");
        connectedUsername=getIntent().getStringExtra("ConnectedUsername");
        editTextChatMessage=findViewById(R.id.editTextChatMessage);
        buttonSendMessage=findViewById(R.id.buttonSendMessage);


        title=findViewById(R.id.title);
        title.setText(username);

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MessageRepository.getInstance().addFollow(new Message(connectedUsername, username, editTextChatMessage.getText().toString()), new FollowRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {

                        System.out.println(code);
                        System.out.println(editTextChatMessage.getText().toString());
                        editTextChatMessage.setText("");

                    }
                });

            }
        });



    }
}

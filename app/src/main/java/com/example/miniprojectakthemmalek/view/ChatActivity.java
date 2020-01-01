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
import com.example.miniprojectakthemmalek.view.fragments.ConversationFragment;
import com.example.miniprojectakthemmalek.view.fragments.DiscussionFragment;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if(getIntent().getIntExtra("redirect",0)==1)
        {

            ConversationFragment conversationFragment=new ConversationFragment();
            Bundle bundle =new Bundle();
            bundle.putString("username",getIntent().getStringExtra("username"));
            bundle.putString("ConnectedUsername",getIntent().getStringExtra("ConnectedUsername"));
            conversationFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.chatFrameLayout,conversationFragment).commit();


        }else if(getIntent().getIntExtra("redirect",0)==2)
        {

            DiscussionFragment discussionFragment=new DiscussionFragment();
            Bundle bundle2 =new Bundle();
            bundle2.putString("connectedUsername",getIntent().getStringExtra("connectedUsername"));
            discussionFragment.setArguments(bundle2);

            getSupportFragmentManager().beginTransaction().replace(R.id.chatFrameLayout,discussionFragment).commit();

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}

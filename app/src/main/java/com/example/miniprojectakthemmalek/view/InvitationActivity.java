package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Invitation;
import com.example.miniprojectakthemmalek.model.repositories.InvitationRepository;
import com.example.miniprojectakthemmalek.view.adapter.AccountsAdapter;
import com.example.miniprojectakthemmalek.view.adapter.InvitationAdapter;

import java.util.List;

public class InvitationActivity extends AppCompatActivity {

    String connectedUsername;

    RecyclerView recyclerViewInvitation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        recyclerViewInvitation=findViewById(R.id.recyclerViewInvitation);

        connectedUsername=getIntent().getStringExtra("username");

    InvitationRepository.getInstance().getReceivedForUser(connectedUsername, new InvitationRepository.getManyCallback() {
        @Override
        public void getMany(List<Invitation> invitationList) {

            InvitationAdapter invitationAdapter=new InvitationAdapter();
            invitationAdapter.setInvitationList(invitationList);
            recyclerViewInvitation.setHasFixedSize(true);
            recyclerViewInvitation.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerViewInvitation.setAdapter(invitationAdapter);

        }
    });


    }
}

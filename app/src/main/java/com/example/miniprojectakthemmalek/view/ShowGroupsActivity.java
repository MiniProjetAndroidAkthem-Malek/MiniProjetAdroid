package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.repositories.GroupRepository;
import com.example.miniprojectakthemmalek.view.adapter.GroupsGridAdapter;

import java.util.List;

public class ShowGroupsActivity extends AppCompatActivity {
GridView gridView;
String connectedUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_groups);
        gridView=findViewById(R.id.gridView);
    connectedUsername=getIntent().getStringExtra("username");
        GroupRepository.getInstance().getAllGroups(new GroupRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<Group> groupList) {
                GroupsGridAdapter groupsGridAdapter=new GroupsGridAdapter(getApplicationContext(),groupList);
                groupsGridAdapter.setUsername(connectedUsername);


                gridView.setAdapter(groupsGridAdapter);

            }
        });



    }
}

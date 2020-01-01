package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
            public void onResponse(final List<Group> groupList) {

                GroupsGridAdapter groupsGridAdapter=new GroupsGridAdapter(getApplicationContext(),groupList);

                groupsGridAdapter.setUsername(connectedUsername);

                gridView.setAdapter(groupsGridAdapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent =new Intent(getApplicationContext(),GroupMainPageActivity.class);
                        intent.putExtra("username",connectedUsername);
                        intent.putExtra("group_name",groupList.get(position).getName());
                        startActivity(intent);
                    }
                });
            }
        });

    }
}

package com.example.miniprojectakthemmalek.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.view.GroupMainPageActivity;

import java.util.List;

public class GroupsGridAdapter extends BaseAdapter {


    private Context context;
    private List<Group> groupList;
    String username;

    public GroupsGridAdapter(Context context, List<Group> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int getCount() {
        return groupList.size();
    }

    @Override
    public Object getItem(int position) {
        return groupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Group single_group=groupList.get(position);

        TextView textView = new TextView(context);
        textView.setLayoutParams(new GridView.LayoutParams(400, 200));
        textView.setBackgroundColor(Color.CYAN);
        textView.setText(single_group.getName().toString());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, GroupMainPageActivity.class);
                intent.putExtra("group_name",single_group.getName());
                intent.putExtra("username",username);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

/*
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            // get layout from xml file
            gridView = inflater.inflate(R.layout.item_grid_view, null);
            // pull views
            TextView letterView =  gridView
                    .findViewById(R.id.title);

            // set values into views
        } else {
            gridView =  convertView;
        }
        return gridView;

*/

        return textView;
    }
}

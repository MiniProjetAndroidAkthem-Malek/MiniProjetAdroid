package com.example.miniprojectakthemmalek.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.Enums.Status;
import com.example.miniprojectakthemmalek.model.repositories.GroupUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;

import java.util.List;

public class GroupsGridAdapter extends BaseAdapter {


    private Context context;
    private List<Group> groupList;
    String username;
    LayoutInflater inflater;



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


        if(inflater == null)
        {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_grid_view,null);
        }

        TextView titleGroup = convertView.findViewById(R.id.title);
        titleGroup.setText(groupList.get(position).getName());

        TextView content=convertView.findViewById(R.id.content);
        content.setText(groupList.get(position).getDescription());

        final TextView postNumber=convertView.findViewById(R.id.postNumber);
        final TextView membersNumber=convertView.findViewById(R.id.membersNumber);

        PostRepository.getInstance().getAllPostByGroupName(groupList.get(position).getName(), new PostRepository.getAllPostCallBack() {
            @Override
            public void onResponse(List<Post> posts) {
                postNumber.setText(""+posts.size());
            }
        });

        GroupUserRepository.getInstance().getUserGroupByStatus(groupList.get(position).getName(), Status.COMFIRMED.toString(), new GroupUserRepository.getAllGroupCallBack() {
            @Override
            public void onResponse(List<GroupUser> list) {
                membersNumber.setText(""+list.size());
            }
        });

        return convertView;
    }
}

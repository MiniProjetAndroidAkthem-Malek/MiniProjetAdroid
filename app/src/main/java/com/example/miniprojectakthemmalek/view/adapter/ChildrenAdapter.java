package com.example.miniprojectakthemmalek.view.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Children;
import com.example.miniprojectakthemmalek.view.fragments.ChildrenProfileFragment;

import java.util.List;

public class ChildrenAdapter extends RecyclerView.Adapter<ChildrenAdapter.ChildrenViewHolder> {


    List<Children> childrenList;
    FragmentManager fragment;
    String connectedUsername;

    public String getConnectedUsername() {
        return connectedUsername;
    }

    public void setConnectedUsername(String connectedUsername) {
        this.connectedUsername = connectedUsername;
    }

    public FragmentManager getFragment() {
        return fragment;
    }

    public void setFragment(FragmentManager fragment) {
        this.fragment = fragment;
    }

    public ChildrenAdapter() {
    }

    public List<Children> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Children> childrenList) {
        this.childrenList = childrenList;
    }

    @NonNull
    @Override
    public ChildrenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.item_children, parent, false);
        ChildrenViewHolder childrenViewHolder = new ChildrenViewHolder(item);

        return childrenViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChildrenViewHolder holder, int position) {

        Children single_children=childrenList.get(position);

        holder.children_name.setText(single_children.getName());
        holder.children_birthdate.setText(single_children.getBirthday().toString().substring(0,10));
        holder.children_description.setText(single_children.getDescription());
        holder.setId(single_children.getId());

        System.out.println("-------------->Id "+holder.getId());

        holder.linearLayoutChildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=new Bundle();
                bundle.putInt("id",holder.getId());
                bundle.putString("connectedUsername",connectedUsername);
                ChildrenProfileFragment childrenProfileFragment=new ChildrenProfileFragment();
                childrenProfileFragment.setArguments(bundle);
                fragment.beginTransaction().replace(R.id.frameProfile,childrenProfileFragment).commit();
            }
        });




    }

    @Override
    public int getItemCount() {
        return childrenList.size();
    }

    public static class ChildrenViewHolder extends RecyclerView.ViewHolder {

        TextView children_name;
        TextView children_birthdate;
        TextView children_description;
        int id;
        LinearLayout linearLayoutChildren;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ChildrenViewHolder(@NonNull View itemView)
        {
            super(itemView);
            children_name = itemView.findViewById(R.id.children_name);
            children_birthdate=itemView.findViewById(R.id.children_birthdate);
            children_description=itemView.findViewById(R.id.children_description);
            linearLayoutChildren=itemView.findViewById(R.id.linearLayoutChildren);


        }


    }


}

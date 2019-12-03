package com.example.miniprojectakthemmalek.view.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.view.fragments.PasswordFragment;

import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.UserViewHolder> {


    private List<User> user_list;
    Fragment fragment;
    public AccountsAdapter(Fragment fragment)
    {
    this.fragment = fragment;
    }

    public List<User> getFilm_list() {
        return user_list;
    }

    public void setFilm_list(List<User> pays_list)
    {
        this.user_list = pays_list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View item=layoutInflater.inflate(R.layout.account_adapter,parent,false);
        UserViewHolder userViewHolder=new UserViewHolder(item);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {

        final User single_user=this.user_list.get(position);
        holder.txt_view.setText(single_user.getUsername());
   //     holder.linearLayout.setBackgroundColor(Color.rgb(single_user.getTheme_r(),single_user.getTheme_g(),single_user.getTheme_b()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PasswordFragment passwordFragment =new PasswordFragment();
                Bundle bundle=new Bundle();
                bundle.putString("username",single_user.getUsername());
                passwordFragment.setArguments(bundle);

                fragment.getFragmentManager().beginTransaction().replace(R.id.accountFrame,passwordFragment).commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return user_list.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_view;
        public LinearLayout linearLayout;

        public UserViewHolder(@NonNull View itemView)
        {

            super(itemView);
            txt_view=itemView.findViewById(R.id.usernameAccount);
            linearLayout=itemView.findViewById(R.id.account_layout);
        }



    }


}

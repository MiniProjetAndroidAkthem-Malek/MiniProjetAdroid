package com.example.miniprojectakthemmalek.view.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.CommentActivity;
import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Activities;
import com.example.miniprojectakthemmalek.model.entities.Enums.ActivityType;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Activities> activitiesList;

    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Activities> getActivitiesList() {
        return activitiesList;
    }

    public void setActivitiesList(List<Activities> activitiesList) {
        this.activitiesList = activitiesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view;


        if(viewType==1)
        {

            LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
            view=layoutInflater.inflate(R.layout.item_activity_follow,parent,false);
            return new FollowViewHolder(view);

        }else if(viewType ==2)
        {

            LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
            view=layoutInflater.inflate(R.layout.item_activity_post,parent,false);
            return new PostViewHolder(view);

        }else
        {

            LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
            view=layoutInflater.inflate(R.layout.item_activity_group,parent,false);
            return new GroupViewHolder(view);

        }


    }

    @Override
    public int getItemViewType(int position) {

    switch (activitiesList.get(position).getActivityType())
    {
        case FOLLOW:
        {
            return 1;
        }
        case POST:
        {
            return 2;
        }
        case GROUP_JOIN:
        {
            return 3;
        }
        default:
            return -1;

    }

    }



    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final Activities single_activity=this.activitiesList.get(position);

        if(single_activity.getActivityType()== ActivityType.FOLLOW)
        {
            ((FollowViewHolder) holder).username.setText(single_activity.getUsername());

            ((FollowViewHolder) holder).usernameOther.setText(single_activity.getActWith());


            ImageRepository.getInstance().loadPicutreOf(username, 0.2f, 0.2f, new ImageRepository.getPictureCallBack() {
                @Override
                public void onResponse(Bitmap picBitmap) {
                    ((FollowViewHolder) holder).image_profile.setImageBitmap(picBitmap);

                }
            });



        }else if(single_activity.getActivityType()==ActivityType.POST){

            ((PostViewHolder) holder).username.setText(single_activity.getUsername());


            ImageRepository.getInstance().loadPicutreOf(username, 0.2f, 0.2f, new ImageRepository.getPictureCallBack() {
                @Override
                public void onResponse(Bitmap picBitmap) {
                    ((PostViewHolder) holder).image_profile.setImageBitmap(picBitmap);

                }
            });


            PostRepository.getInstance().getPostById("" + single_activity.getActWith(), new PostRepository.getAllPostCallBack() {
                @Override
                public void onResponse(List<Post> posts) {

                   if(posts.size()>0)
                   {

                       Post post=posts.get(0);
                       ((PostViewHolder) holder).postcontent.setText(post.getDescription());

                   }

                }
            });

            ((PostViewHolder) holder).postLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(v.getContext(), CommentActivity.class);

                    intent.putExtra("username",username);
                    intent.putExtra("post",((PostViewHolder) holder).postcontent.getText());
                    intent.putExtra("id_post",single_activity.getActWith());

                    v.getContext().startActivity(intent);

                }
            });



        }else  if(single_activity.getActivityType()==ActivityType.GROUP_JOIN)
        {
            ((GroupViewHolder) holder).username.setText(single_activity.getUsername());
            ((GroupViewHolder) holder).groupname.setText(single_activity.getActWith());

            ImageRepository.getInstance().loadPicutreOf(username, 0.2f, 0.2f, new ImageRepository.getPictureCallBack() {
                @Override
                public void onResponse(Bitmap picBitmap) {
                    ((GroupViewHolder) holder).image_profile.setImageBitmap(picBitmap);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return activitiesList.size();
    }

    public static class FollowViewHolder extends RecyclerView.ViewHolder {

        public TextView username,usernameOther;
        CircleImageView image_profile;


        public FollowViewHolder(@NonNull View itemView)
        {
            super(itemView);

            username=itemView.findViewById(R.id.username);
            usernameOther=itemView.findViewById(R.id.usernameOther);
            image_profile=itemView.findViewById(R.id.image_profile);

        }

    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        public TextView username,postcontent;
        public LinearLayout postLinearLayout;
        CircleImageView image_profile;


        public PostViewHolder(@NonNull View itemView)
        {
            super(itemView);

            username=itemView.findViewById(R.id.username);
            postcontent=itemView.findViewById(R.id.postcontent);
            postLinearLayout=itemView.findViewById(R.id.postLinearLayout);
            image_profile=itemView.findViewById(R.id.image_profile);

        }

    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder {

        public TextView username;
        public TextView groupname;
        CircleImageView image_profile;

        public GroupViewHolder(@NonNull View itemView)
        {
            super(itemView);

            username=itemView.findViewById(R.id.username);
            groupname=itemView.findViewById(R.id.groupName);
            image_profile=itemView.findViewById(R.id.image_profile);

        }

    }


}

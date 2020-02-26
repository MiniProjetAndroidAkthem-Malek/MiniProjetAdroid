package com.example.miniprojectakthemmalek.view.utils;

import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.Tag_post;
import com.example.miniprojectakthemmalek.model.entities.Tag_user;
import com.example.miniprojectakthemmalek.model.repositories.TagPostRepository;
import com.example.miniprojectakthemmalek.model.repositories.TagUserRepository;

import java.util.ArrayList;
import java.util.List;

public class RecomandedPosts {


    public void postIsInteresting(final int idPost, String username,final callBack callBack)
    {

        TagUserRepository.getInstance().getTagUserByUsername(username, new TagUserRepository.getAllPostCallBack() {
            @Override
            public void onResponse(final List<Tag_user> tagUsers) {

                    TagPostRepository.getInstance().getTagPostById(idPost, new TagPostRepository.getAllPostCallBack() {
                        @Override
                        public void onResponse(List<Tag_post> tagPosts) {

                            List<String> usersInterested=new ArrayList<String>();
                            List<String> postInterested=new ArrayList<String>();

                            for(Tag_user tg:tagUsers)
                            {
                                usersInterested.add(tg.getName());
                            }

                            for(Tag_post tp: tagPosts)
                            {
                                postInterested.add(tp.getName());
                            }

                            for(String sUserInterest:usersInterested)
                            {
                                for (String sPostInterest:postInterested)
                                {
                                    if(sPostInterest.equals(sUserInterest))
                                    {
                                        callBack.onResponse(true);
                                        return;

                                    }

                                }
                            }
                            callBack.onResponse(false);

                        }


                    });

            }
        });

    }

    public interface callBack
    {
        public void onResponse(Boolean isInterestedIn);
    }


}

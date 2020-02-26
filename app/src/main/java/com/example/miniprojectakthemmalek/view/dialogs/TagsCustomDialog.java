package com.example.miniprojectakthemmalek.view.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Tag_user;
import com.example.miniprojectakthemmalek.model.repositories.TagUserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class TagsCustomDialog extends Dialog {


    List<String> list= new ArrayList<String>();
    List<String> list2=new ArrayList<String>();

    String username;
    Context c;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public TagsCustomDialog(@NonNull Context context) {
        super(context);
        c=context;

    }

    public TagsCustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected TagsCustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    Button btnValidate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_info);
        setCancelable(false);
        final TagContainerLayout mTagContainer = (TagContainerLayout) findViewById(R.id.tag);
        final TagContainerLayout mTagContainer2 = (TagContainerLayout) findViewById(R.id.tag2);
        mTagContainer.setTheme(ColorFactory.NONE);
        mTagContainer.setBackgroundColor(Color.TRANSPARENT);
        mTagContainer2.setTheme(ColorFactory.NONE);
        mTagContainer2.setBackgroundColor(Color.TRANSPARENT);
        btnValidate=findViewById(R.id.btnValidate);

        list.addAll(Arrays.asList(c.getResources().getStringArray(R.array.disease_array)));

        mTagContainer.setTags(list);
        mTagContainer2.setTags(list2);


        mTagContainer.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                list2.add(text);
                list.remove(text);
                mTagContainer.setTags(list);
                mTagContainer2.setTags(list2);

                if(list2.size()>0)
                {
                    btnValidate.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onSelectedTagDrag(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });


        mTagContainer2.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {

                list2.remove(text);
                list.add(text);
                mTagContainer.setTags(list);
                mTagContainer2.setTags(list2);

                if(list2.size()>0)
                {
                    btnValidate.setVisibility(View.VISIBLE);
                }

                if(list2.size()==0)
                {
                    btnValidate.setVisibility(View.GONE);
                }

            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onSelectedTagDrag(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });


        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(list2.size()!=0)
                {

                    for(String s:list2)
                    {
                        Tag_user tag_user=new Tag_user(username,s);
                        TagUserRepository.getInstance().addTagUser(tag_user, new TagUserRepository.addingCallback() {
                            @Override
                            public void addingCallback(int code) {

                            }
                        });

                    }


                    setCancelable(true);
                    dismiss();

                }


            }
        });



    }
}

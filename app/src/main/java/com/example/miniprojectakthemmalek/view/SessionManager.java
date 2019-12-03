package com.example.miniprojectakthemmalek.view;

import android.content.Context;
import android.content.Intent;

import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {


    AppDatabase database;
    Context context;

  public SessionManager(Context context)
    {
        database=AppDatabase.getInstance(context);
        this.context=context;
    }


    public void openSessionForUser(User user)
    {
        if(getUser(user.getUsername())==null)
        {
            user.setActive(1);
            database.userDao().insertOne(user);
        }

        if(user.isActive()==1)
        {
            Intent intent = new Intent(context, ServiceApp.class);
            intent.putExtra("username",user.getUsername());
            context.startService(intent);

        }
    }

    public User getUser(String username)
    {
        return database.userDao().getOne(username);
    }
    public User getUser(int isActive)
    {
        return database.userDao().getOne(isActive);
    }

    public User getUser(String username,int isActive)
    {
        return database.userDao().getOne(username,isActive);
    }


    public User getUserByStatusEqualTo(int isActive)
    {
        return database.userDao().getOne(isActive);
    }

    public void updateConnectionStatusForUser(String username,int isActive)
    {
        database.userDao().updateConnectionStatus(username,isActive);

        if(isActive==1)
        {
            Intent intent = new Intent(context, ServiceApp.class);
            intent.putExtra("username",username);
            context.startService(intent);

        }

    }

    public void updateThemeForUser(String username,int theme_r ,int theme_g, int theme_b)
    {

        database.userDao().updateTheme(username,theme_r,theme_g,theme_b);

    }


    public void synchroniseWithDatabase()
    {
        UserRepository.getInstance().getAllUsers(new UserRepository.getAllUserCallBack() {
            @Override
            public void onResponse(List<User> user) {

                for(User u:getAllUsers())
                {
                    if(!user.contains(u))
                    {
                        System.out.println(u.getUsername());
                        deleteSessionForUser(u.getUsername());
                    }
                }
            }
        });




    }



    public void updateRememberStatusForUser(String username,int rememberMe)
    {
        database.userDao().updateRememberStatus(username,rememberMe);
    }


    public List<User> getAllUsers()
    {
        return database.userDao().getAll();
    }

    public void deleteSessionForUser(String username)
    {
        database.userDao().deleteOne(username);
    }

    public void updateUser(User user)
    {
        database.userDao().updateOne(user);
    }

    public void clearAllSession()
    {
        database.userDao().deleteAll();
    }

}

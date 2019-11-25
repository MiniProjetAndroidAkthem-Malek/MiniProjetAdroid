package com.example.miniprojectakthemmalek.view.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.miniprojectakthemmalek.model.entities.User;

import java.util.List;

@Dao
public interface IUserDao {


@Query("select * from t_user")
List<User> getAll();

@Query("select * from t_user where username= :username")
User getOne(String username);

@Query("select * from t_user where isActive= :isActive")
User getOne(int isActive);

@Query("select * from t_user where isActive= :isActive and username =:username")
User getOne(String username, int isActive);

@Query("update t_user set isActive=:isActive where username=:username ")
void updateConnectionStatus(String username,int isActive);

@Query("update t_user set rememberMe=:rememberMe where username=:username ")
void updateRememberStatus(String username,int rememberMe);

@Query("delete from t_user")
void deleteAll();

@Insert
void insertOne(User user);

@Update
public void updateOne(User user);

@Query("delete from t_user where username=:username")
void deleteOne(String username);

}

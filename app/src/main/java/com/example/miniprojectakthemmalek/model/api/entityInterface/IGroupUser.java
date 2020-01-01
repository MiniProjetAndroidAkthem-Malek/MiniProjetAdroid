package com.example.miniprojectakthemmalek.model.api.entityInterface;

import com.example.miniprojectakthemmalek.model.entities.Group;
import com.example.miniprojectakthemmalek.model.entities.GroupUser;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IGroupUser {


@POST("group_user/add")
Call<JsonPrimitive> addGroup(@Body GroupUser groupUser);

@GET("/group_user/getByGroupName/{groupe_name}/{status}")
Call<List<GroupUser>> getInvitationForGroup(@Path("groupe_name") String groupe_name,@Path("status") String status);

@PUT("/group_user/update")
Call<JsonObject> updateGroupUser(@Body GroupUser groupUser);

@PUT("/group_user/updateRole")
Call<JsonObject> updateRoleGroupUser(@Body GroupUser groupUser);

@DELETE("/group_user/delete/{username}/{groupe_name}")
Call<JsonObject> deleteGroupUser(@Path("username") String username,@Path("groupe_name") String groupe_name);

@GET("/group_user/getByGroupeNameAndUsername/{group_name}/{username}")
Call<List<GroupUser>> getGroupUser(@Path("group_name") String group_name,@Path("username") String username);

@GET("/group_user/getGroupUserByStatusAndRole/{group_name}/{status}/{role}")
Call<List<GroupUser>> getGroupUserByRoleAndStatus(@Path("group_name") String group_name,@Path("status") String status,@Path("role") String role);

@GET("/group_user/getGroupUserByStatus/{group_name}/{status}")
Call<List<GroupUser>> getGroupUserByStatus(@Path("group_name") String group_name,@Path("status") String status);


}

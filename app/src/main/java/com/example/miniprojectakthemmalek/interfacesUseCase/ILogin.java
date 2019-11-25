package com.example.miniprojectakthemmalek.interfacesUseCase;

import android.content.Context;

import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.AuthentificationActivity;

public interface ILogin {

public interface model
{
  public void  getOneUser(String username, final UserRepository.getOneUserCallBack callBack);
}

public interface presenter
{
    public void doValidate(String username,String password);

}

public interface view
{

    public void onValidate(Context context, User user);

}

}

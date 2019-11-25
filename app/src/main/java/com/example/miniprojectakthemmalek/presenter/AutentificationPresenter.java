package com.example.miniprojectakthemmalek.presenter;

import android.content.Context;

import com.example.miniprojectakthemmalek.interfacesUseCase.ILogin;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.AuthentificationActivity;

public class AutentificationPresenter implements ILogin.presenter {

    ILogin.model loginModel;
    ILogin.view loginView;
    Context context;
    public AutentificationPresenter()
    {
        this.loginModel = new UserRepository();
        this.loginView = new AuthentificationActivity();
    }


    public AutentificationPresenter(Context context) {
        this.loginModel = new UserRepository();
        this.loginView = new AuthentificationActivity();
        this.context=context;

    }


    @Override
    public void doValidate(final String username, final String password) {

        loginModel.getOneUser(username, new UserRepository.getOneUserCallBack() {
            @Override
            public void onResponse(User user) {

                if(user !=null)
                {
                    if(user.getUsername().equals(username) && user.getPassword().equals(password))
                    {
                        loginView.onValidate(context,user);
                    }

                }else{

                        loginView.onValidate(context,user);
                }
            }
        });

    }

}

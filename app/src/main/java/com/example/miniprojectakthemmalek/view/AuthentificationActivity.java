package com.example.miniprojectakthemmalek.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IUser;
import com.example.miniprojectakthemmalek.model.entities.Children;
import com.example.miniprojectakthemmalek.model.entities.Message;
import com.example.miniprojectakthemmalek.model.entities.Tag_post;
import com.example.miniprojectakthemmalek.model.entities.Tag_user;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.ChildrenRepository;
import com.example.miniprojectakthemmalek.model.repositories.MessageRepository;
import com.example.miniprojectakthemmalek.model.repositories.NotificationRepositories.NotificationRepository;
import com.example.miniprojectakthemmalek.model.repositories.TagPostRepository;
import com.example.miniprojectakthemmalek.model.repositories.TagUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.dialogs.TagsCustomDialog;
import com.example.miniprojectakthemmalek.view.fragments.AccountsFragment;
import com.example.miniprojectakthemmalek.view.utils.GeoCoder.GeoLocationManager;
import com.example.miniprojectakthemmalek.view.utils.ImageManager;
import com.example.miniprojectakthemmalek.view.utils.NetworkCheck;
import com.example.miniprojectakthemmalek.view.utils.RecomandedPosts;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.example.miniprojectakthemmalek.view.utils.Tools;
import com.google.android.material.textfield.TextInputEditText;
import com.mapbox.mapboxsdk.Mapbox;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


public class AuthentificationActivity extends AppCompatActivity {


    private View parent_view;
    Button signin;
    TextView signup;
    TextInputEditText username, password;
    IUser iUser = RetrofitInstance.getRetrofitInstance().create(IUser.class);
    User newuser;
    LoginButton loginButton;
    ImageManager imageManager;


    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;

    SessionManager sessionManager;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_simple_light);
        System.out.println(NetworkCheck.isNetworkAvailable(getApplicationContext()));


        RecomandedPosts rp=new RecomandedPosts();


        rp.postIsInteresting(67, "ee", new RecomandedPosts.callBack() {
            @Override
            public void onResponse(Boolean isInterestedIn) {

                System.out.println("----------> "+isInterestedIn);
            }
        });

        TagPostRepository.getInstance().getTagPostById(67, new TagPostRepository.getAllPostCallBack() {
            @Override
            public void onResponse(List<Tag_post> tagPosts) {
                System.out.println(tagPosts);
            }
        });


MessageRepository.getInstance().getDiscussionsByConnectedUsername("userTest", new MessageRepository.getManyCallback() {
    @Override
    public void getManyOneFollow(List<Message> messages) {
        System.out.println("------>discussions "+messages);
    }
});

      //  LoginManager.getInstance().logOut();

        sessionManager = new SessionManager(this);
        sessionManager.synchroniseWithDatabase();
        loginButton=findViewById(R.id.login_button);
        imageManager=new ImageManager(getApplicationContext());

        callbackManager=CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));

       // System.out.println("----------------->"+userIsLogged());

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        checkStatusAndRedirect();

        parent_view = findViewById(android.R.id.content);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.sign_up);

        signin.setVisibility(View.INVISIBLE);
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.accountFrame, new AccountsFragment()).commit();
       // System.out.println(sessionManager.getAllUsers());


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NetworkCheck.isNetworkAvailable(getApplicationContext())==true){
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);}
                else if (NetworkCheck.isNetworkAvailable(getApplicationContext())==false) {
                    Intent intent = new Intent(getApplicationContext(), NoInternet.class);
                    startActivity(intent);}

            }

        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);







    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);

         super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker =new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            if(currentAccessToken==null)
            {


            }else {


                loadUserProfile(currentAccessToken);

            }
        }
    };


    private  void loadUserProfile(AccessToken newAccessToken)
    {

        GraphRequest request =GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {
                    String first_name=object.getString("first_name");
                    String last_name=object.getString("last_name");
                    String email=object.getString("email");
                    String id=object.getString("id");
                    String name=object.getString("name");

                    final String image_url ="https://graph.facebook.com/"+id+"/picture?type=normal";

                    System.out.println("firstName---> "+first_name);
                    System.out.println("lastName---> "+last_name);
                    System.out.println("email---> "+email);
                    System.out.println("name---> "+name);
                    System.out.println(image_url);

                    name = name.replaceAll(" ","");
                    newuser =new User(name,first_name,last_name);

                UserRepository.getInstance().getOneUser( name, new UserRepository.getOneUserCallBack() {
                    @Override
                    public void onResponse(User user) {

                        if(user==null)
                        {

                            URL url = null;
                            try {

                                url = new URL(image_url);
                                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                imageManager.multipartImageUpload(bmp,newuser.getUsername(),"profile",null);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            UserRepository.getInstance().addUser(newuser, new UserRepository.addingCallback() {
                                @Override
                                public void addingCallback(int code) {

                                }
                            });

                        }
                    }
                });


                        sessionManager.openSessionForUser(newuser);
                        sessionManager.initRememberMeStatus();
                        sessionManager.updateConnectionStatusForUser(newuser.getUsername(),1);
                        sessionManager.updateRememberStatusForUser(newuser.getUsername(),1);
                        Intent intentHome =new Intent(getApplicationContext(), HomeActivity.class);
                        intentHome.putExtra("username",newuser.getUsername());
                        startActivity(intentHome);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        Bundle parameters = new Bundle();

        parameters.putString("fields","first_name,last_name,email,id,name");

        request.setParameters(parameters);
        request.executeAsync();

    }


    public void checkStatusAndRedirect()
    {

        if(userIsLogged())
        {


            User user=sessionManager.getUserByRemeberMe(1);
            System.out.println(sessionManager.getAllUsers());

            //    System.out.println("------------>"+sessionManager.getUser("Malek Belaib"));

            if(user!=null)
            {
                sessionManager.updateConnectionStatusForUser(user.getUsername(),1);

                Intent intentHome =new Intent(getApplicationContext(), HomeActivity.class);
                intentHome.putExtra("username",user.getUsername());
                startActivity(intentHome);
            //    System.out.println("------------>"+user);

            }

        }


    }


    public boolean userIsLogged()
    {

        if(AccessToken.getCurrentAccessToken()==null)
        {


            return false;

        }else
        {
            return true;
        }

    }




}
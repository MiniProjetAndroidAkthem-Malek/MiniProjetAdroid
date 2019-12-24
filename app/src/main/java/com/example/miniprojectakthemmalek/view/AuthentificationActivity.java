package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IUser;
import com.example.miniprojectakthemmalek.view.fragments.AccountsFragment;
import com.example.miniprojectakthemmalek.view.utils.Tools;
import com.google.android.material.textfield.TextInputEditText;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class AuthentificationActivity extends AppCompatActivity {


    private View parent_view;
    Button signin;
    TextView signup;
    TextInputEditText username, password;
    IUser iUser = RetrofitInstance.getRetrofitInstance().create(IUser.class);


    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_simple_light);



        sessionManager = new SessionManager(this);
        sessionManager.synchroniseWithDatabase();

        parent_view = findViewById(android.R.id.content);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.sign_up);

        signin.setVisibility(View.INVISIBLE);
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.accountFrame, new AccountsFragment()).commit();
        System.out.println(sessionManager.getAllUsers());


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



      }




}
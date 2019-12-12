package com.example.miniprojectakthemmalek.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IUser;
import com.example.miniprojectakthemmalek.model.entities.Follow;
import com.example.miniprojectakthemmalek.model.entities.Invitation;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.FollowRepository;
import com.example.miniprojectakthemmalek.model.repositories.GroupPostsRepository;
import com.example.miniprojectakthemmalek.model.repositories.InvitationRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.database.AppDatabase;
import com.example.miniprojectakthemmalek.view.fragments.AccountsFragment;
import com.example.miniprojectakthemmalek.view.utils.GeoCoder.GeoLocationManager;
import com.example.miniprojectakthemmalek.view.utils.JavaMailApi.SendMail;
import com.google.android.material.snackbar.Snackbar;
import com.example.miniprojectakthemmalek.view.utils.Tools;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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


public class AuthentificationActivity extends AppCompatActivity  {


    private View parent_view;
    Button signin;
    TextView signup;
    TextInputEditText username,password;
    IUser iUser = RetrofitInstance.getRetrofitInstance().create(IUser.class);

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_simple_light);


/*
        SendMail sendMail=new SendMail(getApplicationContext(),"malek.belayeb@esprit.tn","sqd","qqqqqq00");
        sendMail.execute();
*/

        sessionManager=new SessionManager(this);
        sessionManager.synchroniseWithDatabase();

        parent_view = findViewById(android.R.id.content);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.sign_up);

        signin.setVisibility(View.INVISIBLE);
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.accountFrame,new AccountsFragment()).commit();
        System.out.println(sessionManager.getAllUsers());



/*
        InvitationRepository.getInstance().addInvitation(new Invitation("pp", "malek", "malek", "malek"), new InvitationRepository.addingCallback() {
            @Override
            public void addingCallback(int code) {

            }
        });*/
/*
        InvitationRepository.getInstance().addInvitation(new Invitation("uu", "malek", "malek", "malek"), new InvitationRepository.addingCallback() {
            @Override
            public void addingCallback(int code) {

            }
        });
*/


signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent =new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(intent);
    }
});

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

       /*Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);
*/


    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            final Bundle extras = data.getExtras();

            Uri uri = data.getData();
            String[] projection = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            String picturePath = cursor.getString(columnIndex); // returns null
            File imgFile=new File(picturePath);

            UserRepository.getInstance().uploadPhotos(imgFile,"malek");
            cursor.close();

        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    //sessionManager.synchroniseWithDatabase();
    }
}

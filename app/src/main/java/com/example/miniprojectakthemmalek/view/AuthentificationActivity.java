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
import com.example.miniprojectakthemmalek.interfacesUseCase.ILogin;
import com.example.miniprojectakthemmalek.model.api.RetrofitInstance;
import com.example.miniprojectakthemmalek.model.api.entityInterface.IUser;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.presenter.AutentificationPresenter;
import com.example.miniprojectakthemmalek.view.database.AppDatabase;
import com.example.miniprojectakthemmalek.view.fragments.AccountsFragment;
import com.google.android.material.snackbar.Snackbar;
import com.example.miniprojectakthemmalek.view.utils.Tools;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileNotFoundException;

public class AuthentificationActivity extends AppCompatActivity implements ILogin.view {

    private View parent_view;
    Button signin;
    TextView signup;
    ILogin.presenter loginPresenter;
    TextInputEditText username,password;
    IUser iUser = RetrofitInstance.getRetrofitInstance().create(IUser.class);

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_simple_light);

        sessionManager=new SessionManager(this);
        sessionManager.synchroniseWithDatabase();

        loginPresenter =new AutentificationPresenter(getApplicationContext());
        parent_view = findViewById(android.R.id.content);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.sign_up);

        signin.setVisibility(View.INVISIBLE);
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);

        //sessionManager.clearAllSession();
        getSupportFragmentManager().beginTransaction().replace(R.id.accountFrame,new AccountsFragment()).commit();
        System.out.println(sessionManager.getAllUsers());


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
    public void onValidate(Context context,User user) {

        if(user == null)
        {

        }else{

            Intent intent =new Intent(context,ProfileActivity.class);
            startActivity(intent);

        }

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

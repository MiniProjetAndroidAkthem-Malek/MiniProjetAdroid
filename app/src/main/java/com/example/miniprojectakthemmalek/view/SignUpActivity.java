package com.example.miniprojectakthemmalek.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Tag;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.adapter.TagAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.chip.Chip;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

EditText usernameInput,passwordInput,retypeInput,firstName,lastName;
Button email_sign_in_button,facebooklogin;
int x=0, y=0,z=0;
LinearLayout linearLayout;
SessionManager sessionManager;
    AppCompatRadioButton radio_female,radio_male;
    DatePicker birth_date;
String sexe="";

NachoTextView et_tag,et_tag2;


    List<String> chips=new ArrayList<String>();

    List<Tag> tagList=new ArrayList<Tag>();

    List<Tag> tagList2=new ArrayList<Tag>();

    List<String> chips2=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sign_up);
        sessionManager=new SessionManager(this);
        birth_date=findViewById(R.id.birth_date);
        radio_female = findViewById(R.id.radio_female);
        radio_male=findViewById(R.id.radio_male);
        linearLayout=findViewById(R.id.linearLayout);
        usernameInput=findViewById(R.id.usernameInput);
        passwordInput=findViewById(R.id.passwordInput);
        retypeInput=findViewById(R.id.retypeInput);
        firstName=findViewById(R.id.firstName);
        lastName=findViewById(R.id.lastName);
        et_tag=findViewById(R.id.et_tag);
        et_tag2=findViewById(R.id.et_tag2);

        email_sign_in_button=findViewById(R.id.email_sign_in_button);
        email_sign_in_button.setEnabled(false);



    usernameControl(usernameInput);
    passwordControl(passwordInput,retypeInput);
    retypeControl(retypeInput,passwordInput);
    updateButton(email_sign_in_button);


    email_sign_in_button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
final String usernameLoc = usernameInput.getText().toString();

        if(radio_male.isChecked())
        {
            sexe = "Male";

        }else if(radio_female.isChecked())
        {
            sexe= "Female";
        }


        System.out.println("sexe----> "+sexe);
        System.out.println("date----> "+ getDateFromDatePicker());



        UserRepository.getInstance().addUser(new User(usernameInput.getText().toString(), passwordInput.getText().toString(),sexe,birth_date.getYear()+"/"+birth_date.getMonth()+"/"+birth_date.getDayOfMonth(),0,0), new UserRepository.addingCallback() {
            @Override
            public void addingCallback(int code) {
                if(code==200)
                {
                    usernameInput.setText("");
                    passwordInput.setText("");
                    retypeInput.setText("");

                    Snackbar.make(findViewById(android.R.id.content), "You'r welcome to Volks", Snackbar.LENGTH_SHORT).show();

                    Button btn=new Button(getApplicationContext());

                    btn.setText("Continue to my profile");

                    linearLayout.addView(btn,linearLayout.indexOfChild(email_sign_in_button));
                    linearLayout.removeView(email_sign_in_button);
                    linearLayout.removeView(facebooklogin);

                    btn.setBackgroundResource(R.drawable.btn_rounded_accent);
                    btn.setTextColor(Color.WHITE);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            UserRepository.getInstance().getOneUser(usernameLoc, new UserRepository.getOneUserCallBack() {
                                @Override
                                public void onResponse(User user) {

                                    user.setTheme_r(0);
                                    user.setTheme_g(0);
                                    user.setTheme_b(0);
                                    sessionManager.openSessionForUser(user);
                                    System.out.println(user);
                                    Intent intent=new Intent(getApplicationContext(),CardWizardLight.class);
                                    intent.putExtra("username",user.getUsername());
                                    startActivity(intent);
                                }
                            });


                        }
                    });
                }
            }
        });

    }
});




   chips.add("sss");
   chips.add("qqqq");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("dddd");
   chips.add("QAA");
   chips.add("vvvv");
        et_tag.setChipSpacing(R.dimen.spacing_medium);
        et_tag2.setChipSpacing(R.dimen.spacing_medium);
        et_tag.setText(chips);


        et_tag.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);

    et_tag.setOnChipClickListener(new NachoTextView.OnChipClickListener() {
        @Override
        public void onChipClick(Chip chip, MotionEvent event) {

            chips2.add(chip.getText().toString());
            chips.remove(chip.getText().toString());
            et_tag.setText(chips);
            et_tag2.setText(chips2);
            et_tag2.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);

        }
    });






    }

    public void usernameControl(final EditText usernameEdit)
    {
        usernameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(usernameEdit.getText().toString().equals(""))
                {
                    x=0;

                }else {
                    x=1;

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                updateButton(email_sign_in_button);
                
            }
        });
    }

    public void passwordControl(final EditText passwordEdit,final EditText retypeEdit)
    {
        passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                updateButton(email_sign_in_button);
                retypeControl(retypeEdit,passwordEdit);

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(passwordEdit.getText().toString().equals(retypeEdit.getText().toString()))
                {

                    y=1;
                    z=1;
                }else
                {
                    y=0;
                    z=0;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                updateButton(email_sign_in_button);
                retypeControl(retypeEdit,passwordEdit);

            }
        });
    }

    public void retypeControl(final EditText retypeEdit, final EditText passwordEdit)
    {
        retypeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateButton(email_sign_in_button);
                passwordControl(passwordEdit,retypeEdit);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(retypeEdit.getText().toString().equals(passwordEdit.getText().toString()))
                {
                 z=1;
                 y=1;
                }else
                {
                    z=0;
                    y=0;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                updateButton(email_sign_in_button);
                passwordControl(passwordEdit,retypeEdit);

            }
        });
    }



    public Date getDateFromDatePicker()
    {
        int day = birth_date.getDayOfMonth();
        int month = birth_date.getMonth();
        int year =  birth_date.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date=new Date();
        try {

             date = simpleDateFormat.parse(year+"-"+month+"-"+day);


        }catch (ParseException e)
        {
            e.printStackTrace();
        }

        return date;
    }

public void updateButton(Button btn)
{

    if(x+y+z == 3)
    {
        btn.setEnabled(true);

    }else
    {
        btn.setEnabled(false);
    }
}



}

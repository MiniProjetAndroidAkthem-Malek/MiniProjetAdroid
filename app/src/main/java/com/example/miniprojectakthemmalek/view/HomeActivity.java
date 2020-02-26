package com.example.miniprojectakthemmalek.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.EventUser;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.Tag_user;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.EventUserRepository;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.model.repositories.TagUserRepository;
import com.example.miniprojectakthemmalek.view.dialogs.TagsCustomDialog;
import com.example.miniprojectakthemmalek.view.fragments.AddPostFragment;
import com.example.miniprojectakthemmalek.view.fragments.PostsFragment;
import com.facebook.login.LoginManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;


import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeActivity extends AppCompatActivity  {
    private static final int MAX_STEP = 4;

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.

    Button movetoprofile;
    TextView Friends;
    TextView movetogroups;
    NestedScrollView nested_scroll_view;
    TextView movetoposts;
    TextView gotoevents;
    FrameLayout frameHome;
    SessionManager sessionManager;
    User user;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView connectedUserName,connectedUserJob;
    FloatingActionButton notifications;
    Animation slideDown;
    TextView inbox_nums;

    NotificationManagerCompat notificationManagerCompat;
    FloatingActionButton moveToAddPost,movetopostss;
    CardView gotoeventss,gotogroups,gotofriends,gotoposts;

    Spinner spinner;
    public void initStyle()
    {

            if(user.getTheme_r()!=0)
            {
                int rgb = Color.rgb(user.getTheme_r(),user.getTheme_g(),user.getTheme_b());

                toolbar.setBackgroundColor(rgb);

            }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_feed);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        slideDown =  AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);


        toolbar=findViewById(R.id.toolbar);
        Friends= findViewById(R.id.friends);
        navigationView=findViewById(R.id.nav_view);
        movetogroups=findViewById(R.id.movetogroups);
        movetoposts=findViewById(R.id.movetoposts);
        nested_scroll_view=findViewById(R.id.nested_scroll_view);
        frameHome=findViewById(R.id.frameHome);
        gotoevents=findViewById(R.id.gotoevents);
        moveToAddPost=findViewById(R.id.movetozddposts);
        movetopostss=findViewById(R.id.movetoposss);









        moveToAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focusOnView();

                AddPostFragment addPostFragment =    new AddPostFragment();
                Bundle bundle = new Bundle();
                bundle.putString("username",user.getUsername());
                addPostFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.frameHome,addPostFragment).commit();
                moveToAddPost.setVisibility(View.GONE);
            }
        });

        initComponent();


        movetoposts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focusOnView();
            }
        });



        gotoevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),ShowEventsActivity.class);
                intent.putExtra("username",user.getUsername());

                startActivity(intent);
            }
        });



        Friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),ShowFriendsActivity.class);
                intent.putExtra("username",user.getUsername());

                startActivity(intent);
            }
        });


gotoposts=findViewById(R.id.gotoposts);
gotogroups=findViewById(R.id.gotogroups);
gotoeventss=findViewById(R.id.gotoeventss);
gotofriends=findViewById(R.id.gotofriends);




gotofriends.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent =new Intent(getApplicationContext(),ShowFriendsActivity.class);
        intent.putExtra("username",user.getUsername());

        startActivity(intent);
    }
});

gotoeventss.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent =new Intent(getApplicationContext(),ShowEventsActivity.class);
        intent.putExtra("username",user.getUsername());

        startActivity(intent);


    }
});
gotogroups.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent =new Intent(getApplicationContext(),ShowGroupsActivity.class);
        intent.putExtra("username",user.getUsername());
        startActivity(intent);
    }
});
gotoposts.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      focusOnView();

    }
});

        movetogroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),ShowGroupsActivity.class);
                intent.putExtra("username",user.getUsername());
                startActivity(intent);
            }
        });

        View headerView = navigationView.getHeaderView(0);
        TextView connectedUserName = headerView.findViewById(R.id.connectedUserName);
        final CircleImageView avatar=headerView.findViewById(R.id.avatar);
        TextView connectedUserJob = headerView.findViewById(R.id.connectedUserJob);
        inbox_nums = findViewById(R.id.inbox_nums);

        sessionManager=new SessionManager(this);
        if(getIntent().hasExtra("username"))
        {
            user = sessionManager.getUser(getIntent().getStringExtra("username"),1);
        }



        TagUserRepository.getInstance().getTagUserByUsername(user.getUsername(), new TagUserRepository.getAllPostCallBack() {
            @Override
            public void onResponse(List<Tag_user> tagUsers) {

                if(tagUsers.size()==0)
                {

                    TagsCustomDialog tagsCustomDialog=new TagsCustomDialog(HomeActivity.this);
                    tagsCustomDialog.setUsername(user.getUsername());
                    tagsCustomDialog.show();

                }

            }
        });





        FirebaseMessaging.getInstance().subscribeToTopic(user.getUsername().toString());

        connectedUserName.setText(user.getUsername());
        connectedUserJob.setText(user.getJob());
        PostsFragment postsFragment = new PostsFragment();
       Bundle bundle = new Bundle();
       bundle.putString("username",user.getUsername().toString());
       postsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameHome,postsFragment).commit();

      initStyle();

      navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent =new Intent(getApplicationContext(),ProfileActivity.class);
              intent.putExtra("username",user.getUsername());
              startActivity(intent);
          }
      });


        ImageRepository.getInstance().loadPicutreOf(user.getUsername(), 0.3f, 0.3f, new ImageRepository.getPictureCallBack() {
            @Override
            public void onResponse(Bitmap picBitmap) {
                avatar.setImageBitmap(picBitmap);
            }
        });


      navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id= menuItem.getItemId();
        switch (id)
        {
            case R.id.nav_all_inbox:
            {
                Intent intent =new Intent(getApplicationContext(),ChatActivity.class);
                intent.putExtra("connectedUsername",user.getUsername());
                intent.putExtra("redirect",2);
                startActivity(intent);
                break;
            }
            case R.id.nav_group:
            {
                Intent intent =new Intent(getApplicationContext(),GroupActivity.class);
                intent.putExtra("username",user.getUsername());
                startActivity(intent);
               break;
            }
            case R.id.nav_all_groups:
            {
                Intent intent =new Intent(getApplicationContext(),ShowGroupsActivity.class);
                intent.putExtra("username",user.getUsername());
                startActivity(intent);
              break;
            }
            case R.id.nav_starred:
            {
                Intent intent =new Intent(getApplicationContext(),InvitationActivity.class);
                intent.putExtra("username",user.getUsername());
                startActivity(intent);
                break;
            }
            case R.id.nav_add_event:
            {
                Intent intent =new Intent(getApplicationContext(),EventActivity.class);
                intent.putExtra("username",user.getUsername());
                startActivity(intent);
                break;
            }
            case R.id.nav_all_events:
             {

                Intent intent =new Intent(getApplicationContext(),ShowEventsActivity.class);
                intent.putExtra("username",user.getUsername());

                startActivity(intent);

                break;
            }
            case R.id.nav_my_events:
            {

                Intent intent =new Intent(getApplicationContext(),ShowAllEventsActivity.class);
                intent.putExtra("username",user.getUsername());

                startActivity(intent);

                break;
            }
            case R.id.nav_signout: {

                LoginManager.getInstance().logOut();

                sessionManager.updateConnectionStatusForUser(user.getUsername(),0);
                sessionManager.initRememberMeStatus();
                Intent intent=new Intent(getApplicationContext(), AuthentificationActivity.class);
                startActivity(intent);

               break;



            }


        }

        return true;

          }


      });
        movetopostss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focusOnView();

                PostsFragment postFragment =    new PostsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("username",user.getUsername());
                postFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.frameHome,postFragment).commit();
                moveToAddPost.setVisibility(View.VISIBLE);
                movetopostss.setVisibility(View.VISIBLE);

            }
        });
    }

    private void bottomProgressDots(int current_index) {
        LinearLayout dotsLayout =  findViewById(R.id.layoutDotss);
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 10;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);
        }
    }



    private void initComponent() {
        viewPager = findViewById(R.id.viewpager);
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == MAX_STEP-1) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomProgressDots(position);
            }
        });


    }


    public void focusOnView()
    {
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                nested_scroll_view.scrollTo(0,frameHome.getTop());

            }
        });





    }

    @Override
    public void onBackPressed() {

        Intent intentHome =new Intent(getApplicationContext(), HomeActivity.class);
        intentHome.putExtra("username",user.getUsername());
        startActivity(intentHome);
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        private LayoutInflater layoutInflater1;
        private LayoutInflater layoutInflater2;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layoutInflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layoutInflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_article_stepper, container, false);

            //TextView text = (TextView) view.findViewById(R.id.text);
            if (position > 0) {
                //text.setVisibility(View.VISIBLE);
                //image.setBackgroundResource(R.drawable.helps);
                (view.findViewById(R.id.lyt_article_cover2)).setVisibility(View.VISIBLE);
                (view.findViewById(R.id.lyt_article_cover)).setVisibility(View.GONE);
                if (position % 2 == 0) {
                    //text.setText(R.string.long_lorem_ipsum_2);
                    (view.findViewById(R.id.lyt_article_cover3)).setVisibility(View.VISIBLE);
                    (view.findViewById(R.id.lyt_article_cover2)).setVisibility(View.GONE);
                } else {
                  //  text.setText(R.string.long_lorem_ipsum);
                }
            }
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return MAX_STEP;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}

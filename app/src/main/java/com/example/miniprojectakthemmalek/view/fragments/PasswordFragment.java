package com.example.miniprojectakthemmalek.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.User;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.view.ErrorHandlerActivity;
import com.example.miniprojectakthemmalek.view.HomeActivity;
import com.example.miniprojectakthemmalek.view.SessionManager;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PasswordFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextInputEditText passwordTextInput;
    MaterialRippleLayout connectBtn;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

String username;
SessionManager sessionManager;
    private View parent_view;

    ImageButton backAccount;

public PasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PasswordFragment newInstance(String param1, String param2) {
        PasswordFragment fragment = new PasswordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_password, container, false);

        username=getArguments().getString("username");

        passwordTextInput=rootView.findViewById(R.id.passwordTextInput);
        connectBtn=rootView.findViewById(R.id.connectBtn);
        sessionManager=new SessionManager(getContext());
        parent_view = rootView.findViewById(android.R.id.content);
        backAccount=rootView.findViewById(R.id.backAccount);

        backAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.accountFrame,new AccountsFragment()).commit();
            }
        });


        connectBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

      //  System.out.println("isWifiOn --------> "+isWifiOn(getContext()));
      // System.out.println("isInternetConnection --------> "+isInternetConnection(getContext()));

        UserRepository.getInstance().getOneUser(username, new UserRepository.getOneUserCallBack() {
            @Override
            public void onResponse(User user) {
                if(user !=null)
                {
                    if(user.getUsername().equals(username) && user.getPassword().equals(passwordTextInput.getText().toString()))
                    {
                        System.out.println("sssss = "+user);
                        if(sessionManager.getUser(user.getUsername())==null)
                        {
                            sessionManager.openSessionForUser(user);

                        }else
                        {
                            sessionManager.updateConnectionStatusForUser(user.getUsername(),1);
                        }




                        Intent intentHome =new Intent(getContext(), HomeActivity.class);
                        intentHome.putExtra("username",user.getUsername());
                        startActivity(intentHome);

                    }else  {

                        Snackbar.make(parent_view, "Wrong password or username", Snackbar.LENGTH_SHORT).show();

                    }

                }else{




                }
            }
        });


    }
});


        return rootView;
    }

    public static boolean isWifiOn(final Context context) {

        boolean status = false;

        final ConnectivityManager connManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(null != connManager) {
            NetworkInfo[] allNetworks = connManager.getAllNetworkInfo();
            if(null != allNetworks) {
                for(NetworkInfo info: allNetworks) {
                    if ( info.getState() == NetworkInfo.State.CONNECTED ) {
                        status = true;
                        break;
                    }
                }
            }
        }
        return status;
    }

    public static boolean isInternetConnection()
    {
        boolean success = false;
        try {

            URL url = new URL("https://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.connect();
            success = connection.getResponseCode() == 200;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }


    public boolean isConnectingToInternet(Context context) {
        if (networkConnectivity(context)) {
            try {
                Process p1 = Runtime.getRuntime().exec(
                        "ping -c 1 www.google.com");
                int returnVal = p1.waitFor();
                boolean reachable = (returnVal == 0);
                if (reachable) {
                    System.out.println("Internet access");
                    return reachable;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        } else
            return false;

    }

    private boolean networkConnectivity(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    private void runProgress(final ProgressBar progressBar) {
        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                int progress = progressBar.getProgress() + 10;
                progressBar.setProgress(progress);
                if (progress > 100) {
                    progressBar.setProgress(0);
                }
                mHandler.postDelayed(this, 1000);
            }
        };

        mHandler.post(runnable);
    }

}

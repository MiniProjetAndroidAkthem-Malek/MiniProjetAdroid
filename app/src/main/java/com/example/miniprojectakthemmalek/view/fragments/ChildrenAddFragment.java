package com.example.miniprojectakthemmalek.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Children;
import com.example.miniprojectakthemmalek.model.entities.Enums.Disease;
import com.example.miniprojectakthemmalek.model.repositories.ActivitiesRepository;
import com.example.miniprojectakthemmalek.model.repositories.ChildrenRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChildrenAddFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FloatingActionButton children_done;
    EditText childrenFullName,childrenDescription;
    AppCompatRadioButton radio_female,radio_male;
    Spinner spn_disease;
    DatePicker datePicker;
    String connectedUsername;



    public ChildrenAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChildrenAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChildrenAddFragment newInstance(String param1, String param2) {
        ChildrenAddFragment fragment = new ChildrenAddFragment();
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_children_add, container, false);
        children_done=rootView.findViewById(R.id.children_done);
        connectedUsername=getArguments().getString("username");
        childrenDescription=rootView.findViewById(R.id.childrenDescription);
        childrenFullName=rootView.findViewById(R.id.childrenFullName);
        radio_female=rootView.findViewById(R.id.radio_female);
        radio_male=rootView.findViewById(R.id.radio_male);
        spn_disease=rootView.findViewById(R.id.spn_disease);
        datePicker=rootView.findViewById(R.id.birth_date);

        children_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Children children=new Children();

                if(radio_male.isChecked())
                {
                        children.setSexe("Male");
                }
                if(radio_female.isChecked())
                {
                        children.setSexe("Female");
                }

               if( spn_disease.getSelectedItemPosition()==0)
               {
                   children.setDisease(Disease.PHYSICAL);

               }else if (spn_disease.getSelectedItemPosition()==1)
               {
                   children.setDisease(Disease.MENTAL);

               }



                Date date=new Date();
                date.setMonth(datePicker.getMonth());
                date.setDate(datePicker.getDayOfMonth());
                date.setYear(datePicker.getYear());

                SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
                String dateString = sdf.format(date);
                children.setBirthday(dateString);
                children.setParent(connectedUsername);
                children.setDescription(childrenDescription.getText().toString());
                children.setName(childrenFullName.getText().toString());

                ChildrenRepository.getInstance().addChildren(children, new ActivitiesRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {


                    }
                });

                ChildrenHomeFragment childrenHomeFragment=new ChildrenHomeFragment();
                Bundle bundle=new Bundle();
                bundle.putString("username",connectedUsername);
                childrenHomeFragment.setArguments(bundle);


                getFragmentManager().beginTransaction().replace(R.id.frameProfile,childrenHomeFragment).commit();


            }
        });


        return rootView;
    }


}

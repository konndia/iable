package com.example.iable;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class User extends Fragment {

//    //мой код
//    private TextView logout_txt;
//    private FirebaseAuth auth;
//    //конец

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public User() {
        // Required empty public constructor
    }

    public static User newInstance(String param1, String param2) {
        User fragment = new User();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //мой код

//        logout_txt = (TextView) getView().findViewById(R.id.logout_txt);
//        auth = FirebaseAuth.getInstance();
//        logout_txt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                auth.signOut();
//                Intent intent = new Intent(getActivity(), SignUp.class);
//                startActivity(intent);
////                FragmentManager fm = getActivity().getSupportFragmentManager();
////                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            }
//        });
        //конец


        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
}
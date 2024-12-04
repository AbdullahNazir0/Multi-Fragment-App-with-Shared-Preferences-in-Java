package com.example.assignment2;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileView extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView username_tv, email_tv, password_tv, theme_tv, notification_tv;

    private String mParam1;
    private String mParam2;

    public ProfileView() {
        // Required empty public constructor
    }

    public static ProfileView newInstance(String param1, String param2) {
        ProfileView fragment = new ProfileView();
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
        View view =  inflater.inflate(R.layout.fragment_profile_view, container, false);
        username_tv = view.findViewById(R.id.username_tv);
        email_tv = view.findViewById(R.id.email_tv);
        password_tv = view.findViewById(R.id.password_tv);
        theme_tv = view.findViewById(R.id.theme_tv);
        notification_tv = view.findViewById(R.id.notification_tv);

        SharedPreferences sharedPreferences = MainActivity.sharedPreferences;
        SharedPreferences.Editor editor = MainActivity.editor;
        String username = sharedPreferences.getString("username", "");
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        String theme = sharedPreferences.getString("theme", "false");
        String notification = sharedPreferences.getString("notification", "false");

        username_tv.setText(username);
        email_tv.setText(email);
        password_tv.setText(password);
        if(theme.equals("false")) {
            theme_tv.setText("Off");
        } else {
            theme_tv.setText("On");
        }
        if(notification.equals("false")) {
            notification_tv.setText("Off");
        } else {
            notification_tv.setText("On");
        }
        return view;
    }
}
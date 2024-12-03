package com.example.assignment2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class UserSettings extends Fragment {


    EditText username_et, email_et, password_et;
    Switch notification_sw, theme_sw;
    Button save_bt;

    LinearLayout usersettings_ll;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public UserSettings() {
    }
    public static UserSettings newInstance(String param1, String param2) {
        UserSettings fragment = new UserSettings();
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
        View view = inflater.inflate(R.layout.fragment_user_settings, container, false);

        username_et = view.findViewById(R.id.username_et);
        email_et = view.findViewById(R.id.email_et);
        password_et = view.findViewById(R.id.password_et);
        notification_sw = view.findViewById(R.id.notification_sw);
        theme_sw = view.findViewById(R.id.theme_sw);
        save_bt = view.findViewById(R.id.save_Bt);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String username = sharedPreferences.getString("username", "");
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        String theme = sharedPreferences.getString("theme", "false");
        String notification = sharedPreferences.getString("notification", "false");

        username_et.setText(username);
        email_et.setText(email);
        password_et.setText(password);
        notification_sw.setChecked(notification.equals("true"));
        theme_sw.setChecked(theme.equals("true"));
        usersettings_ll = view.findViewById(R.id.usersettings_ll);

        if(theme.equals("false")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username_et.getText() + "";
                String email = email_et.getText() + "";
                String password = password_et.getText() + "";
                String theme = theme_sw.isChecked() + "";
                String notification = theme_sw.isChecked() + "";

                if(theme.equals("false")) {
                    usersettings_ll.setBackgroundColor(Color.WHITE);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    usersettings_ll.setBackgroundColor(Color.BLACK);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }

                editor.putString("username", username);
                editor.putString("email", email);
                editor.putString("password", password);
                editor.putString("theme", theme);
                editor.putString("notification", notification);
                editor.apply();

                Toast.makeText(getContext(), "Settings saved successfully", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
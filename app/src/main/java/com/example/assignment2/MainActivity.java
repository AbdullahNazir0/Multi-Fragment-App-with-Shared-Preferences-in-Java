package com.example.assignment2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    Button userProfile_bt, userSettings_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("UserSettings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        userProfile_bt = findViewById(R.id.userProfile_bt);
        userSettings_bt = findViewById(R.id.userSettings_bt);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if(savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new ProfileView())
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
        }

        userProfile_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new ProfileView())
                        .setReorderingAllowed(true)
                        .addToBackStack("ProfileView")
                        .commit();
            }
        });

        userSettings_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new UserSettings())
                        .setReorderingAllowed(true)
                        .addToBackStack("UserSettings")
                        .commit();
            }
        });
    }
}
package com.example.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.enter_fragment_container, EnterFragment.class, null)
                    .commit();
        }

    }
}
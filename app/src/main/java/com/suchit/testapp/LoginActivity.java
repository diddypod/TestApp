package com.suchit.testapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LoginActivity extends AppCompatActivity {
    
    String grades [] = {"Select Grade", "4th", "5th","6th", "7th", "8th", "9th", "10th"};
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);

        Toolbar myToolbar = findViewById(R.id.loginToolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Spinner mySpinner = findViewById(R.id.grade);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout, R.id.gradeText, grades);
        mySpinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

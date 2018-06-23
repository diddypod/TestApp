package com.suchit.testapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.OnClick;

public class ReadyActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_ready);
    }

    @OnClick(R.id.start_test)
    public void startTest(View view){
        Intent intent = new Intent(this, TestActivity.class);
        startActivity (intent);
    }
}

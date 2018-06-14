package com.suchit.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.takeTest)
    public void onTestClicked(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity (intent);
    }
    @OnClick(R.id.viewResults)
    public void onResultsClicked(){
    }

}

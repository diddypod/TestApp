package com.suchit.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.take_test_button)
    public void onTestClicked(){
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity (intent);
    }

    @OnClick(R.id.view_results_button)
    public void onResultsClicked(){
    }

}

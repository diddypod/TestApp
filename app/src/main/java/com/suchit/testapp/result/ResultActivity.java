package com.suchit.testapp.result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.suchit.testapp.R;

import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
    }

}

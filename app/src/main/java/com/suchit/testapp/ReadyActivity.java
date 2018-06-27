package com.suchit.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.suchit.testapp.test.TestActivity;

import butterknife.OnClick;

import static com.suchit.testapp.data.Test.currentTest;

public class ReadyActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_ready);
    }

    @OnClick(R.id.start_test)
    public void startTest(View view){
        currentTest.setQuestions(this);

        Intent intent = new Intent(this, TestActivity.class);
        startActivity (intent);
    }
}

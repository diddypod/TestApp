package com.suchit.testapp.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.suchit.testapp.R;
import com.suchit.testapp.result.ResultActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.suchit.testapp.data.Result.currentResult;
import static com.suchit.testapp.data.Test.currentTest;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.clock)
    TextView clock;

    private TestTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager);

        clock.setText("20:00");
        timer = new TestTimer(1200000, 1000);
        timer.start();
    }

    @OnClick(R.id.pause_button)
    public void pauseTimer(View view){
        long timeLeft = timer.getTimeLeft();
        timer.cancel();
        timer = new TestTimer(timeLeft, 1000);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Test is currently paused. Press OK to resume.");
        builder.setCancelable(false);
        builder.setNeutralButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        timer.start();
                    }
                });
        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).
                        setTextColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.darkBlue));
            }
        });
        alertDialog.show();
    }

    @OnClick(R.id.end_test_button)
    public void endTest(){
        long timeLeft = timer.getTimeLeft();
        timer.cancel();
        timer = new TestTimer(timeLeft, 1000);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(false);
        builder.setPositiveButton(
                "NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        timer.start();
                    }
                });
        builder.setNegativeButton(
                "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        currentResult.evaluateTest(currentTest);
                        Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                        getBaseContext().startActivity (intent);
                    }
                });
        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).
                        setTextColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.darkBlue));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).
                        setTextColor(ContextCompat.getColor(getApplicationContext(),
                                R.color.darkBlue));
            }
        });
        alertDialog.show();
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int pos) {
            if (pos<20) {
                return QuestionFragment.newInstance(currentTest.getQuestions().get(pos).toArrayList(), pos);
            }
            return QuestionFragment.newInstance(new ArrayList<String>(), -1);
        }

        @Override
        public int getCount() {
            return 20;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return Integer.toString(position+1);
        }

    }

    private class TestTimer extends CountDownTimer {
        private long timeLeft;
        public TestTimer(long millisInFuture, long countDownInterval){
            super(millisInFuture, countDownInterval);
        }
        public void onTick(long millisUntilFinished) {
            timeLeft = millisUntilFinished;
            long secondsUntilFinished = millisUntilFinished/1000;
            long minutesUntilFinished = secondsUntilFinished/60;
            long secondsRemainder = secondsUntilFinished%60;
            clock.setText(minutesUntilFinished + ":" +
                    ((secondsRemainder+"").length() == 2 ?
                            secondsRemainder : "0" + secondsRemainder));
        }
        public void onFinish() {
            //TODO
        }
        public long getTimeLeft() {
            return timeLeft;
        }
    }
}
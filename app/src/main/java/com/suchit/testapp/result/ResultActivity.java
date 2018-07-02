package com.suchit.testapp.result;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.suchit.testapp.R;
import com.suchit.testapp.test.QuestionFragment;
import com.suchit.testapp.test.TestActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suchit.testapp.data.Result.currentResult;
import static com.suchit.testapp.data.Test.currentTest;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.result_pager)
    ViewPager pager;
    @BindView(R.id.result_toolbar)
    Toolbar resultToolbar;
    @BindView(R.id.name_result)
    TextView name;
    @BindView(R.id.grade_result)
    TextView grade;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        setSupportActionBar(resultToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        name.setText(currentResult.getName());
        grade.setText(currentResult.getGrade());
    }


    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos){
                case 0:
                    return OverviewFragment.newInstance();
                case 1:
                    return SectionFragment.newInstance();
                case 2:
                    return ProfileFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}

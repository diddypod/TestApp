package com.suchit.testapp.result;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.suchit.testapp.R;
import com.suchit.testapp.test.QuestionFragment;
import com.suchit.testapp.test.TestActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suchit.testapp.data.Test.currentTest;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.result_pager)
    ViewPager pager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
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
                case 3:
                    return SummaryFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}

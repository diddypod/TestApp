package com.suchit.testapp;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int pos) {
            if (pos<20) {
                return QuestionFragment.newInstance("QuestionFragment,"+Integer.toString(pos+1));
            }
            return QuestionFragment.newInstance("whoopsies");
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
}
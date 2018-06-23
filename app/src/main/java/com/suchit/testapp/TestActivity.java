package com.suchit.testapp;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends FragmentActivity {

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ActionBar actionBar = getActionBar();

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
            switch(pos) {
                case 0: return QuestionFragment.newInstance("QuestionFragment, 0");
                case 1: return QuestionFragment.newInstance("QuestionFragment, 1");
                case 2: return QuestionFragment.newInstance("QuestionFragment, 2");
                case 3: return QuestionFragment.newInstance("QuestionFragment, 3");
                case 4: return QuestionFragment.newInstance("QuestionFragment, 4");
                default: return QuestionFragment.newInstance("QuestionFragment, Default");
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return Integer.toString(position);
        }

    }
}
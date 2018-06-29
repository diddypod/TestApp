package com.suchit.testapp.result;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suchit.testapp.R;

import butterknife.ButterKnife;

public class OverviewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    public static OverviewFragment newInstance() {
        return new OverviewFragment();
    }
}
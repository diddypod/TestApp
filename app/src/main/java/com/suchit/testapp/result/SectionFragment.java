package com.suchit.testapp.result;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suchit.testapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_section, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    public static SectionFragment newInstance(String text) {
        return new SectionFragment();
    }
}

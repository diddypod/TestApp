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

public class ProfileFragment extends Fragment {

    @BindView(R.id.profile_text)
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        String text = getArguments().getString("msg");
        textView.setText(text);

        return view;
    }

    public static ProfileFragment newInstance(String text) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);
        fragment.setArguments(b);
        return fragment;
    }
}

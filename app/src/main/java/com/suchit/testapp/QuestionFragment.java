package com.suchit.testapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionFragment extends Fragment {
    @BindView(R.id.question_text)
    TextView question;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, v);

        question.setText(getArguments().getString("msg"));

        return v;
    }

    public static QuestionFragment newInstance(String text) {

        QuestionFragment questionFragment = new QuestionFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        questionFragment.setArguments(b);

        return questionFragment;
    }
}

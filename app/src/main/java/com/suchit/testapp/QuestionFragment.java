package com.suchit.testapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionFragment extends Fragment {

    @BindView(R.id.question_text)
    TextView question;
    @BindView(R.id.question_image)
    ImageView questionImage;
    @BindView(R.id.option_a)
    RadioButton optionA;
    @BindView(R.id.option_b)
    RadioButton optionB;
    @BindView(R.id.option_c)
    RadioButton optionC;
    @BindView(R.id.option_d)
    RadioButton optionD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, v);

        ArrayList<String> values = getArguments().getStringArrayList("msg");

        question.setText(values.get(0));
        if (values.get(1).equals("true")){
            questionImage.setVisibility(View.VISIBLE);
            questionImage.setImageDrawable(ContextCompat.getDrawable(getActivity(),
                    getResources().getIdentifier(values.get(2), "drawable",
                            getActivity().getPackageName())));
        }
        optionA.setText(values.get(3));
        optionB.setText(values.get(5));
        optionC.setText(values.get(7));
        optionD.setText(values.get(9));
        return v;
    }

    public static QuestionFragment newInstance(ArrayList<String > questions) {
        QuestionFragment questionFragment = new QuestionFragment();
        Bundle b = new Bundle();
        b.putStringArrayList("msg", questions);
        questionFragment.setArguments(b);
        return questionFragment;
    }
}

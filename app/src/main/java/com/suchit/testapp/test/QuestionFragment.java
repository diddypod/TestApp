package com.suchit.testapp.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.suchit.testapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suchit.testapp.data.Test.currentTest;

public class QuestionFragment extends Fragment {

    @BindView(R.id.question_text)
    TextView question;
    @BindView(R.id.question_image)
    ImageView questionImage;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
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
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, view);

        ArrayList<String> questionArray = getArguments().getStringArrayList("msg");
        inflateQuestion(questionArray);
        final int questionNumber = Integer.valueOf(questionArray.get(0));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int optionNumber = radioGroup.indexOfChild(radioGroup.
                        findViewById(radioGroup.getCheckedRadioButtonId()));
                currentTest.getQuestions().get(questionNumber).setAnswered();
                currentTest.getQuestions().get(questionNumber).resetOptions();
                currentTest.getQuestions().get(questionNumber).
                        getOptions().get(optionNumber).setChosen();
            }
        });

        return view;
    }

    public static QuestionFragment newInstance(ArrayList<String > questions, int position) {
        questions.set(0, Integer.toString(position));
        QuestionFragment questionFragment = new QuestionFragment();
        Bundle b = new Bundle();
        b.putStringArrayList("msg", questions);
        questionFragment.setArguments(b);
        return questionFragment;
    }

    private void inflateQuestion(ArrayList<String> values){
        question.setText(values.get(1));
        if (values.get(2).equals("true")){
            questionImage.setVisibility(View.VISIBLE);
            questionImage.setImageDrawable(ContextCompat.getDrawable(getActivity(),
                    getResources().getIdentifier(values.get(3), "drawable",
                            getActivity().getPackageName())));
        }
        optionA.setText(values.get(4));
        optionB.setText(values.get(6));
        optionC.setText(values.get(8));
        optionD.setText(values.get(10));
    }
}

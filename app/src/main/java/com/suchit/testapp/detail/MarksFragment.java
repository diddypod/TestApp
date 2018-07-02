package com.suchit.testapp.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.suchit.testapp.R;
import com.suchit.testapp.ReadyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suchit.testapp.data.Test.currentTest;

public class MarksFragment extends Fragment implements View.OnClickListener{

    private View view;

    @BindView(R.id.submit_marks)
    Button submit;
    @BindView(R.id.maths_marks)
    EditText mathMarksText;
    @BindView(R.id.science_marks)
    EditText scienceMarksText;
    @BindView(R.id.english_marks)
    EditText englishMarksText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_marks, container, false);
        ButterKnife.bind(this, view);

        submit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        float mathMarks;
        float scienceMarks;
        float englishMarks;
        if (mathMarksText.getText().toString().isEmpty()){
            mathMarksText.setError("Please enter marks.");
            return;
        }
        else {
            mathMarks = Float.valueOf(mathMarksText.getText().toString());
            if (mathMarks > 100 || mathMarks < 0) {
                mathMarksText.setError("Invalid value");
                return;
            }
        }
        if (scienceMarksText.getText().toString().isEmpty()){
            scienceMarksText.setError("Please enter marks.");
            return;
        }
        else {
            scienceMarks = Float.valueOf(scienceMarksText.getText().toString());
            if (scienceMarks > 100 || scienceMarks < 0) {
                scienceMarksText.setError("Invalid value");
                return;
            }
        }
        if (englishMarksText.getText().toString().isEmpty()){
            englishMarksText.setError("Please enter marks.");
            return;
        }
        else {
            englishMarks = Float.valueOf(englishMarksText.getText().toString());
            if (englishMarks > 100 || englishMarks < 0) {
                englishMarksText.setError("Invalid value");
                return;
            }
        }
        currentTest.setMarks(mathMarks, scienceMarks, englishMarks);

        Intent intent = new Intent(getActivity(), ReadyActivity.class);
        startActivity (intent);
    }
}

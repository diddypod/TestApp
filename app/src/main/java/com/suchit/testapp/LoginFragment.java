package com.suchit.testapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private View view;

    @BindView(R.id.grade)
    Spinner gradeSpinner;
    @BindView(R.id.submit_details)
    Button submitButton;
    @BindView(R.id.name_text)
    TextView nameText;
    @BindView(R.id.email_text)
    TextView emailText;
    @BindView(R.id.number_text)
    TextView numberText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(adapter);

        submitButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view){
        MarksFragment marksFragment = new MarksFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.details_fragment, marksFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

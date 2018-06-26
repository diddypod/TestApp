package com.suchit.testapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suchit.testapp.Test.currentTest;

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
        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String phone = numberText.getText().toString();
        long grade = gradeSpinner.getSelectedItemId();
        if (name.isEmpty()){
            nameText.setError("Please enter name");
            return;
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Please enter valid email");
            return;
        }
        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()){
            numberText.setError("Please enter valid phone number");
            return;
        }
        if (grade == 0){
            Toast.makeText(getActivity().getApplicationContext(), "Please select a grade.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        currentTest.setLoginDetails(name, email, phone, grade);

        MarksFragment marksFragment = new MarksFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.details_fragment, marksFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

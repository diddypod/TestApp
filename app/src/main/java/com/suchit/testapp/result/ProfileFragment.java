package com.suchit.testapp.result;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.suchit.testapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suchit.testapp.data.Result.currentResult;

public class ProfileFragment extends Fragment {

    @BindView(R.id.profile_pie)
    PieChart profilePie;
    @BindView(R.id.profile_bar)
    BarChart profileBar;
    @BindView(R.id.profile_comparison_bar)
    BarChart profileComparisonBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        setProfilePie();
        setProfileBar();
        setProfileComparisonBar();

        return view;
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    private void setProfilePie(){
        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(currentResult.getVisualCorrectPercentage(), "Visual"));
        entries.add(new PieEntry(currentResult.getTextCorrectPercentage(), "Textual"));

        PieDataSet dataSet = new PieDataSet(entries, "Learning Profile");
        dataSet.setColors(ContextCompat.getColor(getActivity(),R.color.red),
                ContextCompat.getColor(getActivity(),R.color.darkBlue));
        PieData data = new PieData(dataSet);
        profilePie.setData(data);
        profilePie.getDescription().setEnabled(false);
        profilePie.setRotationEnabled(false);

        profilePie.invalidate();
    }
    private void setProfileBar(){
        ArrayList<BarEntry> perfList = new ArrayList<>();

        perfList.add(new BarEntry(0, currentResult.getVisualCorrectPercentage()));
        perfList.add(new BarEntry(1, currentResult.getTextCorrectPercentage()));

        BarDataSet current = new BarDataSet(perfList, "Current test");

        current.setColor(ContextCompat.getColor(getActivity(),R.color.orange));
        current.setDrawValues(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(current);

        final ArrayList<String> labels = new ArrayList<>();
        labels.add("Visual");
        labels.add("Textual");
        BarData data = new BarData(dataSets);

        profileBar.setData(data);
        profileBar.getDescription().setEnabled(false);
        profileBar.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels.get((int)value);
            }
        });
        XAxis xAxis = profileBar.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(-0.5f);
        xAxis.setAxisMaximum(1.5f);
        xAxis.setGranularityEnabled(false);
        xAxis.setCenterAxisLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(8);
        xAxis.setLabelCount(perfList.size());
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        profileBar.getAxisLeft().setAxisMaximum(100);
        profileBar.getAxisRight().setAxisMaximum(100);
        profileBar.getAxisLeft().setAxisMinimum(0);
        profileBar.getAxisRight().setAxisMinimum(0);

        profileBar.invalidate();
    }
    private void setProfileComparisonBar(){
        ArrayList<BarEntry> scoresList = new ArrayList<>();
        ArrayList<BarEntry> schoolScoresList = new ArrayList<>();

        scoresList.add(new BarEntry(0, currentResult.getTotalPercentage()));
        scoresList.add(new BarEntry(1, currentResult.getMathPercentage()));
        scoresList.add(new BarEntry(2, currentResult.getSciencePercentage()));
        scoresList.add(new BarEntry(3, currentResult.getEnglishPercentage()));
        schoolScoresList.add(new BarEntry(0, currentResult.getSchoolFullPercentage()));
        schoolScoresList.add(new BarEntry(1, currentResult.getSchoolMathPercentage()));
        schoolScoresList.add(new BarEntry(2, currentResult.getSchoolSciencePercentage()));
        schoolScoresList.add(new BarEntry(3, currentResult.getSchoolEnglishPercentage()));

        BarDataSet current = new BarDataSet(scoresList, "Current test");
        BarDataSet school = new BarDataSet(schoolScoresList, "School performance");

        current.setColor(ContextCompat.getColor(getActivity(),R.color.orange));
        current.setDrawValues(false);
        school.setColor(ContextCompat.getColor(getActivity(),R.color.grey));
        school.setDrawValues(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(current);
        dataSets.add(school);

        final ArrayList<String> labels = new ArrayList<>();
        labels.add("Overall");
        labels.add("Math");
        labels.add("Science");
        labels.add("English");

        BarData data = new BarData(dataSets);

        float groupSpace = 0.06f;
        float barSpace = 0.02f;
        float barWidth = 0.45f;
        data.setBarWidth(barWidth);
        profileComparisonBar.setData(data);
        profileComparisonBar.groupBars(-0.5f, groupSpace, barSpace);

        profileComparisonBar.getDescription().setEnabled(false);
        XAxis xAxis = profileComparisonBar.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels.get((int)value);
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(-0.5f);
        xAxis.setAxisMaximum(3.5f);
        xAxis.setGranularityEnabled(false);
        xAxis.setCenterAxisLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(8);
        xAxis.setLabelCount(scoresList.size());
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        profileComparisonBar.getAxisLeft().setAxisMaximum(100);
        profileComparisonBar.getAxisRight().setAxisMaximum(100);
        profileComparisonBar.getAxisLeft().setAxisMinimum(0);
        profileComparisonBar.getAxisRight().setAxisMinimum(0);
        profileComparisonBar.invalidate();
    }
}

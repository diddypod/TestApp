package com.suchit.testapp.result;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.suchit.testapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suchit.testapp.data.Result.currentResult;

public class SectionFragment extends Fragment {

    @BindView(R.id.section_bar)
    BarChart sectionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_section, container, false);
        ButterKnife.bind(this, view);

        setSectionBar();

        return view;
    }

    public static SectionFragment newInstance() {
        return new SectionFragment();
    }

    private void setSectionBar(){
        ArrayList<BarEntry> scoresList = new ArrayList<>();

        scoresList.add(new BarEntry(0, currentResult.getTotalPercentage()));
        scoresList.add(new BarEntry(1, currentResult.getMathPercentage()));
        scoresList.add(new BarEntry(2, currentResult.getSciencePercentage()));
        scoresList.add(new BarEntry(3, currentResult.getEnglishPercentage()));
        scoresList.add(new BarEntry(4, currentResult.getTimePercentage()));

        BarDataSet current = new BarDataSet(scoresList, "Current test");

        current.setColor(ContextCompat.getColor(getActivity(),R.color.orange));
        current.setDrawValues(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(current);

        final ArrayList<String> labels = new ArrayList<>();
        labels.add("Overall");
        labels.add("Math");
        labels.add("Science");
        labels.add("English");
        labels.add("Speed");

        BarData data = new BarData(dataSets);

        sectionBar.setData(data);
        sectionBar.getDescription().setEnabled(false);
        XAxis xAxis = sectionBar.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels.get((int)value);
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(-0.5f);
        xAxis.setAxisMaximum(4.5f);
        xAxis.setGranularityEnabled(false);
        xAxis.setCenterAxisLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(8);
        xAxis.setLabelCount(scoresList.size());
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        sectionBar.getAxisLeft().setAxisMaximum(100);
        sectionBar.getAxisRight().setAxisMaximum(100);
        sectionBar.getAxisLeft().setAxisMinimum(0);
        sectionBar.getAxisRight().setAxisMinimum(0);
        sectionBar.invalidate();
    }
}
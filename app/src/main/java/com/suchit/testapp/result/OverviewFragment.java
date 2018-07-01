package com.suchit.testapp.result;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.anastr.speedviewlib.SpeedView;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.suchit.testapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suchit.testapp.data.Result.currentResult;

public class OverviewFragment extends Fragment {


    @BindView(R.id.performance_meter)
    SpeedView meter;
    @BindView(R.id.performance_label)
    TextView performanceLabel;
    @BindView(R.id.performance_radar)
    RadarChart performanceRadar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        ButterKnife.bind(this, view);

        setMeter();
        setRadar();

        return view;
    }

    public static OverviewFragment newInstance() {
        return new OverviewFragment();
    }

    private void setMeter() {
        float percentage = currentResult.getTotalPercentage();
        meter.speedPercentTo((int) percentage);
        if (percentage >= 0 && percentage < 20){
            performanceLabel.setText("Poor");
        }
        else if (percentage >= 20 && percentage < 40){
            performanceLabel.setText("Needs improvement");
        }
        else if (percentage >= 40 && percentage < 60){
            performanceLabel.setText("Average");
        }
        else if (percentage >= 60 && percentage < 80){
            performanceLabel.setText("goodList");
        }
        else if (percentage >= 80 && percentage < 100){
            performanceLabel.setText("Outstanding");
        }
    }
    private void setRadar(){
        ArrayList<RadarEntry> currentList = new ArrayList<>();
        ArrayList<RadarEntry> goodList = new ArrayList<>();
        ArrayList<RadarEntry> badList = new ArrayList<>();

        currentList.add(new RadarEntry(currentResult.getTotalPercentage()/5));
        currentList.add(new RadarEntry(currentResult.getMathPercentage()/5));
        currentList.add(new RadarEntry(currentResult.getSciencePercentage()/5));
        currentList.add(new RadarEntry(currentResult.getEnglishPercentage()/5));
        currentList.add(new RadarEntry(currentResult.getTimePercentage()/5));
        goodList.add(new RadarEntry(19));
        goodList.add(new RadarEntry(19));
        goodList.add(new RadarEntry(19));
        goodList.add(new RadarEntry(19));
        goodList.add(new RadarEntry(10));
        badList.add(new RadarEntry(7));
        badList.add(new RadarEntry(7));
        badList.add(new RadarEntry(7));
        badList.add(new RadarEntry(7));
        badList.add(new RadarEntry(10));

        RadarDataSet current = new RadarDataSet(currentList, "This test");
        RadarDataSet good = new RadarDataSet(goodList, "Good");
        RadarDataSet bad = new RadarDataSet(badList, "Bad");

        current.setColor(ContextCompat.getColor(getActivity(),R.color.orange));
        current.setDrawValues(false);
        good.setColor(ContextCompat.getColor(getActivity(),R.color.green));
        good.setDrawFilled(true);
        good.setFillColor(ContextCompat.getColor(getActivity(),R.color.green));
        good.setDrawValues(false);
        bad.setColor(ContextCompat.getColor(getActivity(),R.color.red));
        bad.setDrawFilled(true);
        bad.setFillColor(ContextCompat.getColor(getActivity(),R.color.red));
        bad.setDrawValues(false);

        ArrayList<IRadarDataSet> dataSets = new ArrayList<>();
        dataSets.add(current);
        dataSets.add(good);
        dataSets.add(bad);

        RadarData data = new RadarData(dataSets);

        final ArrayList<String> labels = new ArrayList<>();
        labels.add("Overall");
        labels.add("Math");
        labels.add("Science");
        labels.add("English");
        labels.add("Speed");

        performanceRadar.setData(data);
        performanceRadar.invalidate();
        performanceRadar.setWebLineWidthInner(0.5f);
        performanceRadar.getYAxis().setAxisMaxValue(20);
        performanceRadar.getYAxis().setAxisMinimum(0);
        performanceRadar.setRotationEnabled(false);
        performanceRadar.getDescription().setEnabled(false);
        performanceRadar.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels.get((int)value);
            }
        });
    }
}
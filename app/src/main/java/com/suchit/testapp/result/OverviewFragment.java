package com.suchit.testapp.result;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
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
    @BindView(R.id.summary_table)
    TableLayout summaryTable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        ButterKnife.bind(this, view);

        setMeter();
        setRadar();
        setSummary();

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

        currentList.add(new RadarEntry(currentResult.getTotalPercentage()));
        currentList.add(new RadarEntry(currentResult.getMathPercentage()));
        currentList.add(new RadarEntry(currentResult.getSciencePercentage()));
        currentList.add(new RadarEntry(currentResult.getEnglishPercentage()));
        currentList.add(new RadarEntry(currentResult.getTimePercentage()));
        goodList.add(new RadarEntry(95));
        goodList.add(new RadarEntry(95));
        goodList.add(new RadarEntry(95));
        goodList.add(new RadarEntry(95));
        goodList.add(new RadarEntry(50));
        badList.add(new RadarEntry(35));
        badList.add(new RadarEntry(35));
        badList.add(new RadarEntry(35));
        badList.add(new RadarEntry(35));
        badList.add(new RadarEntry(50));

        RadarDataSet current = new RadarDataSet(currentList, "Current test");
        RadarDataSet good = new RadarDataSet(goodList, "Good performance");
        RadarDataSet bad = new RadarDataSet(badList, "Bad performance");

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
        performanceRadar.setWebLineWidthInner(0.5f);
        performanceRadar.getYAxis().setAxisMaxValue(100);
        performanceRadar.getYAxis().setAxisMinimum(0);
        performanceRadar.setRotationEnabled(false);
        performanceRadar.getDescription().setEnabled(false);
        performanceRadar.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels.get((int)value);
            }
        });
        performanceRadar.invalidate();
    }
    private void setSummary(){
        addRow("SCORE:", currentResult.getTotalMarks() + " / " +
                currentResult.getFullMarks());
        addRow("MATHS:", currentResult.getMathMarks() + " / " +
                currentResult.getMathFullMarks());
        addRow("ENGLISH:", currentResult.getEnglishMarks() + " / " +
                currentResult.getEnglishFullMarks());
        addRow("SCIENCE:", currentResult.getScienceMarks() + " / " +
                currentResult.getScienceFullMarks());
        int timeUsedSec = currentResult.getFullTimeSeconds()-currentResult.getTimeLeftSeconds();
        int fullTimeSec = currentResult.getFullTimeSeconds();
        addRow("TIME TAKEN:", timeUsedSec/60 + ":" +
                ((timeUsedSec%60+"").length() == 2 ? timeUsedSec%60 : "0" + timeUsedSec%60) +
                " / " + fullTimeSec/60 + ":" +
                ((fullTimeSec%60+"").length() == 2 ? fullTimeSec%60 : "0" + fullTimeSec%60));
    }

    private void addRow (String parameterString, String valueString){
        TableRow row = new TableRow(getActivity());
        row.setPadding(5,5,5,5);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        row.setLayoutParams(lp);

        TextView parameter = new TextView(getActivity());
        parameter.setText(parameterString);
        parameter.setTextSize(15);
        parameter.setTextColor(ContextCompat.getColor(getActivity(),R.color.black));
        parameter.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        parameter.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        parameter.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT));
        row.addView(parameter);

        TextView value = new TextView(getActivity());
        value.setText(valueString);
        value.setTextSize(15);
        value.setTextColor(ContextCompat.getColor(getActivity(),R.color.black));
        value.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        value.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT));
        row.addView(value);

        summaryTable.addView(row);
    }
}
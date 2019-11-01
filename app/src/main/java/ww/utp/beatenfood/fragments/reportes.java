package ww.utp.beatenfood.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import ww.utp.beatenfood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class reportes extends Fragment {

    BarChart barChart;
    public reportes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reportes, container, false);
        barChart=view.findViewById(R.id.graficopastel);
        creargraficopastel();
        return view;
    }
    private void  creargraficopastel(){

        //  barChart.animateY(5000);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(8f, 0));
        entries.add(new BarEntry(2f, 1));
        entries.add(new BarEntry(5f, 2));
        entries.add(new BarEntry(20f, 3));
        entries.add(new BarEntry(15f, 4));
        entries.add(new BarEntry(19f, 5));
        entries.add(new BarEntry(8f, 6));
        entries.add(new BarEntry(2f, 7));
        entries.add(new BarEntry(5f, 8));
        entries.add(new BarEntry(20f, 9));
        entries.add(new BarEntry(15f, 10));
        entries.add(new BarEntry(19f, 11));

        BarDataSet bardataset = new BarDataSet(entries, "Cells");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Dec");
        labels.add("Nov");
        labels.add("Oct");
        labels.add("Sep");
        labels.add("Aug");
        labels.add("Jul");
        labels.add("Jun");
        labels.add("May");
        labels.add("Apr");
        labels.add("Mar");
        labels.add("Feb");
        labels.add("Jan");

        BarData data = new BarData(labels, bardataset);
        barChart.setData(data); // set the data and list of lables into chart

        barChart.setDescription("Expenditure for the year 2018");  // set the description

        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(1500);
    }

}

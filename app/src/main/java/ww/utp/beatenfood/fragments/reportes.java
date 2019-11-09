package ww.utp.beatenfood.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    Button mes;
    BarChart barChart;
    private int mYear, mMonth, mDay;
    public reportes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reportes, container, false);
        barChart=view.findViewById(R.id.graficopastel);
        mes=view.findViewById(R.id.btnreporte);

        creargraficopastel();
        mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
    private void  creargraficopastel(){

        //  barChart.animateY(5000);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(20f, 0));
        entries.add(new BarEntry(2f, 1));


        BarDataSet bardataset = new BarDataSet(entries, "Cells");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Consumidos");
        labels.add("Malogrados");


        BarData data = new BarData(labels, bardataset);
        barChart.setData(data); // set the data and list of lables into chart
        barChart.setDescription("Expenditure for the year 2018");  // set the description

        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(1500);
    }


}

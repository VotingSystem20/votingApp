package com.example.project;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;


public class piechartresult extends Activity {


    private int[] votecount={85,11,3,1};
    private String[] partiesresultname={"BJP","AAM","CONGRESS","OTHER"};
    PieChart pieChart;
    TextView tvq,tvq1;

//    PieChart pieChart;
//    PieData pieData;
//    PieDataSet pieDataSet;
//    ArrayList pieEntries;
//    ArrayList PieEntryLabels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechartresult);

        tvq=findViewById(R.id.pieparty);
        tvq1=findViewById(R.id.piepartycount);


        pieChart=(PieChart)findViewById(R.id.piechartview);
        
        addDataset();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                int ww=Integer.parseInt(e.getData().toString());


                int we= votecount[ww];
                tvq.setText(partiesresultname[ww]+"");
                tvq1.setText(""+we);

               // tvq1.setText(votecount[Integer.parseInt(e.getData().toString())]);


            }

            @Override
            public void onNothingSelected() {

            }
        });
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//


//        View rootview=inflater.inflate(R.layout.fregment_account,container,false);


        //        getEntries();
//        pieDataSet = new PieDataSet(pieEntries, "");
//        pieData = new PieData(pieDataSet);
//        pieChart.setData(pieData);
//        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//        pieDataSet.setSliceSpace(2f);
//        pieDataSet.setValueTextColor(Color.WHITE);
//        pieDataSet.setValueTextSize(10f);
//        pieDataSet.setSliceSpace(5f);

        //return rootview;
//a
//    setuppiechart();
//
//        Pie pie= AnyChart.pie();
//
//        for (int i=0;i<vote.length(),i++){
//
//    }




//    private void getEntries() {
//        pieEntries = new ArrayList<>();
//        pieEntries.add(new PieEntry(2f, 0));
//        pieEntries.add(new PieEntry(4f, 1));
//        pieEntries.add(new PieEntry(6f, 2));
//        pieEntries.add(new PieEntry(8f, 3));
//        pieEntries.add(new PieEntry(7f, 4));
//        pieEntries.add(new PieEntry(3f, 5));
//    }


}

    private void addDataset() {

        ArrayList<PieEntry> yentry=new ArrayList<>();

        ArrayList<String> xentry=new ArrayList<>();


        for (int i=0;i<votecount.length;i++){
            yentry.add(new PieEntry(votecount[i],i));
        }


        for (int i=1; i<partiesresultname.length; i++){
            xentry.add(partiesresultname[i]);
        }

        PieDataSet pieDataSet=new PieDataSet(yentry,"Election result");

        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(0);

        ArrayList<Integer> colors=new ArrayList<>();

        colors.add(Color.rgb(242,121,14));
        colors.add(Color.rgb(27,229,3));
        colors.add(Color.BLUE);
        colors.add(Color.GRAY);

        pieDataSet.setColors(colors);


        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);


        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }


}

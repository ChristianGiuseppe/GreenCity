package com.example.greencity.activity;

import android.os.Bundle;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


import com.example.greencity.R;
import com.example.greencity.pojo.InformazioniGenerali;
import com.example.greencity.pojo.Markers;

public class Pie_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_);


        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Pie pie = AnyChart.pie();

        ArrayList <Markers>listaWait =  new  ArrayList();
        ArrayList <Markers>listaDone =  new  ArrayList();
        ArrayList <Markers>listaReject =  new  ArrayList();

        listaWait = InformazioniGenerali.getInformazioniGenerali().listaWait;
        listaDone = InformazioniGenerali.getInformazioniGenerali().listaDone;
        listaReject = InformazioniGenerali.getInformazioniGenerali().listaReject;


        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("In Attesa", listaWait.size()));
        data.add(new ValueDataEntry("Confermato", listaDone.size()));
        data.add(new ValueDataEntry("Rifiutato", listaReject.size()));

        pie.data(data);

        pie.title("Status Report Utenti");

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                //.text("Retail channels")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        anyChartView.setChart(pie);

    }

}

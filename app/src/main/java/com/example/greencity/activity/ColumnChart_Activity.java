package com.example.greencity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.greencity.R;
import com.example.greencity.pojo.InformazioniGenerali;
import com.example.greencity.pojo.Markers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ColumnChart_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_);

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        ArrayList <Markers>listaWait =  new  ArrayList();
        ArrayList <Markers>listaWaitLatitudine =  new  ArrayList();
        ArrayList <Markers>listaDone =  new  ArrayList();
        ArrayList <Markers>listaReject =  new  ArrayList();

        listaWait = InformazioniGenerali.getInformazioniGenerali().listaWait;
        listaDone = InformazioniGenerali.getInformazioniGenerali().listaDone;
        listaReject = InformazioniGenerali.getInformazioniGenerali().listaReject;

        ArrayList<String> months = new ArrayList<String>();



        List<DataEntry> data = new ArrayList<>();
        String nomeMese ="";
        Integer countGennaioWait =0 ;
        Integer countFebbraioWait =0 ;
        Integer countMarzoWait =0 ;
        Integer countAprileWait =0 ;
        Integer countMaggioWait =0 ;
        Integer countGiugnoWait =0 ;
        Integer countLuglioWait =0 ;
        Integer countAgostoWait =0 ;
        Integer countSettembreWait =0 ;
        Integer countOttobreWait =0 ;
        Integer countNovembreWait =0 ;
        Integer countDicembreWait =0 ;
        for(int i = 0; i< listaWait.size() ; i++){
                String  month_name =  listaWait.get(i).getDataOra();
                String[] splitMonth_name = month_name.split("/");
                if(splitMonth_name[1].equals("01")){
                    countGennaioWait++;
                }
                else if(splitMonth_name[1].equals("02")){
                    countFebbraioWait++;
                }
                else if(splitMonth_name[1].equals("03")){
                    countMarzoWait++;
                }
                else if(splitMonth_name[1].equals("04")){
                    countAprileWait++;
                }
                else if(splitMonth_name[1].equals("05")){
                    countMaggioWait++;
                }
                else if(splitMonth_name[1].equals("06")){
                    countGiugnoWait++;
                }
                else if(splitMonth_name[1].equals("07")){
                    countLuglioWait++;
                }
                else if(splitMonth_name[1].equals("08")){
                    countAgostoWait++;
                }
                else if(splitMonth_name[1].equals("09")){
                    countSettembreWait++;
                }
                else if(splitMonth_name[1].equals("10")){
                    countOttobreWait++;
                }
                else if(splitMonth_name[1].equals("11")){
                    countNovembreWait++;
                }
                else if(splitMonth_name[1].equals("12")){
                    countDicembreWait++;
                }
            }



        data.add(new ValueDataEntry("Gennaio", countGennaioWait));
        data.add(new ValueDataEntry("Febbraio", countFebbraioWait));
        data.add(new ValueDataEntry("Marzo", countMarzoWait));
        data.add(new ValueDataEntry("Aprile", countAprileWait));
        data.add(new ValueDataEntry("Maggio", countMaggioWait));
        data.add(new ValueDataEntry("Giugno", countGiugnoWait));
        data.add(new ValueDataEntry("Luglio", countLuglioWait));
        data.add(new ValueDataEntry("Agosto", countAgostoWait));
        data.add(new ValueDataEntry("Settembre", countSettembreWait));
        data.add(new ValueDataEntry("Ottobre", countOttobreWait));
        data.add(new ValueDataEntry("Novembre", countNovembreWait));
        data.add(new ValueDataEntry("Dicembre", countDicembreWait));


        Cartesian cartesian = AnyChart.column();





        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Report per mese in Wait ");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Mesi");
        cartesian.yAxis(0).title("Report aggiunti");

        anyChartView.setChart(cartesian);
    }
}

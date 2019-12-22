package com.example.greencity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.greencity.pojo.InformazioniGenerali;
import com.example.greencity.pojo.Markers;

import java.util.Calendar;
import java.util.Date;

public class CustomDialog extends Dialog {
    private Button btnConferma;
    private Button btnCancella;

    public CustomDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mPrefs = getContext().getSharedPreferences("SP_INFO",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        SharedPreferences mPrefs2 = getContext().getSharedPreferences("SP_INFO2",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = mPrefs2.edit();
        String lat = mPrefs2.getString("LATITUDE","");
        String longi = mPrefs2.getString("LONGITUDE","");
        String idUsSP = mPrefs.getString("IDUSER","").toString();
        setContentView(R.layout.activity_custom_dialog);
        CustomDialog c = new CustomDialog(getContext());
        btnConferma = findViewById(R.id.buttonConferma);
        btnCancella = findViewById(R.id.buttonCancella);
        String idUser = InformazioniGenerali.getInformazioniGenerali().getIdUs();
        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentTime = Calendar.getInstance().getTime();

                //Passare l oggetto Marker che viene creato dalla Dialog
                Markers m = new Markers("titolo","","","arancione","",lat,longi,"In Attesa",currentTime.toString());
                if(idUsSP == null){
                    DBFirebase.getDbFirebase().getDatabaseReference().child("users").child(idUser).push().setValue(m);
                }else{
                    DBFirebase.getDbFirebase().getDatabaseReference().child("users").child(idUsSP).push().setValue(m);
                }
                dismiss();
            }
        });


        btnCancella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



    }




}

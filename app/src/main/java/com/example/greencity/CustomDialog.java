package com.example.greencity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.view.menu.MenuBuilder;

import com.example.greencity.activity.Navbar;
import com.example.greencity.activity.SplashGreenCity;
import com.example.greencity.pojo.InformazioniGenerali;
import com.example.greencity.pojo.Markers;
import com.example.greencity.pojo.Utente;

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
        String lat = mPrefs.getString("LATITUDE","");
        String longi = mPrefs.getString("LONGITUDE","");
        String idUsSP = mPrefs.getString("IDUSER","").toString();
        setContentView(R.layout.activity_custom_dialog);
        CustomDialog c = new CustomDialog(getContext());
        btnConferma = findViewById(R.id.buttonConferma);
        btnCancella = findViewById(R.id.buttonCancella);
        String idUser = InformazioniGenerali.getInformazioniGenerali().getIdUs();
        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Passare l oggetto Marker che viene creato dalla Dialog
                Markers m = new Markers("titolo","","","","",lat,longi);
                if(idUsSP == null){
                    DBFirebase.getDbFirebase().getDatabaseReference().child("users").child(idUser).push().setValue(m);
                }else{
                    DBFirebase.getDbFirebase().getDatabaseReference().child("users").child(idUsSP).push().setValue(m);
                }
                Intent i = new Intent(getContext(), SplashGreenCity.class);
                getContext().startActivity(i);
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

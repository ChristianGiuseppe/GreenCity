package com.example.greencity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.greencity.activity.SplashGreenCity;
import com.example.greencity.pojo.InformazioniGenerali;
import com.example.greencity.pojo.Markers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomDialog extends Dialog {
    private Button btnConferma;
    private Button btnCancella;
    private TextView titoloText;
    private TextView descrizioneText;
    public CustomDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences mPrefs = getContext().getSharedPreferences("SP_INFO",Context.MODE_PRIVATE);
        SharedPreferences mPrefs2 = getContext().getSharedPreferences("SP_INFO2",Context.MODE_PRIVATE);
        String lat = mPrefs2.getString("LATITUDE","");
        String longi = mPrefs2.getString("LONGITUDE","");
        String idUsSP = mPrefs.getString("IDUSER","").toString();
        setContentView(R.layout.activity_custom_dialog);
        btnConferma = findViewById(R.id.buttonConferma);
        btnCancella = findViewById(R.id.buttonCancella);
        String idUser = InformazioniGenerali.getInformazioniGenerali().getIdUs();
        titoloText = (TextView) findViewById(R.id.title_markers);
        descrizioneText = (TextView) findViewById(R.id.description_problem);
        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentTime = Calendar.getInstance().getTime();
                String DateT = getCurrentTime();
                if (isNetworkConnected()) {
                    if(checkTitle() && checkDescription()) {
                        //Passare l oggetto Marker che viene creato dalla Dialog
                        if (idUsSP == null || idUsSP == "") {
                            Markers m = new Markers(idUser, titoloText.getText().toString(), descrizioneText.getText().toString(), lat, longi, "WAIT", DateT);
                            DBFirebase.getDbFirebase().getDatabaseReference().child("lista_wait").push().setValue(m);
                        } else {
                            Markers m = new Markers(idUsSP, titoloText.getText().toString(), descrizioneText.getText().toString(), lat, longi, "WAIT", DateT);
                            DBFirebase.getDbFirebase().getDatabaseReference().child("lista_wait").push().setValue(m);

                        }
                        dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Complimenti!");
                        builder.setMessage("Report inserito con Successo. Premi OK per tornare alla Home")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent i = new Intent(getContext(), SplashGreenCity.class);
                                        getContext().startActivity(i);
                                        dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Nessuna connessione ad Internet trovata");
                    builder.setMessage("Per utilizzare l'app hai bisogno di una connessione mobile o wifi. Premi OK per uscire")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dismiss();
                                    getOwnerActivity().finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            }
        });


        btnCancella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private boolean checkTitle(){
        if (TextUtils.isEmpty(titoloText.getText())){
            titoloText.setError("Inserire il titolo del report");
            return false;
        }
        return true;
    }


    private boolean checkDescription(){
        if (TextUtils.isEmpty(descrizioneText.getText())){
            descrizioneText.setError("Inserire la descrizione del report");
            return false;
        }
        return true;
    }

    /**
     * getCurrentTime() it will return system time
     *
     * @return
     */
    public static String getCurrentTime() {
        //date output format
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }// end of getCurrentTime()
}

package com.example.greencity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

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
        SharedPreferences mPrefs2 = getContext().getSharedPreferences("SP_INFO2",Context.MODE_PRIVATE);
        String lat = mPrefs2.getString("LATITUDE","");
        String longi = mPrefs2.getString("LONGITUDE","");
        String idUsSP = mPrefs.getString("IDUSER","").toString();
        setContentView(R.layout.activity_custom_dialog);
        btnConferma = findViewById(R.id.buttonConferma);
        btnCancella = findViewById(R.id.buttonCancella);
        String idUser = InformazioniGenerali.getInformazioniGenerali().getIdUs();
        TextView titoloText = (TextView) findViewById(R.id.title_markers);
        TextView descrizioneText = (TextView) findViewById(R.id.description_problem);
        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentTime = Calendar.getInstance().getTime();
                if (isNetworkConnected()) {
                    //Passare l oggetto Marker che viene creato dalla Dialog
                    Markers m = new Markers(titoloText.getText().toString(), descrizioneText.getText().toString(), "arancione", lat, longi, "WAIT", currentTime.toString());
                    if (idUsSP == null || idUsSP == "") {
                        DBFirebase.getDbFirebase().getDatabaseReference().child("users").child(idUser).child("lista_report").push().setValue(m);

                    } else {
                        DBFirebase.getDbFirebase().getDatabaseReference().child("users").child(idUsSP).push().setValue(m);
                    }
                    dismiss();
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


}

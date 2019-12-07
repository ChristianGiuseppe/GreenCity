package com.example.greencity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

        setContentView(R.layout.activity_custom_dialog);
        CustomDialog c = new CustomDialog(getContext());
        btnConferma = findViewById(R.id.buttonConferma);
        btnCancella = findViewById(R.id.buttonCancella);
        String idUser = InformazioniGenerali.getInformazioniGenerali().getIdUs();
        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Passare l oggetto Marker che viene creato dalal Dialog
                //Utente user = new Utente("nome", "cognome", "email", "password", "regione", "capoluogo");
                Markers m = new Markers("titolo","","","","","-34","151");
                DBFirebase.getDbFirebase().getDatabaseReference().child("users").child(idUser.toString()).push().setValue(m);

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

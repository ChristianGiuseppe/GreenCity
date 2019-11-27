package com.example.greencity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



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
        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

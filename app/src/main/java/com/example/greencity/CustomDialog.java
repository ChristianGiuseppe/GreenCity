package com.example.greencity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
    }
}

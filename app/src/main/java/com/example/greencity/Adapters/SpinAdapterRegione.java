package com.example.greencity.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.greencity.pojo.Regioni;

import java.util.ArrayList;

public class SpinAdapterRegione extends ArrayAdapter<Regioni> {

    private Context context;
    private ArrayList<Regioni> values;

    public SpinAdapterRegione(Context context, int textViewResourceId, ArrayList<Regioni> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.WHITE);
        label.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        label.setText(values.get(position).getNome());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        label.setText(values.get(position).getNome());
        return label;
    }
}
package com.example.greencity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.greencity.R;
import com.example.greencity.pojo.Markers;

import java.util.List;

public class MarkersAdminAdapter extends ArrayAdapter<Markers> {

    private List<Markers> markersList;

    public MarkersAdminAdapter(@NonNull Context context, int resource, @NonNull List<Markers> objects) {
        super(context, resource, objects);
        markersList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.report_admin_item, null, false);
        ConstraintLayout colorStateReport = (ConstraintLayout) convertView.findViewById(R.id.report_color_state_admin);
        TextView titleReport = (TextView) convertView.findViewById(R.id.title_report_admin);
        TextView dateReport = (TextView) convertView.findViewById(R.id.report_date_admin);
        ImageButton imageButtonDone = (ImageButton) convertView.findViewById(R.id.btn_done);
        ImageButton imageButtonReject = (ImageButton) convertView.findViewById(R.id.btn_reject);

        imageButtonDone.setOnClickListener(view -> {

        });

        imageButtonReject.setOnClickListener(view -> {

        });
        return convertView;
    }
}

package com.example.greencity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.greencity.R;
import com.example.greencity.pojo.Markers;

import java.util.List;
import java.util.Objects;

public class MarkersUserAdapter extends ArrayAdapter<Markers> {

    private List<Markers> markersList;

    public MarkersUserAdapter(@NonNull Context context, int resource, @NonNull List<Markers> objects) {
        super(context, resource, objects);
        markersList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.report_user_item, null, false);
        ConstraintLayout colorStateReport = (ConstraintLayout) convertView.findViewById(R.id.report_color_state);
        TextView titleReport = (TextView) convertView.findViewById(R.id.title_report);
        TextView dateReport = (TextView) convertView.findViewById(R.id.report_date);
        ImageView imageStateReport = (ImageView) convertView.findViewById(R.id.inbox_report_state);
        titleReport.setText(markersList.get(position).getTitolo());
        dateReport.setText(markersList.get(position).getDataOra());
        switch (Objects.requireNonNull(markersList.get(position).getStato())) {
            case "WAIT":
                colorStateReport.setBackgroundColor(getContext().getColor(R.color.wait_report));
                imageStateReport.setBackgroundResource(R.drawable.ic_inbox_wating);
                break;
            case "REJECT":
                colorStateReport.setBackgroundColor(getContext().getColor(R.color.red_state));
                imageStateReport.setBackgroundResource(R.drawable.ic_inbox_reject);
                break;
            case "DONE":
                colorStateReport.setBackgroundColor(getContext().getColor(R.color.primary_color));
                imageStateReport.setBackgroundResource(R.drawable.ic_inbox_done);
                break;
        }

        return convertView;
    }
}

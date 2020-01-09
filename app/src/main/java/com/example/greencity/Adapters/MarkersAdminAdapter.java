package com.example.greencity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.greencity.DBFirebase;
import com.example.greencity.R;
import com.example.greencity.pojo.Markers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
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
        titleReport.setText(markersList.get(position).getTitolo());
        dateReport.setText(markersList.get(position).getDataOra());
        final String[] idkeyUserMarkerClick = {markersList.get(position).getKeyUser()};

        imageButtonDone.setOnClickListener(view -> {
            DatabaseReference fromPath = FirebaseDatabase.getInstance().getReference("lista_wait");
            DatabaseReference toPath = FirebaseDatabase.getInstance().getReference("lista_done");

            //copyRecord(fromPath,toPath,position);
            ValueEventListener reportListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // obtain list of java objects from the datasnapshot
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        Markers markers = postSnapshot.getValue(Markers.class);
                        if(idkeyUserMarkerClick[0] != "" ){
                            idkeyUserMarkerClick[0] = "";
                        markers.setStato("DONE");
                        String keyMark = postSnapshot.getKey();
                        fromPath.child(keyMark).setValue(null);
                        toPath.child(keyMark).setValue(markers);


                        
                        break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            DBFirebase.getDbFirebase().getDatabaseReference().child("lista_wait").orderByChild("keyUser").equalTo(idkeyUserMarkerClick[0].toString())
                    .addValueEventListener(reportListener);







        });

        imageButtonReject.setOnClickListener(view -> {

        });
        return convertView;
    }

    private void copyRecord(DatabaseReference fromPath, final DatabaseReference toPath,Integer i) {
        fromPath.addListenerForSingleValueEvent(new ValueEventListener()  {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                toPath.setValue(dataSnapshot.getValue().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String idkeyUserMarkerClick = markersList.get(i).getKeyUser();
                        ValueEventListener reportListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // obtain list of java objects from the datasnapshot
                                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                                    Markers markers = postSnapshot.getValue(Markers.class);
                                    markers.setStato("DONE");
                                    String keyMark = postSnapshot.getKey();
                                    /*fromPath.child(keyMark).setValue("xxx");
                                    toPath.child(keyMark).setValue(markers);*/
                                    break;
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        };
                        DBFirebase.getDbFirebase().getDatabaseReference().child("lista_wait").orderByChild("keyUser").equalTo(idkeyUserMarkerClick.toString())
                                .addValueEventListener(reportListener);



                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }



}

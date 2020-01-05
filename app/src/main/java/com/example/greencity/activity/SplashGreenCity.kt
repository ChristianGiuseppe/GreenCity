package com.example.greencity.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.greencity.DBFirebase
import com.example.greencity.R
import com.example.greencity.pojo.InformazioniGenerali
import com.example.greencity.pojo.Markers
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class SplashGreenCity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_green_city)

        val iLogin = Intent(this, Navbar::class.java)
        var listMarkers: ArrayList<Markers> = ArrayList()
        var idKey = InformazioniGenerali.getInformazioniGenerali().idUs

        val reportsListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // Get Post object and use the values to update the UI
                for (reportSnapshot in dataSnapshot.getChildren()) {
                    var mark: Markers = reportSnapshot.getValue(Markers::class.java)!!
                    listMarkers.add(mark)

                }
                val informazioniGenerali = InformazioniGenerali.getInformazioniGenerali()
                informazioniGenerali.markers = listMarkers
                startActivity(iLogin)
                finish()
            }

            override fun onCancelled(databaseError: DatabaseError) { // Getting Post failed, log a message

            }
        }

        DBFirebase.getDbFirebase().databaseReference.child("users").child(idKey)
            .child("lista_report").addValueEventListener(reportsListener)

        DBFirebase.getDbFirebase().databaseReference.removeEventListener(reportsListener)

    }
}

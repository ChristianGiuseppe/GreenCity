package com.example.greencity.activity

import android.content.Intent
import android.icu.text.IDNA
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.greencity.DBFirebase
import com.example.greencity.R
import com.example.greencity.pojo.InformazioniGenerali
import com.example.greencity.pojo.Markers
import com.example.greencity.pojo.Regioni
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SplashGreenCity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_green_city)
        Handler().postDelayed({

            val iLogin = Intent(this, Navbar::class.java)
            var listMarkers: ArrayList<Markers?> = ArrayList()
            DBFirebase.getDbFirebase().databaseReference.addValueEventListener(object :
                ValueEventListener {


                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val snapshotIterator = dataSnapshot.child("Marcatori").children
                    val iterator = snapshotIterator.iterator()
                    while (iterator.hasNext()) {
                        var nextIt = iterator.next()
                        var mark: Markers? = nextIt.getValue(Markers::class.java)
                        listMarkers.add(mark)

                    }
                    val informazioniGenerali = InformazioniGenerali.getInformazioniGenerali()
                    informazioniGenerali.markers = listMarkers
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })

            startActivity(iLogin)
            finish() // finish the current activity

        }, 3000)

    }
}

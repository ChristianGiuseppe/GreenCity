package com.example.greencity.activity

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.greencity.DBFirebase
import com.example.greencity.pojo.InformazioniGenerali
import com.example.greencity.pojo.Markers
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.lang.Exception
import android.content.Context
import java.io.File
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.google.firebase.database.FirebaseDatabase


class SplashGreenCity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.greencity.R.layout.activity_splash_green_city)
            val iLogin = Intent(this, Navbar::class.java)
            var listMarkers: ArrayList<Markers> = ArrayList()
            var list_done: ArrayList<Markers> = ArrayList()
            var list_reject: ArrayList<Markers> = ArrayList()
            var list_wait: ArrayList<Markers> = ArrayList()
            var idKey = InformazioniGenerali.getInformazioniGenerali().idUs
            val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
            val idUsSP = sharedPreferences?.getString("IDUSER","").toString()


            // CICLO LISTA WAITING
            val reportsListenerWaiting: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // Get Post object and use the values to update the UI
                for (reportSnapshot in dataSnapshot.getChildren()) {
                    var rep_wait: Markers = reportSnapshot.getValue(Markers::class.java)!!
                    list_wait.add(rep_wait)
                    InformazioniGenerali.getInformazioniGenerali().listaWait = list_wait
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) { // Getting Post failed, log a message

                }
            }
            DBFirebase.getDbFirebase().databaseReference.child("lista_wait").addValueEventListener(reportsListenerWaiting)

        // CICLO LISTA DONE
        val reportsListenerDone: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // Get Post object and use the values to update the UI
                for (reportSnapshot in dataSnapshot.getChildren()) {
                    var rep_done: Markers = reportSnapshot.getValue(Markers::class.java)!!
                    list_done.add(rep_done)
                    InformazioniGenerali.getInformazioniGenerali().listaDone = list_done
                }
            }

            override fun onCancelled(databaseError: DatabaseError) { // Getting Post failed, log a message

            }
        }
        DBFirebase.getDbFirebase().databaseReference.child("lista_done").addValueEventListener(reportsListenerDone)


        // CICLO LISTA REJECT
        val reportsListenerReject: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // Get Post object and use the values to update the UI
                for (reportSnapshot in dataSnapshot.getChildren()) {
                    var rep_reject: Markers = reportSnapshot.getValue(Markers::class.java)!!
                    list_reject.add(rep_reject)
                    InformazioniGenerali.getInformazioniGenerali().listaReject = list_reject
                }
            }

            override fun onCancelled(databaseError: DatabaseError) { // Getting Post failed, log a message

            }
        }
        DBFirebase.getDbFirebase().databaseReference.child("lista_reject").addValueEventListener(reportsListenerReject)





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
        if( idKey != null){

            DBFirebase.getDbFirebase().databaseReference.child("users").child(idKey)
                .child("lista_report").addValueEventListener(reportsListener)
            DBFirebase.getDbFirebase().databaseReference.removeEventListener(reportsListener)

        }else{

            DBFirebase.getDbFirebase().databaseReference.child("users").child(idUsSP)
                .child("lista_report").addValueEventListener(reportsListener)
            DBFirebase.getDbFirebase().databaseReference.removeEventListener(reportsListener)

        }






    }





}

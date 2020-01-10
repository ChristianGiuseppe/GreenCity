package com.example.greencity.activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.greencity.DBFirebase
import com.example.greencity.pojo.InformazioniGenerali
import com.example.greencity.pojo.Markers
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import android.content.Context








class SplashGreenCity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.greencity.R.layout.activity_splash_green_city)
        val iLogin = Intent(this, Navbar::class.java)
        var list_done: ArrayList<Markers> = ArrayList()
        var list_reject: ArrayList<Markers> = ArrayList()
        var list_wait: ArrayList<Markers> = ArrayList()
        var idKey = InformazioniGenerali.getInformazioniGenerali().idUs
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val idUsSP = sharedPreferences?.getString("IDUSER","").toString()



        val reportsListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // Get Post object and use the values to update the UI
                for (reportSnapshot in dataSnapshot.getChildren()) {
                    var mark = reportSnapshot.getValue()!!
                    if(mark.equals("true")){
                        adminListReportWait(list_wait)
                        adminListReportDone(list_done)
                        adminListReportReject(list_reject)
                        break
                    }
                    else{
                        userListReportWait(list_wait)
                        userListReportDone(list_done)
                        userListReportReject(list_reject)
                        break
                    }
                }
                startActivity(iLogin)
                finish()
            }

            override fun onCancelled(databaseError: DatabaseError) { // Getting Post failed, log a message

            }
        }
        if( idKey != null){

            DBFirebase.getDbFirebase().databaseReference.child("users").child(idKey)
                .addValueEventListener(reportsListener)
            DBFirebase.getDbFirebase().databaseReference.removeEventListener(reportsListener)

        }
        else{

            DBFirebase.getDbFirebase().databaseReference.child("users").child(idUsSP)
                .addValueEventListener(reportsListener)

        }



    }



    private fun userListReportWait(list_wait :  ArrayList<Markers>){
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

        DBFirebase.getDbFirebase().databaseReference.child("lista_wait").orderByChild("keyUser").equalTo(idUsSP.toString())
            .addListenerForSingleValueEvent(reportsListenerWaiting)

    }

    private fun userListReportDone(list_done :  ArrayList<Markers>){
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val idUsSP = sharedPreferences?.getString("IDUSER","").toString()

        // CICLO LISTA DONE
        val reportsListenerWaiting: ValueEventListener = object : ValueEventListener {
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

        DBFirebase.getDbFirebase().databaseReference.child("lista_done").orderByChild("keyUser").equalTo(idUsSP.toString())
            .addListenerForSingleValueEvent(reportsListenerWaiting)

    }

    private fun userListReportReject(list_reject :  ArrayList<Markers>){
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val idUsSP = sharedPreferences?.getString("IDUSER","").toString()

        // CICLO LISTA REJECT
        val reportsListenerWaiting: ValueEventListener = object : ValueEventListener {
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

        DBFirebase.getDbFirebase().databaseReference.child("lista_reject").orderByChild("keyUser").equalTo(idUsSP.toString())
            .addListenerForSingleValueEvent(reportsListenerWaiting)

    }


    private fun adminListReportWait(list_wait :  ArrayList<Markers>){
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

        DBFirebase.getDbFirebase().databaseReference.child("lista_wait")
            .addListenerForSingleValueEvent(reportsListenerWaiting)

    }

    private fun adminListReportDone(list_done :  ArrayList<Markers>){
        // CICLO LISTA DONE
        val set = HashSet<Markers>()
        var flag = true
        val reportsListenerWaiting: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // Get Post object and use the values to update the UI
                for (reportSnapshot in dataSnapshot.getChildren()) {
                    var rep_done: Markers = reportSnapshot.getValue(Markers::class.java)!!
                    list_done.add(rep_done)
                    for (i in 0 until list_done.size) {
                        flag = set.add(list_done.get(i))
                        if (!flag) {
                            list_done.remove(list_done.get(i))
                            InformazioniGenerali.getInformazioniGenerali().listaDone = list_done
                            flag = true
                            break
                        }
                    }

                }
                InformazioniGenerali.getInformazioniGenerali().listaDone = list_done
            }

            override fun onCancelled(databaseError: DatabaseError) { // Getting Post failed, log a message

            }

        }

        DBFirebase.getDbFirebase().databaseReference.child("lista_done")
            .addValueEventListener(reportsListenerWaiting)

    }

    private fun adminListReportReject(list_reject :  ArrayList<Markers>){
        // CICLO LISTA REJECT
        val set = HashSet<Markers>()
        var flag = true
        val reportsListenerWaiting: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // Get Post object and use the values to update the UI
                for (reportSnapshot in dataSnapshot.getChildren()) {
                    var rep_reject: Markers = reportSnapshot.getValue(Markers::class.java)!!
                    list_reject.add(rep_reject)
                    for (i in 0 until list_reject.size) {
                        flag = set.add(list_reject.get(i))
                        if (!flag) {
                            list_reject.remove(list_reject.get(i))
                            InformazioniGenerali.getInformazioniGenerali().listaReject = list_reject
                            flag = true
                            break
                        }
                    }

                }
                InformazioniGenerali.getInformazioniGenerali().listaReject = list_reject
            }

            override fun onCancelled(databaseError: DatabaseError) { // Getting Post failed, log a message

            }
        }

        DBFirebase.getDbFirebase().databaseReference.child("lista_reject")
            .addValueEventListener(reportsListenerWaiting)

    }

}


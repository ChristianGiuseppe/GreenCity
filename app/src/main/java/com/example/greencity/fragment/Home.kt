package com.example.greencity.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.greencity.DBFirebase
import com.example.greencity.R
import com.example.greencity.activity.MainActivity
import com.example.greencity.pojo.InformazioniGenerali
import com.example.greencity.pojo.Markers
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class Home : Fragment() {
    private var textWelcomeUser: TextView? = null
    private var textCountReports: TextView? = null
    private var logOutImg : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (container != null)

            container.removeAllViews()
        textWelcomeUser =  view?.findViewById(R.id.textViewWelcomeUs)
        logOutImg = view?.findViewById(R.id.logOutImg)
        logOutImg?.isClickable = true



        val sharedPreferences = context?.getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        var textWelcomeSP = sharedPreferences?.getString("WELCOMEUSER","").toString()
        textWelcomeUser?.text = "Benvenuto\n"+textWelcomeSP
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = context?.getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val sharedPreferences2 = context?.getSharedPreferences("SP_INFO2", Context.MODE_PRIVATE)
        val idUsSP = sharedPreferences?.getString("IDUSER","").toString()
        val editor = sharedPreferences?.edit()
        val editor2 = sharedPreferences2?.edit()
        textCountReports =  view.findViewById(R.id.inAttesaCountText)
        var countR = 0

        DBFirebase.getDbFirebase().databaseReference.addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(InformazioniGenerali.getInformazioniGenerali().idUs != null){
                    val snapshotIterator = dataSnapshot.child("users").child(InformazioniGenerali.getInformazioniGenerali().idUs).children
                    val iterator = snapshotIterator.iterator()
                    while (iterator.hasNext()) {
                        var isValid = false
                        var nextIt = iterator.next()
                        var finishMarkCognome = nextIt.key?.equals("cognome")
                        var finishMarkNome = nextIt.key?.equals("nome")
                        var finishMarkEmail = nextIt.key?.equals("email")
                        var finishMarkPassword = nextIt.key?.equals("password")
                        var finishMarkAdmin =  nextIt.key?.equals("admin")


                        if(finishMarkCognome == true || finishMarkNome == true || finishMarkEmail == true || finishMarkPassword == true || finishMarkAdmin == true){
                            isValid = true
                        }


                        if(isValid == false){
                            var marker: Markers? = nextIt.getValue(Markers::class.java)
                            var reportsState = marker?.stato
                            if(reportsState != ""){
                                countR ++
                                textCountReports?.text = countR.toString()
                            }
                        }
                    }
                }
                else{
                    val snapshotIterator = dataSnapshot.child("users").child(idUsSP.toString()).children
                    val iterator = snapshotIterator.iterator()
                    while (iterator.hasNext()) {
                        var isValid = false
                        var nextIt = iterator.next()
                        var finishMarkCognome = nextIt.key?.equals("cognome")
                        var finishMarkNome = nextIt.key?.equals("nome")
                        var finishMarkEmail = nextIt.key?.equals("email")
                        var finishMarkPassword = nextIt.key?.equals("password")
                        var finishMarkAdmin =  nextIt.key?.equals("admin")


                        if(finishMarkCognome == true || finishMarkNome == true || finishMarkEmail == true || finishMarkPassword == true || finishMarkAdmin == true){
                            isValid = true
                        }


                        if(isValid == false){
                            var marker: Markers? = nextIt.getValue(Markers::class.java)
                            var reportsState = marker?.stato
                            if(reportsState != ""){
                                 countR ++
                                textCountReports?.text = countR.toString()
                            }
                        }
                    }
                }
            }

        })
        var textWelcomeSP = sharedPreferences?.getString("WELCOMEUSER","").toString()

        textWelcomeUser =  view.findViewById(R.id.textViewWelcomeUs)
        textWelcomeUser?.text = "Benvenuto\n"+textWelcomeSP

        logOutImg = view?.findViewById(R.id.logOutImg)
        logOutImg?.isClickable = true

        logOutImg?.setOnClickListener {
            editor?.clear()
            editor?.commit()
            val iLogin = Intent (context, MainActivity::class.java)
            iLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(iLogin)


        }

    }
}

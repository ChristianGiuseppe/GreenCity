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
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment() {
    private var textWelcomeUser: TextView? = null
    private var inAttesaTextCountReports: TextView? = null
    private var ConfermatoTextCountReports: TextView? = null
    private var RifiutatoTextCountReports: TextView? = null
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
        var idKey = InformazioniGenerali.getInformazioniGenerali().idUs
        inAttesaTextCountReports =  view.findViewById(R.id.inAttesaCountText)
        ConfermatoTextCountReports = view.findViewById(R.id.inConfermaCountText)
        RifiutatoTextCountReports = view.findViewById(R.id.inRifiutoCountText)
        var countAttes = 0
        var countRifiuto = 0
        var countConferma = 0
        var listMarkers: ArrayList<Markers> = ArrayList()
        val reportsListener: ValueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (reportSnapshot in dataSnapshot.getChildren()) {
                        var mark: Markers = reportSnapshot.getValue(Markers::class.java)!!
                        if(mark.stato != null  && mark.stato != ""){
                            if(mark.stato.equals("WAIT")){
                                countAttes ++
                                inAttesaTextCountReports?.text = countAttes.toString()
                            }
                            else if(mark.stato.equals( "Confermato")){
                                countConferma ++
                                inConfermaCountText?.text = countConferma.toString()
                            }
                            else if(mark.stato.equals( "Rifutato")){
                                countRifiuto ++
                                inRifiutoCountText?.text = countRifiuto.toString()
                            }
                        }
                    }
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
        var textWelcomeSP = sharedPreferences?.getString("WELCOMEUSER","").toString()

        textWelcomeUser =  view.findViewById(R.id.textViewWelcomeUs)
        textWelcomeUser?.text = "Benvenuto\n"+textWelcomeSP

        logOutImg = view?.findViewById(R.id.logOutImg)
        logOutImg?.isClickable = true

        logOutImg?.setOnClickListener {
            DBFirebase.setDbFirebaseNull()
            editor?.clear()
            editor?.commit()
            editor2?.clear()
            editor2?.commit()
            val iLogin = Intent (context, MainActivity::class.java)
            iLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP )
            activity?.finish()
            startActivity(iLogin)
        }

    }
}

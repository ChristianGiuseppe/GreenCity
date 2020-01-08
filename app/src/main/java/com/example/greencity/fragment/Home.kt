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

class Home : Fragment() {
    private var textWelcomeUser: TextView? = null
    private var inAttesaTextCountReports: TextView? = null
    private var confermatoTextCountReports: TextView? = null
    private var rifiutatoTextCountReports: TextView? = null
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
        var idKey = InformazioniGenerali.getInformazioniGenerali().idUs
        inAttesaTextCountReports =  view.findViewById(R.id.inAttesaCountText)
        confermatoTextCountReports = view.findViewById(R.id.inConfermaCountText)
        rifiutatoTextCountReports = view.findViewById(R.id.inRifiutoCountText)

        val sharedPreferences = context?.getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()


        var listaWait: ArrayList<Markers> = ArrayList()
        var listaDone: ArrayList<Markers> = ArrayList()
        var listaReject: ArrayList<Markers> = ArrayList()

        listaWait = InformazioniGenerali.getInformazioniGenerali().listaWait
        listaDone = InformazioniGenerali.getInformazioniGenerali().listaDone
        listaReject = InformazioniGenerali.getInformazioniGenerali().listaReject

        inAttesaTextCountReports?.text = listaWait.size.toString()
        confermatoTextCountReports?.text = listaDone.size.toString()
        rifiutatoTextCountReports?.text = listaReject.size.toString()

        var textWelcomeSP = sharedPreferences?.getString("WELCOMEUSER","").toString()

        textWelcomeUser =  view.findViewById(R.id.textViewWelcomeUs)
        textWelcomeUser?.text = "Benvenuto\n"+textWelcomeSP

        logOutImg = view?.findViewById(R.id.logOutImg)
        logOutImg?.isClickable = true

        logOutImg?.setOnClickListener {
            DBFirebase.setDbFirebaseNull()
            editor?.clear()
            editor?.commit()
            val iLogin = Intent (context, MainActivity::class.java)
            iLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP )
            activity?.finish()
            startActivity(iLogin)
        }

    }
}

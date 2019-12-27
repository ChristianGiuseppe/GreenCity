package com.example.greencity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.greencity.Adapters.MarkersAdapter
import com.example.greencity.R
import com.example.greencity.pojo.InformazioniGenerali
import com.example.greencity.pojo.Markers


class ReportUser : Fragment() {

    private var listViewMarkers: ListView? = null
    private var listaReportUser: List<Markers> = ArrayList<Markers>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listaReportUser = InformazioniGenerali.getInformazioniGenerali().markers;
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        container?.removeAllViews()
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_report_user, container, false)
        listViewMarkers = v.findViewById(R.id.lista_report)
        //TODO DA SOSTITUIRE MOCK LISTA REPORT
        //listaReportUser = InformazioniGenerali.getInformazioniGenerali().markers
        listaReportUser = listOf(
            Markers(
                "titolo",
                "",
                "",
                "arancione",
                "",
                "",
                "",
                "WAIT",
                "13:40:00 16/06/2020"
            )
        )
        var markersAdapter: MarkersAdapter =
            MarkersAdapter(context!!, R.layout.report_user_item, listaReportUser)
        listViewMarkers?.adapter = markersAdapter
        return v
    }

}

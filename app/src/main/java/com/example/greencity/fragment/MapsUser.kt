package com.example.greencity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.greencity.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker


/**
 * @class MapsUser: Questa classe gestisce il fragment per la mappa
 * @var mapGreenCity: utilizzata per settare le informazioni sulla mappa
 */
class MapsUser : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mapGreenCity: GoogleMap;

    /**
     * @callback onMapReady: viene invocata nel momento in cui la mappa può' essere visualizzata
     * in questo caso nel momento in cui è pronta vengono settati i paramentri necessari
     * per la visualizzazione.
     */
    override fun onMapReady(mappa: GoogleMap) {
        mapGreenCity = mappa
        mapGreenCity.uiSettings.isZoomControlsEnabled = true
        mapGreenCity.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        val root: View = inflater.inflate(R.layout.fragment_maps_user, container, false)
        return root
    }
}
